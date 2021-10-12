<template>
  <b-container fluid id="main" style="padding:0;">
    <navi-header style="z-index:100;"/>
    <div id="intro">
        <div style="height:150px;"></div>
        <div class="mt-3"><h1>HoonDam은 이렇게 노력했습니다.</h1></div>
          <div id="body">
            <div id="canvas">
              <div id="rotate">
                <div class="face">
                  <div class="cell" id="c1"></div>
                  <div class="cell" id="c2"></div>
                  <div class="cell" id="c3"></div>
                  <div class="cell" id="c4"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c5"></div>
                  <div class="cell" id="c6"></div>
                  <div class="cell" id="c7"></div>
                  <div class="cell" id="c8"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c9"></div>
                  <div class="cell" id="c10"></div>
                  <div class="cell" id="c11"></div>
                  <div class="cell" id="c12"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c13"></div>
                  <div class="cell" id="c14"></div>
                  <div class="cell" id="c15"></div>
                  <div class="cell" id="c16"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c17"></div>
                  <div class="cell" id="c18"></div>
                  <div class="cell" id="c19"></div>
                  <div class="cell" id="c20"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c21"></div>
                  <div class="cell" id="c22"></div>
                  <div class="cell" id="c23"></div>
                  <div class="cell" id="c24"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c25"></div>
                  <div class="cell" id="c26"></div>
                  <div class="cell" id="c27"></div>
                  <div class="cell" id="c28"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c29"></div>
                  <div class="cell" id="c30"></div>
                  <div class="cell" id="c31"></div>
                  <div class="cell" id="c32"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c33"></div>
                  <div class="cell" id="c34"></div>
                  <div class="cell" id="c35"></div>
                  <div class="cell" id="c36"></div>
                </div>
                <div class="face">
                  <div class="cell" id="c37"></div>
                  <div class="cell" id="c38"></div>
                  <div class="cell" id="c39"></div>
                  <div class="cell" id="c40"></div>
                </div>
              </div>
          </div>
        </div>
    </div>
  </b-container>
</template>

<script>
import NaviHeader from '@/layout/Header2.vue';

export default {
  name: "intro",
  components: {
    NaviHeader,
  },
};
</script>

