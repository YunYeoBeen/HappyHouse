import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import RegListView from "../views/RegListView.vue";
import NewsListView from "../views/NewsListView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/board/BoardView.vue"),
  },
  {
    path: "/board/detail/:no",
    name: "boarddetail",
    component: () => import("@/views/board/BoardDetailView.vue"),
  },
  {
    path: "/board/regist/",
    name: "boardregist",
    component: () => import("@/views/board/BoardRegistView.vue"),
  },
  {
    path: "/board/modify/:no",
    name: "boardmodify",
    component: () => import("@/views/board/BoardModifyView.vue"),
  },
  {
    path: "/history",
    name: "history",
    component: RegListView,
  },
  {
    path: "/news",
    name: "news",
    component: NewsListView,
  },
  {
    path: "/",
    name: "qnalist",
    component: () => import("@/views/qna/qnaListView.vue"),
  },
  {
    path: "/qna/detail/:id",
    name: "qnadetail",
    component: () => import("@/views/qna/qnaDetailView.vue"),
  },
  {
    path: "/qna/regist",
    name: "qnaregist",
    component: () => import("@/views/qna/qnaRegistView.vue"),
  },
  {
    path: "/qna/modify/:id",
    name: "qnamodify",
    component: () => import("@/views/qna/qnaModifyView.vue"),
  },
  {
    path: "/user/signin",
    name: "signIn",
    component: () => import("@/components/user/MemberLogin.vue"),
  },
  {
    path: "/user/signup",
    name: "signup",
    component: () => import("@/components/user/MemberRegister.vue"),
  },
  {
    path: "/user/mypage",
    name: "mypage",
    component: () => import("@/components/user/MemberMyPage.vue"),
  },
  {
    path: "*",
    name: "404",
    component: () => import("@/components/404.vue"),
  },
  {
    path: "/mbti",
    name: "mbtiView",
    component: () => import("@/views/mbtiView.vue"),
  },
  {
    path: "/mbti/page1",
    name: "page1View",
    component: () => import("@/views/mbti/page1View"),
  },
  {
    path: "/mbti/page2",
    name: "page2View",
    component: () => import("@/views/mbti/page2View"),
  },
  {
    path: "/mbti/page3",
    name: "page3View",
    component: () => import("@/views/mbti/page3View"),
  },
  {
    path: "/mbti/page4",
    name: "page4View",
    component: () => import("@/views/mbti/page4View"),
  },
  {
    path: "/mbti/page5",
    name: "page5View",
    component: () => import("@/views/mbti/page5View"),
  },
  {
    path: "/mbti/page6",
    name: "page6View",
    component: () => import("@/views/mbti/page6View"),
  },
  {
    path: "/mbti/page7",
    name: "page7View",
    component: () => import("@/views/mbti/page7View"),
  },
  {
    path: "/mbti/page8",
    name: "page8View",
    component: () => import("@/views/mbti/page8View"),
  },
  {
    path: "/mbti/page9",
    name: "page9View",
    component: () => import("@/views/mbti/page9View"),
  },
  {
    path: "/mbti/resultpage",
    name: "resultPageView",
    component: () => import("@/views/mbti/resultView"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { x: 0, y: 0 };
    }
  },
});

export default router;
