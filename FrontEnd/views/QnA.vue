<template>
  <b-container fluid id="main" style="padding:0;">
    <navi-header style="z-index:100;"/>
    <div style="height:130px;"></div>
    <div style="height:1300px;">
    <search-qna></search-qna>
    <b-container>
      <regist-qna style="float: right;" class="mt-3" v-on:mvFirstPage="mvFirstPage"></regist-qna>
    </b-container>
    <b-container>
    <b-table 
    :per-page="perPage"
    :current-page="currentPage"
    ref="qnaTable"
    id="qnaTable"
    hover :items="qnas" :fields="fields" class="mt-3"> 
    <template #cell(completed)="row">
         <span v-if="row.item.completed == 'false'">답변대기</span>
        <span v-if="row.item.completed == 'true'" style="color:red">답변완료</span>
      </template>
      <template #cell(detail)="row">
        <b-button v-if="row.item.isprivate == 'false'" variant="outline-secondary" size="sm" @click="[row.toggleDetails(), setDetail(row.item)]" class="mr-2">상세보기</b-button>
        <span v-if="row.item.isprivate == 'true'">🔐</span>
      </template>
      <template  #row-details="row">
        <b-card bg-variant="light"  :header="row.item.mode=='detail'? 'Content':'Title'" class="text-center">
          <b-row class="mb-2">
            <b-col v-if="row.item.mode=='detail'">{{row.item.content }}</b-col>
            <b-col v-if="row.item.mode=='update'">
              <b-form-input
              id="input-1"
              v-model="form.title"
              placeholder="제목을 입력해주세요."
              required
            ></b-form-input>
            <b-form-checkbox
              id="checkbox-1"
              v-model="form.isprivate"
              name="checkbox-1"
              value="true"
              unchecked-value="false"
            >나만보기
           </b-form-checkbox>
            </b-col>
          </b-row>
          <div v-if="row.item.mode=='detail'">
            <b-button pill size="sm" class="mt-5" v-if="!row.item.answer" variant="success" @click="updateQna(row.item)">수정하기</b-button>
            <b-button pill size="sm" class="mt-5" variant="danger" @click="deleteQna(row.item.id)">삭제하기</b-button>
          </div>           
        </b-card>
        <b-card style="z-index:-1;" bg-variant="light" :header="row.item.mode=='detail'? 'Answer':'Content'" class="text-center">
          <b-row class="mb-2">
            <b-col v-if="row.item.answer">{{row.item.answer}}</b-col>
            <b-col v-else>
              <div v-if="row.item.mode=='detail'">
                <b-form-textarea 
                  id="textarea-auto-height"
                  placeholder="Auto height textarea"
                  rows="3"
                  max-rows="8"
                v-model="row.item.answerText"></b-form-textarea>
              <br>
              <b-button variant="outline-info" @click="saveAnswer(row.item)">작성완료</b-button>
              </div>

              <div  v-if="row.item.mode=='update'">             
                <b-form-textarea
                  id="textarea-auto-height"
                  placeholder="Auto height textarea"
                  rows="3"
                  max-rows="8"
                v-model="form.content"></b-form-textarea>
                <br>
                <b-button pill size="sm" class="mt-5" v-if="!row.item.answer" variant="success" @click="saveQna(row.item)">저장하기</b-button>
            <b-button pill size="sm" class="mt-5" v-if="!row.item.answer" variant="success" @click="cancelUpdateQna(row.item)">취소하기</b-button>
            <b-button pill size="sm" class="mt-5" variant="danger" @click="deleteQna(row.item.id)">삭제하기</b-button>
            </div>

            </b-col>
          </b-row>
        </b-card>
      </template>
    </b-table>
    <b-row>
                <b-col/>
                <b-col>
                  <b-pagination
                  v-model="currentPage"
                  :total-rows="rows"
                  :per-page="perPage"
                  aria-controls="qnaTable" pills
                  ></b-pagination></b-col>
                  <b-col/>
              </b-row>
    </b-container>
    </div>
  </b-container>
</template>


<script>
import NaviHeader from '@/layout/Header2.vue';
import SearchQna from '../components/qna/SearchQna.vue';
import RegistQna from '../components/qna/RegistQna.vue';
import http from '@/util/http-common';

export default {
  name: "QnA",
  components: {
    NaviHeader,
    SearchQna,
    RegistQna
  },
  data() {
      return {
         fields: [{'title':'제목'}, {'writer': '작성자'}, {'completed' : '답변여부'}, {'detail': '세부정보'}],
  
         form: {
          content: '',
           title: '',
           isprivate: ''
         },
         currentPage : 1,
         perPage: 10,
         contentText: '',

      }
    },
     methods: {
        registQna(){
      },
      deleteQna(id) {
        let searchCondition = {
          id : id,
          key : this.$store.state.searchCondition.key,
          word : this.$store.state.searchCondition.word,
        }
        this.$store.dispatch('deleteQna', searchCondition);
        this.$emit('hideModal')
      },
        mvUpdate(data){
          console.log()
            this.$store.dispatch('detailQnA', data);
            this.$router.push({name:'update',params:{id: data}});
        },
        mvFirstPage () {
        this.currentPage = 1
      },
      updateQna(item) {
        item.mode = 'update';
        this.form.title = item.title;
        this.form.content = item.content;

      },
      saveQna(item) {
        const qna = {
          id : item.id,
          title: this.form.title,
          content: this.form.content,
          isprivate :  this.form.isprivate
        }
        http
        .put('/qna', qna)
        .then(({data}) => {
          this.form.title = data.title;
          this.form.content = data.content;
          item.mode = 'detail'
          console.dir(item)
          this.$store.dispatch('loadQnA', this.$store.state.searchCondition);

        })
        .catch(() => {
          alert('에러 발생');
        });
      },
      cancelUpdateQna (item) {
        console.dir(item)
        item.mode = 'detail';
      },
      setDetail(item) {
        console.dir(item)
        item.answerText = '',
        item.mode = 'detail';
      },
      saveAnswer(item) {
        const qna = {
          id : item.id,
          completed: 'true',
          answer :  item.answerText
        }
        http
        .put('/qna/answer', qna)
        .then(({data}) => {
          item.answerText = data.answer;
          item.mode = 'detail'
          this.$store.dispatch('loadQnA', this.$store.state.searchCondition);

        })
        .catch(() => {
          alert('에러 발생');
        });
      }
    }, 
    created() {
      this.$store.dispatch('loadQnA', {
        key: '',
        word: ''
      });
    },
    computed: {
      qnas() {
        return this.$store.state.qnas
      },
      rows() {
        return this.$store.state.qnas.length
      },
   
    }
};
</script>


<style scoped>

</style>