<style scoped>

        * { margin: 0; padding: 0 }
        #body { height:100%; }

        #body {
            height:800px;
            width: 600px;
            margin: 0 auto;
        }

        #canvas  {
            position: relative;
            width: 100%; height: 200px;
            -webkit-perspective: 1500px;
            perspective: 1500px;
        }

        #rotate {
            position: absolute;
            left: 50%; top: 30px;

            transform-style: preserve-3d;
            animation: rint 15s infinite linear;
        }

        @keyframes rint 
        {
            from { transform: rotateY(0deg); }
            to { transform: rotateY(360deg); }
        }

        .face {
            position: absolute;
            left: 0; top: 0;
            margin-left: -115px;

            backface-visibility: hidden;
            transform-style: preserve-3d;
        }

        .cell {
            width: 230px; height: 150px;
            margin-bottom: 5px;
            background: url(../assets/WelcomeHouse1.jpg);
            background-size: 100% 100%;

            transition-duration: 0.5s;
        }

        #c1{
          background: url(../assets/intro/1.png);
          background-size: 100% 100%;
        }
        #c2{
          background: url(../assets/intro/2.jpg);
          background-size: 100% 100%;
        }
        #c3{
          background: url(../assets/intro/11.jpg);
          background-size: 100% 100%;
        }
        #c4{
          background: url(../assets/intro/5.png);
          background-size: 100% 100%;
        }
        #c5{
          background: url(../assets/intro/4.png);
          background-size: 100% 100%;
        }
        #c6{
          background: url(../assets/intro/8.jpg);
          background-size: 100% 100%;
        }
        #c7{
          background: url(../assets/intro/7.png);
          background-size: 100% 100%;
        }
        #c8{
          background: url(../assets/intro/6.png);
          background-size: 100% 100%;
        }
        #c9{
          background: url(../assets/intro/9.jpg);
          background-size: 100% 100%;
        }
        #c10{
          background: url(../assets/intro/10.jpg);
          background-size: 100% 100%;
        }
        #c11{
          background: url(../assets/intro/18.jpg);
          background-size: 100% 100%;
        }
        #c12{
          background: url(../assets/intro/12.jpg);
          background-size: 100% 100%;
        }
        #c13{
          background: url(../assets/intro/13.jpg);
          background-size: 100% 100%;
        }
        #c14{
          background: url(../assets/intro/14.jpg);
          background-size: 100% 100%;
        }
        #c15{
          background: url(../assets/intro/15.jpg);
          background-size: 100% 100%;
        }
        #c16{
          background: url(../assets/intro/16.jpg);
          background-size: 100% 100%;
        }
        #c17{
          background: url(../assets/intro/17.jpg);
          background-size: 100% 100%;
        }
        #c18{
          background: url(../assets/intro/11.jpg);
          background-size: 100% 100%;
        }
        #c19{
          background: url(../assets/intro/19.jpg);
          background-size: 100% 100%;
        }
        #c20{
          background: url(../assets/intro/20.png);
          background-size: 100% 100%;
        }
        #c21{
          background: url(../assets/intro/21.jpg);
          background-size: 100% 100%;
        }
        #c22{
          background: url(../assets/intro/22.jpg);
          background-size: 100% 100%;
        }
        #c23{
          background: url(../assets/intro/23.jpg);
          background-size: 100% 100%;
        }
        #c24{
          background: url(../assets/intro/24.jpg);
          background-size: 100% 100%;
        }
        #c25{
          background: url(../assets/intro/25.jpg);
          background-size: 100% 100%;
        }
        #c26{
          background: url(../assets/intro/26.jpg);
          background-size: 100% 100%;
        }
        #c27{
          background: url(../assets/intro/27.jpg);
          background-size: 100% 100%;
        }
        #c28{
          background: url(../assets/intro/28.jpg);
          background-size: 100% 100%;
        }
        #c29{
          background: url(../assets/intro/29.jpg);
          background-size: 100% 100%;
        }
        #c30{
          background: url(../assets/intro/30.jpg);
          background-size: 100% 100%;
        }
        #c31{
          background: url(../assets/intro/31.jpg);
          background-size: 100% 100%;
        }
        #c32{
          background: url(../assets/intro/32.jpg);
          background-size: 100% 100%;
        }
        #c33{
          background: url(../assets/intro/33.jpg);
          background-size: 100% 100%;
        }
        #c34{
          background: url(../assets/intro/34.jpg);
          background-size: 100% 100%;
        }
        #c35{
          background: url(../assets/intro/35.jpg);
          background-size: 100% 100%;
        }
        #c36{
          background: url(../assets/intro/36.jpg);
          background-size: 100% 100%;
        }
        #c37{
          background: url(../assets/intro/37.jpg);
          background-size: 100% 100%;
        }
        #c38{
          background: url(../assets/intro/38.jpg);
          background-size: 100% 100%;
        }
        #c39{
          background: url(../assets/intro/39.jpg);
          background-size: 100% 100%;
        }
        #c40{
          background: url(../assets/intro/40.jpg);
          background-size: 100% 100%;
        }
                
        .face:nth-child(1) { transform: rotateY(  0deg) translateZ(-370px); }
        .face:nth-child(2) { transform: rotateY( 36deg) translateZ(-370px); }
        .face:nth-child(3) { transform: rotateY( 72deg) translateZ(-370px); }
        .face:nth-child(4) { transform: rotateY(108deg) translateZ(-370px); }
        .face:nth-child(5) { transform: rotateY(144deg) translateZ(-370px); }
        .face:nth-child(6) { transform: rotateY(180deg) translateZ(-370px); }
        .face:nth-child(7) { transform: rotateY(216deg) translateZ(-370px); }
        .face:nth-child(8) { transform: rotateY(252deg) translateZ(-370px); }
        .face:nth-child(9) { transform: rotateY(288deg) translateZ(-370px); }
        .face:nth-child(10){ transform: rotateY(324deg) translateZ(-370px); }

        .cell:hover {
            transform: scale(1.3) translateZ(50px);
        }

    </style>