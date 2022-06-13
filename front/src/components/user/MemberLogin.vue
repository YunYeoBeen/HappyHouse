<template>
  <section class="py-5">
    <div class="container w-50 mt-5 mx-auto px-4" style="min-width: 400px; max-width: 500px">
      <b-form id="login_form" name="login_form" class="contianer px-4">
        <div class="d-grid gap-2 mb-4">
          <p class="h3 text-center fw-bold">로그인</p>
        </div>
        <div class="d-grid gap-2 mb-4">
          <div class="row">
            <div class="col-3 text-end">
              <label for="id">아이디</label>
            </div>
            <div class="col-9">
              <input
                class="form-control"
                type="text"
                id="id"
                name="id"
                v-model="user.id"
                required
                autofocus
              />
            </div>
          </div>
          <div class="row">
            <div class="col-3 text-end">
              <label for="pw">비밀번호</label>
            </div>
            <div class="col-9">
              <input class="form-control" type="password" id="pw" name="pw" v-model="user.pw" />
            </div>
          </div>
        </div>
        <div class="d-grid gap-2 mb-4">
          <div class="row">
            <div class="col-12">
              <input
                type="submit"
                value="로그인하기"
                class="btn btn-dark w-100"
                @click.prevent="confirm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-12 text-center">
              계정이 없으신가요?
              <router-link to="/user/signup">회원가입</router-link>
            </div>
          </div>
        </div>
      </b-form>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        id: null,
        pw: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        this.$alertify.success("로그인에 성공하였습니다.");
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      } else {
        this.$alertify.error("로그인에 실패하였습니다.");
      }
    },
    movePage() {
      this.$router.push({ name: "signup" });
    },
  },
};
</script>

<style></style>
