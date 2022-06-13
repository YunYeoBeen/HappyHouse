<template>
  <div class="container px-4 px-lg-5 mt-3 mb-3">
    <div class="row gx-4 gx-lg-5 justify-content-center">
      <div class="col-md-10 col-lg-8 col-xl-7">
        <h1 class="mb-5 mt-5 fw-bolder mb-3 font-weight-bold">공지사항</h1>
        <div>
          <h4>제목</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="boardList.title"
            readonly
          />
          <h4>내용</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black; height: 300px"
            v-model="boardList.content"
            readonly
          />
          <h4>작성자</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="boardList.memberId"
            readonly
          />
          <!-- 자유게시판 쓴 아이디랑 로그인한 아이디가 같을 때 -->
          <div v-if="userInfo" class="float-right">
            <div v-if="userInfo.id == 'admin001'">
              <b-button class="btn btn-warning mr-2" @click.prevent="moveModify" size="sm"
                ><b-icon icon="pencil"></b-icon>수정</b-button
              >
              <b-button class="btn btn-warning mr-2" @click.prevent="deleteBoard" size="sm"
                ><b-icon icon="trash"></b-icon>삭제</b-button
              >
              <b-button class="btn btn-warning mr-2" @click="moveHome" size="sm"
                ><b-icon icon="bank2"></b-icon>홈으로</b-button
              >
            </div>
            <div class="float-right" v-else>
              <b-button class="btn btn-warning mr-2" @click="moveHome" size="sm"
                ><b-icon icon="bank2"></b-icon>홈으로</b-button
              >
            </div>
          </div>
          <div class="float-right" v-if="!userInfo">
            <b-button class="btn btn-warning mr-2" @click="moveHome" size="sm"
              ><b-icon icon="bank2"></b-icon>홈으로</b-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Http from "@/api/Http.js";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      boardList: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.no = this.$route.params.no;
    this.getBoard();
  },
  methods: {
    getBoard() {
      Http.get(`/board/${this.no}`).then(({ data }) => (this.boardList = data));
    },
    moveModify() {
      this.$router.push({ name: "boardmodify", params: { no: this.boardList.no } });
    },
    deleteBoard() {
      if (confirm("정말 삭제하시겠습니까?")) {
        Http.delete(`/board/${this.no}`)
          .then(() => {
            this.$alertify.success("공지사항 글 삭제에 성공하였습니다.");
            this.moveList();
          })
          .catch(() => {
            this.$alertify.error("공지사항 글 삭제에 실패하였습니다.");
          });
      }
    },
    moveList() {
      this.$router.push({ name: "board" });
    },
    moveHome() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
b-button {
  size: "sm";
}
</style>
