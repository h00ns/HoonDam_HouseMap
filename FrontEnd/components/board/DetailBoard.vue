<template>
<b-container>
       <b-card bg-variant="light"  v-if="mode=='detail'" header="Title" class="text-center">
          <b-row class="mb-2">
            <b-col >{{ form.title }}</b-col>
           </b-row>
        </b-card>
        <br  v-if="mode=='detail'">
        <b-card bg-variant="light"  v-if="mode=='detail'" header="Content" class="text-center">
          <b-row class="mb-2">
            <b-col >{{ form.content }}</b-col>
           </b-row>
        </b-card>
         <br  v-if="mode=='detail'">
         <div v-if="mode=='detail'" id="formBtn"> 
              <b-button pill @click="updateBoard" variant="success">수정하기</b-button>
              <b-button pill @click="deleteBoard" variant="danger">삭제하기</b-button>
          </div>
        <b-form v-if="mode=='update'">
        <!-- asdasdassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss -->
      <b-form-group class="m-5"
        id="input-group-1"
        label="제목"
        label-for="input-1"
        styel="margin: 0px;"
      >
        <b-form-input
          id="input-1"
          v-model="form.title"
          required
          :disabled="disabled"
        ></b-form-input>
      </b-form-group>
      <b-form-group class="m-5" id="input-group-2" label="내용" label-for="input-2" style=" padding: 0;">
        <b-form-textarea
        id="input-2"
        v-model="form.content"
        placeholder="내용을 입력해주세요."
        rows="8"
        max-rows="6"
        :disabled="disabled"
        ></b-form-textarea>
      </b-form-group>
      <div v-if="mode == 'update'" id="formBtn">
        <b-button pill @click="saveBoard" variant="success">저장하기</b-button>
        <b-button pill @click="cancelUpdateBoard" variant="warning">취소하기</b-button>
        <b-button pill @click="deleteBoard" variant="danger">삭제하기</b-button>
      </div>
    </b-form>
  </b-container>
</template>
<script>
import http from '@/util/http-common';
  export default {
    props: ['id','title', 'content'],
    data() {
      return {
       form: {
          title: this.title,
          content: this.content,
        },
        mode: 'detail'
      }
    },
    methods: {
     updateBoard() {
        this.mode = 'update'    
      },
      deleteBoard() {
        this.form.title = '';
        this.form.content = '';
        let searchCondition = {
          id : this.id,
          key : this.$store.state.searchCondition.key,
          word : this.$store.state.searchCondition.word,
        }
        this.$store.dispatch('deleteBoard', searchCondition);
        this.$emit('hideModal')
      },
      saveBoard() {
         const board = {
              id : this.id,
             title: this.form.title,
             content: this.form.content,
         }
        http
        .put('/board', board)
        .then(({data}) => {
          this.form.title = data.title;
          this.form.content = data.content;
          this.mode = 'detail'
          this.$store.dispatch('loadBoard', this.$store.state.searchCondition);
        })
        .catch(() => {
          alert('에러 발생');
        });
      },
      cancelUpdateBoard() {
        this.form.title = this.title;
        this.form.content = this.content;
        this.mode = 'detail'
      }

    },
    computed: {
      disabled() {
        return this.mode === 'detail'
      },

    }
  }
</script>

<style scoped>
#formBtn{
  text-align: center;
}

#formBtn > button{
  margin: 0 5px;
}
</style>