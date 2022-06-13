<template>
  <div class="container px-4 px-lg-5 mt-3 mb-3">
    <div class="row gx-4 gx-lg-5 justify-content-center">
      <div class="col-md-10 col-lg-8 col-xl-7">
        <h1 class="mb-5 mt-5 fw-bolder mb-3 font-weight-bold">자유게시판</h1>
        <div>
          <h4>제목</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="qnaList.title"
            readonly
          />
          <h4>내용</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black; height: 300px"
            v-model="qnaList.content"
            readonly
          />
          <h4>작성자</h4>
          <b-form-input
            class="my-3 p-3"
            style="border: 1px solid black"
            v-model="qnaList.writer"
            readonly
          />
          <!-- 자유게시판 쓴 아이디랑 로그인한 아이디가 같을 때 -->
          <div v-if="userInfo" class="row">
            <div v-if="userInfo.id == qnaList.writer || userInfo.id == 'admin001'">
              <b-button
                class="btn btn-warning mr-2 float-right"
                size="sm"
                @click.prevent="moveModify"
                ><b-icon icon="pencil"></b-icon>수정</b-button
              >
              <b-button
                class="btn btn-warning mr-2 float-right"
                size="sm"
                @click.prevent="deleteQna"
                ><b-icon icon="trash"></b-icon>삭제</b-button
              >
              <b-button class="btn btn-warning mr-2 float-right" size="sm" @click="moveList"
                ><b-icon icon="card-list"></b-icon>목록</b-button
              >
            </div>
            <div v-else>
              <b-button class="btn btn-warning mr-2 float-right" size="sm" @click="moveList"
                ><b-icon icon="card-list"></b-icon>목록</b-button
              >
            </div>
          </div>
          <div v-else>
            <b-button class="btn btn-warning mr-2 float-right" size="sm" @click="moveList"
              ><b-icon icon="card-list"></b-icon>목록</b-button
            >
            <b-button class="btn btn-warning mr-2 float-right" size="sm" @click="moveHome"
              ><b-icon icon="bank2"></b-icon>홈으로</b-button
            >
          </div>
        </div>

        <b-list-group class="mt-3">
          <b-list-group-item v-for="(cmt, index) in commentList" :key="index">
            <b-row>
              <b-col cols="2">
                <b-badge>{{ cmt.writer }}</b-badge>
              </b-col>
              <b-col cols="6">
                <span style="white-space: pre"> {{ cmt.content }}</span>
              </b-col>
              <b-col cols="2">
                <small class="text-muted"> {{ cmt.writedate }}</small>
              </b-col>
              <!-- 로그인한 아이디가 댓글 쓴 아이디랑 같을 때 -->
              <b-col cols="2" v-if="userInfo.id == cmt.writer || userInfo.id == 'admin001'">
                <b-button-group class="float-right" size="sm">
                  <b-btn variant="btn btn-warning mr-1" @click="mdModCmtOpen(cmt)"
                    ><b-icon icon="pencil"
                  /></b-btn>
                  <b-btn variant="btn btn-danger" @click="commentDelete(cmt.cno)"
                    ><b-icon icon="trash"
                  /></b-btn>
                </b-button-group>
              </b-col>
            </b-row>
          </b-list-group-item>
          <b-list-group-item>
            <span> 새 댓글 작성 </span>
            <b-form-input
              class="mb-3"
              type="text"
              v-model="commentContent"
              placeholder="내용을 입력하세요."
              @keyup.enter.native="commentInsert"
            />
            <b-button-group class="float-right" size="sm">
              <b-btn variant="btn btn-warning" @click="commentInsert"
                ><b-icon icon="pencil" />등록</b-btn
              >
            </b-button-group>
          </b-list-group-item>
        </b-list-group>
        <b-modal ref="mdModCmt" title="댓글 수정하기">
          <b-form @submit.prevent="commentUpdate">
            <b-form-group label="내용" label-for="cmtcontent">
              <b-form-textarea
                id="cmtcontent"
                placeholder="내용을 입력하세요"
                v-model="formCmt.content"
                v-on:keyup.enter="commentUpdate"
              ></b-form-textarea>
            </b-form-group>
            <b-btn type="submit" variant="warning" class="float-right"
              ><b-icon icon="pencil"></b-icon>글 수정</b-btn
            >
          </b-form>
        </b-modal>
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
      qnaList: {},
      commentList: {},
      id: 0,
      commentContent: "",
      modifycontent: "",
      visible: true,
      formCmt: {
        cno: "",
        content: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.Qnas.length;
    },
  },
  created() {
    this.id = this.$route.params.id;
    this.bno = this.$route.params.id;
    this.getQna();
    this.getComment();
  },
  methods: {
    mdModCmtOpen(v) {
      this.formCmt.cno = v.cno;
      this.formCmt.content = v.content;
      this.$refs.mdModCmt.show();
    },
    getQna() {
      Http.get(`/qna/${this.id}`).then(({ data }) => (this.qnaList = data));
    },
    moveModify() {
      this.$router.push({ name: "qnamodify", params: { id: this.qnaList.id } });
    },
    deleteQna() {
      if (confirm("정말 삭제하시겠습니까?")) {
        Http.delete(`/qna/${this.id}`)
          .then(() => {
            this.$alertify.success("자유게시판 글 삭제에 성공하였습니다.");
            this.moveList();
          })
          .catch(() => {
            this.$alertify.error("자유게시판 글 삭제에 실패하였습니다.");
          });
      }
    },
    getComment() {
      Http.get(`/comment/${this.bno}`).then(({ data }) => (this.commentList = data));
      this.$emit("total", this.qnaList.totalComment);
    },
    commentInsert() {
      if (confirm("댓글을 등록하시겠습니까? ")) {
        Http.post("/comment", {
          bno: this.bno,
          content: this.commentContent,
          writer: this.userInfo.id,
        })
          .then(() => {
            this.$alertify.success("댓글을 등록했습니다.");
            this.commentContent = "";
            this.getComment();
          })
          .catch();
      }
    },
    commentDelete(cno) {
      if (confirm("정말 삭제하시겠습니까?")) {
        Http.delete(`/comment/${cno}`).then(() => {
          this.$alertify.success("댓글 삭제에 성공하였습니다.");
          this.getComment();
        });
      }
    },
    commentUpdate() {
      if (confirm("정말 수정하시겠습니까?")) {
        Http.put(`/comment/${this.formCmt.cno}`, {
          content: this.formCmt.content,
        }).then(() => {
          console.log(this.modifycontent);
          this.$alertify.success("댓글 수정에 성공하였습니다.");
          this.refresh();
        });
      }
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
    moveHome() {
      this.$router.go(-1);
    },
    refresh() {
      this.$router.go();
    },
    toggle() {
      this.visible = !this.visible;
    },
  },
};
</script>
