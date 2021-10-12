<template>
  <b-container fluid style="padding:0;">
    <navi-header/>
    <div style="height:130px;"></div>
    <div style="height:600px;">
      <b-row>
        <div style="width:50%; padding:0;">
          <img src="@/assets/WelcomeHouse2.jpg" style="height:600px; width:100%;"/>
        </div>
        <div id="div">
          <div style="height:70px;"></div>
          <b-form>
            <span style="color:red; position:relative; margin-left:70px; display:inline-block;"><br>{{checkMsg}}</span><br>
            <div>
                <label for="id">ID : </label>
                <b-form-input id="id" class="input" placeholder="아이디" v-model="id" v-on:keyup="check"/>
            </div>
            <div class="mt-3">
                <label for="name">Name : </label>
                <b-form-input id="name" class="input" placeholder="이름" v-model="name"/><br>
            </div>
            <div class="mt-3">
                <label for="pass">Pass : </label>
                <b-form-input id="pass" type="password" class="input" placeholder="비밀번호" v-model="pwd"/><br>
            </div>
            <div class="mt-3">
                <label for="email">Email : </label>
                <b-form-input id="email" type="email" class="input" placeholder="이메일" v-model="email"/><br>
            </div>
            <div class="mt-3">
                <label for="phone">Phone : </label>
                <b-form-input id="phone" class="input" placeholder="연락처" v-model="tell"/><br>
            </div>
             <div class="mt-3">
                <b-button class="mr-1" pill squared variant="outline-primary" @click="signUp">회원가입</b-button>
                <b-button @click="mvMain" pill squared variant="outline-success">메인으로</b-button>
            </div>
            <div class="mt-3">
                회원이라면 ?
                <b-button @click="mvLogin" pill squared variant="outline-warning">로그인</b-button>
            </div>
          </b-form>
        </div>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import NaviHeader from '@/layout/Header2.vue';

export default {
  name: "SignUp",
  data(){
    return{
      id:'',
      pwd:'',
      name:'',
      tell:'',
      email:'',
      // checkMsg:'',
    }
  },
  components: {
    NaviHeader,
  },
  computed:{
    checkMsg(){
      return this.$store.state.checkMsg;
    }
  },
  methods:{
      mvLogin(){
          this.$router.push("/login");
      },
      mvMain(){
          this.$router.push("/");
      },
      signUp(){
        const member = {
          memberid:this.id,
          membername:this.name,
          memberpwd:this.pwd,
          memberemail:this.email,
          membertell:this.tell,
        };
        this.$store.dispatch('signUp',member);
        this.$router.push('/');
      },
      check(){
        this.$store.dispatch('check',this.id);
        // this.checkMsg = this.$store.state.checkMsg;
      },
  }
};
</script>

<style scoped>
b-col{
  padding:0;
  z-index: -1;
}

#div{
    width:50%;
    background-color: whitesmoke;
}

label{
    width:75px;
}

input{
    display:inline-block;
    width:150px;
}
</style>