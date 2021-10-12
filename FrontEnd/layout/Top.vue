<template>
  <b-container fluid id="top">
    <div id="top2">
        <router-link to="/login" class="link" v-if="!this.$store.state.userId">| 로그인 | </router-link>
        <router-link to="/signUp" class="link" v-if="!this.$store.state.userId">회원가입 | </router-link>
        <span class="mr-3" id="profile" v-if="this.$store.state.userId">{{this.$store.state.userName}}님 환영합니다. </span>
        <router-link to="/manager" class="link" v-if="this.$store.state.userId==='admin'">| 관리하기 | </router-link> 
        <span v-if="this.$store.state.userId !== 'admin'">
          <router-link to="/mypage" class="link" v-if="this.$store.state.userId">| 마이페이지 | </router-link> 
        </span>
        <a href="#" @click="logout" id="a" v-if="this.$store.state.userId">로그아웃 |</a>
    </div>
  </b-container>
</template>


<style scoped>
#top{
  color:rgb(194, 190, 183);
  opacity: 0.9;
  background-color:rgb(31, 29, 29);
  height:30px;
  line-height:30px;
  position:fixed;
}

.link{
    color:rgb(194, 190, 183);
}

#top2{
    margin-right:50px;
    float:right;
}

#a{
  color:rgb(194, 190, 183);
}
</style>


<script>
export default {
  methods: {
    logout(){
      this.$store.dispatch('logout');
      this.$router.push('/');
    }
  },
}
</script>