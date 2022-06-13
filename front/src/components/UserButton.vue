<template>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
      <span v-if="id != ''" style="color: white; padding: 15px">{{ name }}님</span>
      <span v-if="userInfo" style="color: white; padding: 15px">{{ userInfo.name }}님</span>
      <li class="nav-item dropdown">
        <b-dropdown size="lg" variant="dark" toggle-class="text-decoration-none">
          <template #button-content>
            <i class="h3 bi bi-person-circle"></i>
          </template>

          <b-dropdown-item v-if="userInfo"
            ><router-link to="/user/mypage">내 정보관리</router-link></b-dropdown-item
          >
          <b-dropdown-item v-if="userInfo" @click.prevent="onClickLogout"
            ><a href="#">로그아웃</a></b-dropdown-item
          >
          <b-dropdown-item v-else>
            <router-link :to="{ name: 'signIn' }">로그인</router-link></b-dropdown-item
          >
        </b-dropdown>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      id: "",
      name: "",
    };
  },
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      this.$alertify.success("로그아웃하였습니다");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    logout() {
      this.id = "";
      this.name = "";
    },
  },
};
</script>

<style></style>
