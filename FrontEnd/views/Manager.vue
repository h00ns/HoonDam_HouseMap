<template>
  <div fluid id="main" style="padding:0;">
    <!-- <navi-header style="z-index:1;"/> -->
    <div style="height:30px;"></div>
    <div style="height:1200px;" >
        <b-row>
            <b-col cols="2" id="left">
                <div class="mt-3 ml-1">
                <img src="@/assets/manager.png" style="width:35px;"/>
                <h2 id="name" class="ml-2">관리자페이지</h2>
                </div>
                <div style="text-align:left; width:200px; margin:20px auto; border-bottom:1px solid white; font-weight:bold;">
                    <img class="mb-1 ml-2" src="@/assets/fix.png" style="width:25px;"/> 
                    <span class="ml-2" style="font-size:20px;">회원관리</span>
                </div>
                <div class="ml-4"><b-button style="width:200px;" @click="mvview" pill block variant="primary">회원 목록</b-button></div>
               
                <div style="text-align:left; width:200px; margin:20px auto; border-bottom:1px solid white; font-weight:bold;">
                    <img class="mb-1 ml-1" src="@/assets/run.png" style="width:35px;"/> 
                    <span class="ml-1" style="font-size:20px;">이동하기</span>
                </div>
                <div class="ml-4 mt-3"><b-button style="width:200px;" @click="back" pill block variant="warning">메인으로</b-button></div>
                <div class="ml-4 mt-3"><b-button style="width:200px;" @click="logout" pill block variant="light">로그아웃</b-button></div>
            </b-col>
            <b-col cols="10" id="main">
                <!-- 검색 창-->
                <div class="mt-3" style="text-align:left; margin-left:50px;">
                    <b-input-group style="width:300px" class="mt-3">
                        
                        <b-form-input @keyup.enter="search" v-model="word" placeholder="이름으로 검색"/>
                        <b-input-group-append>
                        <b-button @click="search" variant="warning">검색</b-button>
                        </b-input-group-append>
                    </b-input-group>
                </div>

                <!-- 회원 목록 -->
                <template v-if="mode==='view'" >
                    <div class="memberTable" v-for="(item,idx) in memberList" :key="idx">
                        <div style="height:70px; text-align:left;">
                            <img class="ml-5 mt-2" src="@/assets/user.png" style="width:45px"/>
                            <span class="ml-2" style="position:relative; top:13px; font-weight:bold; font-size:30px;">{{item.memberid}}</span>
                            <img @click="remove(item.memberid)" src="@/assets/trash.png" style="cursor:pointer; width:40px; float:right; margin-top:10px; margin-right:20px;"/>
                        </div>

                        <div style="height:70px;">
                            <b-row class="ml-5 mr-5">
                                <b-col class="section">
                                    <span class="txt">{{item.membername}}</span>
                                    <img class="img" src="@/assets/id.png"/>
                                </b-col>
                                <b-col class="ml-3 section">
                                    <span class="txt">{{item.memberpwd}}</span>
                                    <img class="img" src="@/assets/pwd.png"/>
                                </b-col>
                                <b-col class="ml-3 section">
                                    <span class="txt">{{item.memberemail}}</span>
                                    <img class="img" src="@/assets/email.png"/>
                                </b-col>
                                <b-col class="ml-3 section">
                                    <span class="txt">{{item.membertell}}</span>
                                    <img class="img" src="@/assets/phone.png"/>
                                </b-col>
                            </b-row>
                        </div>
                       
                    </div>
                </template>
                

            </b-col>
        </b-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "manager",
  components: {

  },
  methods:{
      back(){
        this.$router.push('/');
      },
      mvview(){
        this.mode='view';
      },
      logout(){
        this.$store.dispatch('logout');
        this.$router.push('/');
      },
      search(){
          this.$store.dispatch('searchMember',this.word);
      },
      remove(memberid){
          this.$store.dispatch('memberRemove', memberid).then(()=>{
              this.$store.dispatch('loadMemberList');
          });
      },
  },
  data(){
      return{
          mode:'view',
          word:'',
      }
  },
  computed:{
      memberList(){
          return this.$store.state.memberList;
      }
  },
  created(){
      this.$store.dispatch('loadMemberList');
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
    width:1580px;
        /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#4096ee+0,4096ee+100;Blue+Flat+%232 */
    background: rgb(64,150,238); /* Old browsers */
    background: -moz-linear-gradient(top,  rgba(64,150,238,1) 0%, rgba(64,150,238,1) 100%); /* FF3.6-15 */
    background: -webkit-linear-gradient(top,  rgba(64,150,238,1) 0%,rgba(64,150,238,1) 100%); /* Chrome10-25,Safari5.1-6 */
    background: linear-gradient(to bottom,  rgba(64,150,238,1) 0%,rgba(64,150,238,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#4096ee', endColorstr='#4096ee',GradientType=0 ); /* IE6-9 */
    color:black;
    overflow-y:scroll;
    overflow-x:hidden;
}

#name{
    position:relative;
    top:10px;
    display:inline-block;
    font-size:30px;
    font-weight: bold;
}

.memberTable{
    height:150px;
    width:1200px;
    margin:20px auto;
    border:2px solid white;
    border-radius: 50px;
    text-align:left;
    box-shadow: 10px 10px 10px black;
}

.section{
    background-color:white;
    height:65px;
    border-radius: 50px;
    line-height:65px;
    float:left;
    box-shadow: 10px 10px 10px black;
}

.txt{
    margin-left:10px;
    font-weight:bold;
    font-size:18px;
    
}

.img{
    width:35px;
    margin-top:15px;
    float:right;
}
</style>