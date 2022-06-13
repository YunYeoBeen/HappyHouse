<template>
  <section class="py-5">
    <div class="container w-50 mt-5 mx-auto px-4" style="min-width: 400px; max-width: 500px">
      <form id="join_form" name="join_form" class="container px-4" method="post" action="">
        <div class="d-grid gap-2 mb-4">
          <p class="h3 text-center fw-bold">회원가입</p>
        </div>
        <div class="d-grid gap-2 mb-4">
          <div class="row">
            <div class="col-3 text-end">
              <label for="id"> 아이디 </label>
            </div>
            <div class="col-9">
              <input
                id="id"
                name="id"
                type="text"
                class="form-control"
                data-toggle="tooltip"
                data-placement="left"
                title="Tooltip on left"
                v-model="user.id"
                @blur="idCheck"
                required
                autofocus
                ref="id"
              />
              <span v-if="errorMsg != ''" style="color: red; padding: 3px">{{ errorMsg }}</span>
            </div>
          </div>
          <div class="row">
            <div class="col-3 text-end">
              <label for="pw">비밀번호</label>
            </div>
            <div class="col-9">
              <input id="pw" name="pw" type="password" class="form-control" v-model="user.pw" />
            </div>
          </div>
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
                placeholder="예) 010-1111-2222"
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
                id="addr_search_btn"
                class="btn btn-secondary y w-100"
                type="button"
                @click="searchPost"
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
              <button type="button" id="join_btn" class="btn btn-dark w-100" @click="join">
                가입하기
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
import axios from "axios";
export default {
  name: "MemberRegister",
  data() {
    return {
      user: {
        id: "",
        pw: "",
        name: "",
        phone: "",
        address: "",
        detailAddr: "",
        zip: "",
      },
      errorMsg: "",
    };
  },
  methods: {
    join() {
      if (
        this.user.id === "" ||
        this.user.pw === "" ||
        this.user.name === "" ||
        this.user.phone === "" ||
        this.user.detailAddr === "" ||
        this.user.address === ""
      ) {
        console.log(this.user);
        alert("입력되지 않은 값이 있습니다");
      } else {
        axios
          .post("http://localhost:8080/user", {
            id: this.user.id,
            pw: this.user.pw,
            name: this.user.name,
            address: this.user.address,
            phone: this.user.phone,
            zonecode: this.user.zip,
            detail_address: this.user.detailAddr,
          })
          .then(() => {
            alert("회원가입에 성공하였습니다.");
            this.$router.push("/user/signin");
          })
          .catch((error) => {
            // Todo: 아이디 중복 errorMsg로 처리
            if (error.response.status === 406) {
              alert("아이디 중복!!!");
            } else {
              console.log("error");
            }
          });
      }
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
    idCheck() {
      let strIdLen = this.user.id.length;
      if (strIdLen < 4 || strIdLen > 10) {
        document.getElementById("id").focus();
        // this.$refs.id.focus();
        this.errorMsg = "아이디를 4 ~ 10자로 입력해주세요.";
      } else {
        this.errorMsg = "";
      }
    },
  },
};
</script>

<style></style>
