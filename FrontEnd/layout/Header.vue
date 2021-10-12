<template>
  <b-container fluid id="navi">
     <div id="top">
        <router-link to="/intro" class="link">소개</router-link> 
        <router-link to="/board" class="link">공지사항</router-link> 
        <router-link to="/deal" class="link">거래정보</router-link> 
        <img src="@/assets/HoonDam_Logo.png" id="img" @click="mvMain" style="padding-right:200px; height:100px;">
        <router-link to="/qna" class="link2">Q&A</router-link> 
    </div>
  </b-container>
</template>

<style scoped>
#navi{
  line-height:100px;
  height:100px;
  position:fixed;
  top:30px;
  border-bottom:0.3px solid rgb(214, 212, 207);
}

#navi:hover{
  background-color:white;
  transition-duration: 0.5s;
}

#navi:hover .link{
  color:black;
}
#navi:hover .link2{
  color:black;
}

.link{
    color:white;
    margin-left:50px;
    font-weight: 600;
    font-size:20px;
    float:left;
}

.link2{
   color:white;
    margin-right:50px;
    font-weight: 600;
    font-size:20px;
    float:right;
}

#top{
    margin-left:0;
    margin-right:50px;
}

#img{
  cursor: pointer;
}
</style>

<script>
export default {
  methods: {
    mvMain(){
      this.$router.push('/');
    }
  },
}
</script>
