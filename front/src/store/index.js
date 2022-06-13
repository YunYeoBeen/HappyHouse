import Vue from "vue";
import Vuex from "vuex";
import Http from "@/api/Http";
import createPersistedState from "vuex-persistedstate";
import memberStore from "@/store/modules/memberStore.js";
import Constant from "@/common/Constant";

Vue.use(Vuex);
const store = new Vuex.Store({
  modules: {
    memberStore,
  },
  state: {
    addrCode: "",
    boards: [],
    mbti: {
      // I: 독립성 , E: 사회성
      E: 0,
      // N: 휴식 , S:자아 실형
      N: 0,
      // F: 심미성 , T: 실용성
      T: 0,
    },
    focus: {
      x: "",
      y: "",
    },
    curMarker: {
      x: "",
      y: "",
    },
  },
  mutations: {
    SET_ADDR_CODE(state, payload) {
      state.addrCode = payload;
    },
    [Constant.SET_BOARD_SEARCH](state, payload) {
      state.boards = payload.boards;
    },
    SET_FOCUS(state, payload) {
      state.focus = payload;
    },
    SET_CUR_MARKER(state, payload) {
      state.curMarker = payload;
    },
    //
    SET_MBTI(state, payload) {
      state.mbti = payload;
    },
  },
  actions: {
    setAddrCode(context, payload) {
      context.commit("SET_ADDR_CODE", payload);
      return;
    },
    setFocus(context, payload) {
      context.commit("SET_FOCUS", payload);
      return;
    },
    setCurMarker(context, payload) {
      context.commit("SET_CUR_MARKER", payload);
      return;
    },
    async [Constant.GET_BOARD_SEARCH](context, payload) {
      const { data } = await Http.get(`/board?option=${payload.option}&keyword=${payload.keyword}`);
      context.commit(Constant.SET_BOARD_SEARCH, { boards: data });
    },
    setMbti(context, payload) {
      console.log(payload);
      context.commit("SET_MBTI", payload);
      return;
    },
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
export const strict = false;
