import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main.vue";
import Login from "@/views/Login.vue";
import SignUp from "@/views/SignUp.vue";
import Intro from "@/views/Intro.vue";
import QnA from "@/views/QnA.vue";
import Board from "@/views/Board.vue";
import MyPage from "@/views/MyPage.vue";
import Manager from "@/views/Manager.vue";
import Deal from "@/views/Deal.vue";
import Test from "@/views/Test.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signUp",
    name: "SignUp",
    component: SignUp,
  },
  {
    path: "/intro",
    name: "Intro",
    component: Intro,
  },
  {
    path: "/qna",
    name: "QnA",
    component: QnA,
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/manager",
    name: "Manager",
    component: Manager,
  },
  {
    path: "/deal",
    name: "Deal",
    component: Deal,
  },
  {
    path: "/test",
    name: "Test",
    component: Test,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;