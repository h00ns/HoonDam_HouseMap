<template>
  <b-container fluid id="main" style="padding:0;">
    <navi-header style="z-index:1;"/>
    <div style="height:200px;"></div>
    <!-- height:480px; 이 중앙-->
    <b-row>
      <b-col cols="4"></b-col>
      <b-col cols="4">
      <b-form>
        <b-form-input id="input" v-model="word" placeholder="어느지역의 집을 찾으세요 ?"/>
        <b-button variant="primary" @click="search" id="btn">검색</b-button>
      </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import NaviHeader from '@/layout/Header.vue';

export default {
  name: "main",
  components: {
    NaviHeader,
  },
  data(){
    return{
      word:'',
    }
  },
  methods:{
    search(){
      this.$router.push('/deal');
    },
  }
};
</script>

<style scoped>
#main{
    height:880px;
    background-image: url(../assets/mainhouse.jpg);
    background-size: 100%;
    background-repeat: no-repeat;
}

#input{
  display:inline-block;
  width:88%;
  height:50px;
  z-index: -1;
}

#btn{
  height:50px;
}
</style>