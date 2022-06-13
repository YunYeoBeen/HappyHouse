<template>
  <div class="d-grid d-sm-flex gap-3 mb-5">
    <select name="sido" id="sido" class="form-select" v-model="sidoCode">
      <option value="">시/도</option>
    </select>
    <select name="sigugun" id="sigugun" class="form-select" v-model="sigugunCode">
      <option value="">시/군/구</option>
    </select>
    <select name="dong" id="dong" class="form-select" v-model="dongCode">
      <option value="">동/읍/면</option>
    </select>
    <button
      type="submit"
      id="addr_search_btn"
      name="addr_search_btn"
      class="btn btn-secondary y w-25"
      @click="startSearch"
    >
      <i class="bi bi-search"></i>
    </button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      sidoCode: "",
      sigugunCode: "",
      dongCode: "",
      addrName: "",
    };
  },
  methods: {
    getSido() {
      this.getSidoOptions();
    },
    clearElements(id) {
      const element = document.getElementById(id);
      while (element.hasChildNodes()) {
        element.removeChild(element.firstChild);
      }
      var defaultOp = document.createElement("option");
      defaultOp.setAttribute("value", "");
      if (id === "sigugun") {
        defaultOp.innerHTML = "시/군/구";
        this.sigugunCode = "";
        this.dongCode = "";
      } else if (id === "dong") {
        defaultOp.innerHTML = "동/읍/면";
        this.dongCode = "";
      }
      element.appendChild(defaultOp);
    },
    async getSigugun() {
      await this.clearElements("sigugun");
      await this.clearElements("dong");
      this.getSigugunOptions();
    },
    async getDong() {
      await this.clearElements("dong");
      this.getDongOptions();
    },
    getSidoOptions() {
      return axios
        .get(
          "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000",
        )
        .then((response) => {
          response.data.regcodes.forEach((data) => {
            var option = document.createElement("option");
            option.setAttribute("value", data.code[0] + data.code[1]);
            option.innerHTML = data.name;
            document.getElementById("sido").appendChild(option);
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getSigugunOptions() {
      if (this.sidoCode != "") {
        return axios
          .get(
            `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${this.sidoCode}*000000&is_ignore_zero=true`,
          )
          .then((response) => {
            response.data.regcodes.forEach((data) => {
              var option = document.createElement("option");
              option.setAttribute("value", data.code[2] + data.code[3]);
              const arr = data.name.split(" ");
              option.innerHTML = arr[1];
              document.getElementById("sigugun").appendChild(option);
            });
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    getDongOptions() {
      if (this.sigugunCode != "") {
        return axios
          .get(
            `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${this.sidoCode}${this.sigugunCode}*&is_ignore_zero=true`,
          )
          .then((response) => {
            response.data.regcodes.forEach((data) => {
              var option = document.createElement("option");
              option.setAttribute("value", data.code.slice(4));
              const arr = data.name.split(" ");
              var dongName = "";
              for (let i = 2; i < arr.length; i++) {
                dongName += arr[i];
                dongName += " ";
              }
              option.innerHTML = dongName;
              document.getElementById("dong").appendChild(option);
            });
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    startSearch() {
      if (this.sidoCode === "" || this.sigugunCode === "" || this.dongCode === "") {
        this.$alertify.warning("옵션을 전부 입력하세요!");
      } else {
        this.$store
          .dispatch("setAddrCode", this.sidoCode + this.sigugunCode + this.dongCode)
          .then(() => {
            axios
              .get(
                "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=" +
                  (this.sidoCode + this.sigugunCode + this.dongCode),
              )
              .then(({ data }) => {
                axios
                  .get("http://localhost:8080/map/loc/" + data.regcodes[0].name)
                  .then(({ data }) => {
                    this.$store.dispatch("setFocus", data);
                  })
                  .catch(() => {
                    console.log("focus찾기에서 문제발생");
                  });
              });
            if (this.$route.path === "/") {
              this.$router.push("/history");
            }
          });
      }
    },
  },
  created() {
    const addr = this.$store.state.addrCode;
    if (addr != "") {
      this.sidoCode = addr.slice(0, 2);
      this.sigugunCode = addr.slice(2, 4);
      this.dongCode = addr.slice(4);
    }
    if (this.$store.state.addrName != "") {
      this.addrName = this.$store.state.addrName;
    }
  },
  mounted() {
    if (this.sidoCode != "") {
      this.getSidoOptions().then(() => {
        let sidos = document.getElementById("sido").options;
        Array.prototype.forEach.call(sidos, (sido) => {
          if (sido.value === this.sidoCode) {
            sido.selected = true;
          }
        });
      });
      this.getSigugunOptions().then(() => {
        let siguguns = document.getElementById("sigugun").options;
        Array.prototype.forEach.call(siguguns, (sigugun) => {
          if (sigugun.value === this.sigugunCode) {
            sigugun.selected = true;
          }
        });
      });
      this.getDongOptions().then(() => {
        let dongs = document.getElementById("dong").options;
        Array.prototype.forEach.call(dongs, (dong) => {
          if (dong.value === this.dongCode) {
            dong.selected = true;
          }
        });
      });
    } else {
      this.getSido();
    }
    document.getElementById("sido").onchange = this.getSigugun;
    document.getElementById("sigugun").onchange = this.getDong;
  },
};
</script>
