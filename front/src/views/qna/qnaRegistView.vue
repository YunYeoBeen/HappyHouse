<template>
  <div class="container px-4 px-lg-5 mt-3 mb-3">
    <div class="row gx-4 gx-lg-5 justify-content-center">
      <div class="col-md-10 col-lg-8 col-xl-7">
        <h1 class="mb-5 mt-5 fw-bolder mb-3 font-weight-bold">자유게시판 글 등록</h1>
        <h4>제목</h4>
        <b-form-textarea
          id="title"
          name="title"
          class="col-md-12 my-3 p-3"
          style="border: 1px solid black"
          v-model="title"
          required="required"
        ></b-form-textarea>
        <h4>내용</h4>
        <b-form-textarea
          id="content"
          name="content"
          class="col-md-12 my-3 p-3"
          style="border: 1px solid black; height: 300px"
          v-model="content"
          required="required"
        ></b-form-textarea>
        <div class="float-right">
          <b-button class="btn btn-warning mr-2" @click="registQna"
            ><b-icon icon="pencil"></b-icon>등록</b-button
          >
          <b-button class="btn btn-warning mr-2" @click="moveList"
            ><b-icon icon="card-list"></b-icon>목록</b-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Http from "@/api/Http";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  data() {
    return {
      title: "",
      content: "",
      writer: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    registQna() {
      Http.post("/qna", {
        title: this.title,
        content: this.content,
        writer: this.userInfo.id,
      })
        .then(() => {
          this.$alertify.success("자유게시판 글 등록에 성공하였습니다.");
          this.moveList();
        })
        .catch(() => {
          console.log(this.content, this.writer, this.title);
          this.$alertify.warning("자유게시판 글 등록에 실패하였습니다.");
        });
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>
