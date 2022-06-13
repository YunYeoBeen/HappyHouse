<template>
  <section class="py-5">
    <div class="container w-50 mt-5 mx-auto px-4" style="min-width: 400px; max-width: 500px">
      <form id="modify_form" name="modify_form" class="contianer px-4" method="post" action="">
        <div class="d-grid gap-2 mb-4">
          <p class="h3 text-center fw-bold">회원정보 수정</p>
        </div>
        <div class="d-grid gap-2 mb-4">
          <div class="row">
            <div class="col-3 text-end">
              <label for="name">이름</label>
            </div>
            <div class="col-9">
              <input id="name" name="name" type="text" class="form-control" v-model="user.name" />
            </div>
          </div>
          <div class="row">
            <div class="col-3 text-end">
              <label for="phone">전화번호</label>
            </div>
            <div class="col-9">
              <input
                id="phone"
                name="phone"
                type="text"
                class="form-control"
                v-model="user.phone"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-3 text-end">
              <label for="addr1">주소</label>
            </div>
            <div class="col-6">
              <input
                id="addr1"
                name="address"
                type="text"
                class="form-control"
                v-model="user.address"
                readonly
              />
            </div>
            <div class="col-3">
              <button
                @click="searchPost"
                id="addr_search_btn"
                class="btn btn-secondary y w-100"
                type="button"
              >
                검색
              </button>
            </div>
          </div>
          <div class="row">
            <div class="col-3"></div>
            <div class="col-9">
              <input
                id="addr2"
                name="detailAddress"
                type="text"
                class="form-control"
                v-model="user.detailAddr"
              />
            </div>
          </div>
        </div>
        <div class="d-grid gap-2 mb-4">
          <div class="row">
            <div class="col-12">
              <button @click="modify" id="modify_btn" type="button" class="btn btn-dark w-100">
                수정하기
              </button>
            </div>
            <div style="padding: 5px"></div>
            <div class="col-12">
              <button @click="resign" id="delete_btn" type="button" class="btn btn-danger w-100">
                탈퇴하기
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import http from "@/api/Http.js";
const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      user: {
        name: "",
        phone: "",
        address: "",
        detailAddr: "",
        zip: "",
      },
    };
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    modify() {
      if (
        this.user.name === "" ||
        this.user.phone === "" ||
        this.user.detailAddr === "" ||
        this.user.address === ""
      ) {
        alert("입력되지 않은 값이 있습니다");
      } else {
        http
          .put("/user", {
            id: this.userInfo.id,
            pw: this.userInfo.pw,
            name: this.user.name,
            address: this.user.address,
            phone: this.user.phone,
            zonecode: this.user.zip,
            detail_address: this.user.detailAddr,
          })
          .then(() => {
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            alert("수정하였습니다. 다시 로그인 해주세요.");
            this.$router.push("/");
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    resign() {
      http
        .delete("/user/" + this.userInfo.id)
        .then(() => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          alert("탈퇴되었습니다.");
          this.$router.push("/");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchPost() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          this.user.zip = data.zonecode;
          if (data.userSelectedType === "R") {
            this.user.address = data.roadAddress;
          } else {
            this.user.address = data.jibunAddress;
          }
        },
      }).open();
    },
  },
  mounted() {
    console.log(this.userInfo.name);
    this.user.name = this.userInfo.name;
    this.user.phone = this.userInfo.phone;
    this.user.address = this.userInfo.address;
    this.user.detailAddr = this.userInfo.detail_address;
    this.user.zip = this.userInfo.zonecode;
  },
};
</script>

<style></style>
