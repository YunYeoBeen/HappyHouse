/* eslint-disable prettier/prettier */
<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container px-5">
      <div>
        <img style="margin-right: 0" class="navbar-brand" src="../assets/logo.png" height="50" />
        <router-link to="/" class="navbar-brand"
          ><img style="width: 120px" src="@/assets/logo_name.png"
        /></router-link>
      </div>
      <div id="navbarNav" class="collapse navbar-collapse">
        <ul class="navbar-nav mx-5">
          <li class="nav-item active">
            <router-link to="/board" class="nav-link">공지사항</router-link>
          </li>
          <li class="nav-item active">
            <router-link to="/history" class="nav-link">실거래가 조회</router-link>
          </li>
          <li class="nav-item active">
            <router-link to="/news" class="nav-link">오늘의 뉴스</router-link>
          </li>
          <li class="nav-item active">
            <router-link to="/mbti" class="nav-link">MBTI</router-link>
          </li>
          <li class="nav-item active">
            <router-link :to="{ name: 'qnalist' }" class="nav-link">자유게시판</router-link>
          </li>
        </ul>
      </div>
      <user-button></user-button>
    </div>
  </nav>
</template>

<script>
import UserButton from "@/components/UserButton.vue";
export default {
  components: {
    UserButton,
  },
};
</script>
