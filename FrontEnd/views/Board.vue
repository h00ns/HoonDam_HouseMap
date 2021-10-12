<template>
  <b-container fluid style="padding:0;">
    <navi-header style="z-index:100;"/>
    <div style="height:130px;"></div>
    <b-container style="height:900px;">
        <h1 class="mt-5">공지사항</h1>
        <div class="mt-5" >
            <search-board v-on:search="search"></search-board>
            <b-container>
              <regist-board style="float: right;" class="mt-3" v-on:mvFirstPage="mvFirstPage"></regist-board>
            </b-container>
            <b-table 
            :per-page="perPage"
            :current-page="currentPage"
            id="boardTable" hover  ref="boardTable" :items="boards" :fields="fields" class="mt-3" @row-clicked="rowClicked"> 
            <template #cell(boarddate)="row">
              <span>{{ $moment(row.item.boarddate).format('YYYY-MM-DD')}}</span>
              </template>
              </b-table>
              <b-row>
                <b-col/>
                <b-col>
                  <b-pagination
                  v-model="currentPage"
                  :total-rows="rows"
                  :per-page="perPage"
                  aria-controls="boardTable" pills
                  ></b-pagination></b-col>
                  <b-col/>
              </b-row>
        </div>
    </b-container>
      <b-modal id="modal-scoped" ref="boardDetail">
    <template #modal-header="">     
      <h5>세부내용</h5>
    </template>
    <template #default="">
      <detail-board :id="id" :title="title" :content="content" v-on:hideModal="hideModal"></detail-board>
    </template> 
    <template #modal-footer="{cancel}">
      <!-- Emulate built in modal footer ok and cancel button actions -->
      <b-button size="sm" variant="outline-danger" @click="cancel()">
        취소
      </b-button> 
    </template>
  </b-modal>

  </b-container>
</template>

<script>
import NaviHeader from '@/layout/Header2.vue';
import SearchBoard from '../components/board/SearchBoard.vue';
import RegistBoard from '../components/board/RegistBoard.vue';
import DetailBoard from '../components/board/DetailBoard.vue';

export default {
  name: "board",
  components: {
    NaviHeader,
    RegistBoard,
    SearchBoard,
    DetailBoard,
  },
  data() {
      return {
          fields:[{'id':'글번호'}, {'title': '제목'}, {'writer': '작성자'}, {'boarddate': '작성일'}],
          key:'title',
          word:'',
          id:'',
          title:'',
          content:'',
          currentPage : 1,
          perPage: 10
      }

    },
    created(){
      this.$store.dispatch('loadBoard', {
        key : '',
        word : '',
      });
    },
    computed:{
      boards(){
        return this.$store.state.boards;
      },
      rows() {
        return this.$store.state.boards.length;
      }
    },
    methods:{
      search(){
        this.currentPage = 1       
      },
      rowClicked(item){
        this.id = item.id
        this.title = item.title
        this.content = item.content
        this.$refs['boardDetail'].show()
      },
      hideModal() {
        this.$refs['boardDetail'].hide()
      },
      mvFirstPage () {
        this.currentPage = 1
      }
    }
};
</script>

<style scoped>
#form{
    float:right;
}
#select{
    width:100px;
    display:inline-block;
    height:40px;
}

#input{
    width:200px;
    display: inline-block;
    height:40px;
}

#btn{
    height:40px;
}
</style>