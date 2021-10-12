<template>
  <b-container fluid style="padding:0;">
    <navi-header/>
    <div style="height:130px;"></div>
    <div style="height:600px;">
      <b-row>
        <div class="div">
            <div style="height:200px;"></div>
          <b-form>
            <div>
                <label for="id">ID : </label>
                <b-form-input id="id" class="input" placeholder="아이디" v-model="user.userid"/><br>
            </div>
            <div class="mt-3">
                <label for="pass">Pass : </label>
                <b-form-input id="pass" type="password" class="input" placeholder="비밀번호" v-model="user.userpwd"/><br>
            </div>
            <div class="mt-3">
                <b-button class="mr-1" pill squared variant="outline-primary" @click="login">로그인</b-button>
                <b-button @click="mvMain" pill squared variant="outline-success">메인으로</b-button>
            </div>
            <div class="mt-3">
             아직 회원이 아니라면 ?
             <b-button @click="mvSignUp" pill squared variant="outline-warning">회원가입</b-button>
            </div>
          </b-form>
        </div>
        <div style="width:50%; padding:0;">
          <img src="@/assets/WelcomeHouse1.jpg" style="height:600px; width:100%;"/>
        </div>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import NaviHeader from '@/layout/Header2.vue';

export default {
  name: "Login",
  data(){
    return{
      user:{
        userid:'',
        userpwd:'',
      },
      message:'',
    }
  },
  components: {
    NaviHeader,
  },
  methods:{
      mvSignUp(){
          this.$router.push("/signUp");
      },
      mvMain(){
          this.$router.push("/");
      },
      login(){
        //login 실행
        // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
        this.$store.dispatch("login", this.user)
        .then(()=>{
          if(this.$store.state.userId !== ''){
            this.$router.push('/');
          }
        })
       

        // .then(() => this.$router.replace(`/${this.nextRoute}`))
        // .catch(({ message }) => (this.message = message));
      },
  },
};
</script>

<style scoped>
b-col{
  padding:0;  
  z-index: -1;
}

.div{
    width:50%;
    background-color: whitesmoke;
    font-weight:bold;
}

label{
    width:75px;
}

input{
    display:inline-block;
    width:150px;
}
</style>