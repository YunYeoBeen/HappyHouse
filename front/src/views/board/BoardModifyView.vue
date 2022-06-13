<template>
  <div class="container px-4 px-lg-5 mt-3 mb-3">
    <div class="row gx-4 gx-lg-5 justify-content-center">
      <div class="col-md-10 col-lg-8 col-xl-7">
        <h1 class="mb-4">공지사항 수정</h1>
        <div>
          <h4>제목</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="boardList.title"
            required
          />
          <h4>내용</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black; height: 300px"
            v-model="boardList.content"
            required
          />
          <h4>작성자</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="boardList.memberId"
            readonly
          />
          <!-- 자유게시판 쓴 아이디랑 로그인한 아이디가 같을 때 -->
          <div class="float-right">
            <b-button class="btn btn-warning mr-2" size="sm" @click.prevent="modifyBoard"
              ><b-icon icon="pencil"></b-icon>수정완료</b-button
            >
            <b-button class="btn btn-warning mr-2" size="sm" @click="moveBefore"
              ><b-icon icon="arrow-left"></b-icon>이전</b-button
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
      boardList: {
        no: 0,
        memberId: "",
        content: "",
        title: "",
      },
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
    async modifyBoard() {
      if (confirm("정말 수정하시겠습니까?")) {
        await Http.put(`/board/${this.no}`, {
          title: this.boardList.title,
          content: this.boardList.content,
        })
          .then(() => {
            this.$alertify.success("공지사항 글 수정에 성공하였습니다.");
            this.moveList();
          })
          .catch(() => {
            this.$alertify.error("공지사항 글 수정에 실패하였습니다.");
          });
      }
    },
    moveList() {
      this.$router.push({ name: "board" });
    },
    moveHome() {
      this.$router.go({ name: "home" });
    },
    moveBefore() {
      this.$router.go(-1);
    },
  },
};
</script>
