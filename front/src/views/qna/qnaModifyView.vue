<template>
  <div class="container px-4 px-lg-5 mt-3 mb-3">
    <div class="row gx-4 gx-lg-5 justify-content-center">
      <div class="col-md-10 col-lg-8 col-xl-7">
        <h1 class="mb-4">자유게시판 수정</h1>
        <div>
          <h4>제목</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="qna.title"
            required
          />
          <h4>내용</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black; height: 300px"
            v-model="qna.content"
            required
          />
          <h4>작성자</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="qna.writer"
            readonly
          />
          <!-- 자유게시판 쓴 아이디랑 로그인한 아이디가 같을 때 -->
          <div class="float-right" v-if="userInfo.id == qna.writer || userInfo.id == 'admin001'">
            <b-button class="btn btn-warning mr-2" size="sm" @click.prevent="modifyQna"
              ><b-icon icon="pencil"></b-icon>수정완료</b-button
            >
            <b-button class="btn btn-warning mr-2" size="sm" @click="moveBefore"
              ><b-icon icon="arrow-left"></b-icon>이전</b-button
            >
          </div>
          <div class="float-right" v-else>
            <b-button class="btn btn-warning mr-2" size="sm" @click.prevent="modifyQna"
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
      qna: {
        id: 0,
        writer: "admin001",
        content: "",
        title: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.id = this.$route.params.id;
    this.getQna();
  },
  methods: {
    getQna() {
      Http.get(`/qna/${this.id}`).then(({ data }) => (this.qna = data));
    },
    modifyQna() {
      if (confirm("정말 수정하시겠습니까?")) {
        Http.put(`/qna/${this.qna.id}`, {
          title: this.qna.title,
          content: this.qna.content,
          writer: this.qna.writer,
        }).then(() => {
          this.$alertify.success("자유게시판 글 수정에 성공하였습니다.");
          this.moveList();
        });
      }
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
    moveBefore() {
      this.$router.go(-1);
    },
  },
};
</script>
