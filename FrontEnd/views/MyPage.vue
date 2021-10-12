<template>
  <div fluid id="main" style="padding:0;">
    <!-- <navi-header style="z-index:1;"/> -->
    <div style="height:30px;"></div>
    <div style="height:800px;" >
        <b-row>
            <b-col cols="2" id="left">
                <div class="mt-3">
                <img src="@/assets/user.png" style="width:40px;"/>
                <h2 id="name" class="ml-2">{{this.$store.state.userName}}님</h2>
                </div>
                <div style="text-align:left; width:200px; margin:20px auto; border-bottom:1px solid white; font-weight:bold;">
                    <img class="mb-1 ml-2" src="@/assets/fix.png" style="width:25px;"/> 
                    <span class="ml-2" style="font-size:20px;">회원관리</span>
                </div>
                <div class="ml-3"><b-button @click="mvview" pill block variant="primary" style="width:200px;">정보 조회</b-button></div>
                <div class="ml-3 mt-3"><b-button @click="mvmodify" pill block variant="success" style="width:200px;">정보 수정</b-button></div>
                <div class="ml-3 mt-3"><b-button @click="mvdelete" pill block variant="danger" style="width:200px;">회원 탈퇴</b-button></div>
                <div style="text-align:left; width:200px; margin:20px auto; border-bottom:1px solid white; font-weight:bold;">
                    <img class="mb-1 ml-1" src="@/assets/run.png" style="width:35px;"/> 
                    <span class="ml-1" style="font-size:20px;">이동하기</span>
                </div>
                <div class="ml-3 mt-3"><b-button @click="back" pill block variant="warning" style="width:200px;">메인으로</b-button></div>
                <div class="ml-3 mt-3"><b-button @click="logout" pill block variant="light" style="width:200px;">로그아웃</b-button></div>
            </b-col>
            <b-col cols="10" id="main">
                
                <!-- 조회창 -->
                <template v-if="mode==='view'">
                    <div style="height:150px;"></div>
                    <div class="view">
                        <label>ID : </label>
                        <b-form-input class="input" disabled v-model="member.memberid" /><br>
                    </div>
                    <div class="view">
                        <label>Name : </label>
                        <b-form-input class="input" disabled v-model="member.membername"/><br>
                    </div>
                    <div class="view">
                        <label>Pass : </label>
                        <b-form-input class="input" disabled v-model="member.memberpwd"/><br>
                    </div>
                    <div class="view">
                        <label>Email : </label>
                        <b-form-input class="input" disabled v-model="member.memberemail"/><br>
                    </div>
                    <div class="view">
                        <label>Phone : </label>
                        <b-form-input class="input" disabled v-model="member.membertell"/><br>
                    </div>
                </template>

                <!-- 수정창 -->
                <template v-if="mode==='modify'">
                    <div style="height:150px;"></div>
                    <div class="view">
                        <label>ID : </label>
                        <b-form-input class="input" aria-placeholder="" disabled v-model="member.memberid" style="color:red; font-weight:bold;" /><br>
                    </div>
                    <div class="view">
                        <label>Name : </label>
                        <b-form-input class="input"  v-model="member.membername"/><br>
                    </div>
                    <div class="view">
                        <label>Pass : </label>
                        <b-form-input class="input"  v-model="member.memberpwd"/><br>
                    </div>
                    <div class="view">
                        <label>Email : </label>
                        <b-form-input class="input"  v-model="member.memberemail"/><br>
                    </div>
                    <div class="view">
                        <label>Phone : </label>
                        <b-form-input class="input"  v-model="member.membertell"/><br>
                    </div>
                    <div style="margin-top:20px; margin-left:130px;">
                        <b-button @click="update" class="mr-3" style="width:150px;" squared variant="warning">수정하기</b-button>
                        <b-button @click="reset" style="width:150px;" squared variant="danger">초기화</b-button>
                        
                    </div>
                </template>

                <!-- 탈퇴창 -->
                <template v-if="mode==='delete'">
                    <div style="height:250px;"></div>
                    <div>
                        <h1 style="display:inline-block;">정말로 회원을 탈퇴하시겠습니까 ?</h1>
                        <img class="ml-3" src="@/assets/sad.png" style="width:50px; position:relative; top:-10px;"/>
                    </div>
                    <b-button @click="memberDelete" variant="danger" class="mt-3" style="width:200px;">탈퇴하기</b-button>
                    <div style="height:250px;"></div>
                </template>

            </b-col>
        </b-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "mypage",
  components: {
    
  },
  methods:{
      back(){
        this.$router.push('/');
      },
      mvview(){
        this.mode='view';
      },
      mvmodify(){
        this.mode='modify';
      },
      mvdelete(){
        this.mode='delete';
      },
      memberDelete(){
        this.$store.dispatch('memberDelete');
        this.$router.push('/');
      },
      logout(){
        this.$store.dispatch('logout');
        this.$router.push('/');
      },
      reset(){
        const member = this.$store.state.myMember;
  
        this.member.memberid = member.memberid;
        this.member.membername = member.membername;
        this.member.memberpwd = member.memberpwd;
        this.member.memberemail = member.memberemail;
        this.member.membertell = member.membertell;
      },
      update(){
        this.$store.dispatch('updateMember',{
            memberid:this.member.memberid,
            membername:this.member.membername,
            memberpwd:this.member.memberpwd,
            memberemail:this.member.memberemail,
            membertell:this.member.membertell,
        }).then(()=>{
            this.mode='view';

            const member = this.$store.state.myMember;
            
            this.member.memberid = member.memberid;
            this.member.membername = member.membername;
            this.member.memberpwd = member.memberpwd;
            this.member.memberemail = member.memberemail;
            this.member.membertell = member.membertell;
        })
      },
  },
  data(){
      return{
          mode:'view',
          member:{
              memberid:'',
              membername:'',
              memberpwd:'',
              memberemail:'',
              membertell:'',
          }
      }
  },
  computed:{
      myMember(){
          return this.$store.state.myMember;
      }
  },
  created(){
    const member = this.$store.state.myMember;
  
    this.member.memberid = member.memberid;
    this.member.membername = member.membername;
    this.member.memberpwd = member.memberpwd;
    this.member.memberemail = member.memberemail;
    this.member.membertell = member.membertell

  },
};
</script>

<style scoped>
#left{
    height:800px;
    /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#7abcff+0,60abf8+44,4096ee+100;Blue+3D+%2314 */
    background: rgb(122,188,255); /* Old browsers */
    background: -moz-linear-gradient(top,  rgba(122,188,255,1) 0%, rgba(96,171,248,1) 44%, rgba(64,150,238,1) 100%); /* FF3.6-15 */
    background: -webkit-linear-gradient(top,  rgba(122,188,255,1) 0%,rgba(96,171,248,1) 44%,rgba(64,150,238,1) 100%); /* Chrome10-25,Safari5.1-6 */
    background: linear-gradient(to bottom,  rgba(122,188,255,1) 0%,rgba(96,171,248,1) 44%,rgba(64,150,238,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#7abcff', endColorstr='#4096ee',GradientType=0 ); /* IE6-9 */
}

#main{
        /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#4096ee+0,4096ee+100;Blue+Flat+%232 */
    background: rgb(64,150,238); /* Old browsers */
    background: -moz-linear-gradient(top,  rgba(64,150,238,1) 0%, rgba(64,150,238,1) 100%); /* FF3.6-15 */
    background: -webkit-linear-gradient(top,  rgba(64,150,238,1) 0%,rgba(64,150,238,1) 100%); /* Chrome10-25,Safari5.1-6 */
    background: linear-gradient(to bottom,  rgba(64,150,238,1) 0%,rgba(64,150,238,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#4096ee', endColorstr='#4096ee',GradientType=0 ); /* IE6-9 */
    color:black;
}

#name{
    position:relative;
    top:10px;
    display:inline-block;
}

label{
    width:125px;
    display:inline-block;
    font-size:25px;
}

.input{
    width:350px;
    height:50px;
    display:inline-block;
    text-align:center;
}

.view{
    margin:20px;
}
</style>