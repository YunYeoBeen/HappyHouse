<template>
  <div class="container-fluid">
    <div class="row m-3">
      <div class="col-3">
        <b-form-group v-slot="{ ariaDescribedby }" class="mt-3 d-inline">
          <b-form-radio-group
            class="d-inline"
            v-model="selected"
            :options="options"
            :aria-describedby="ariaDescribedby"
            name="searchtype"
            plain
          ></b-form-radio-group>
        </b-form-group>
        <b-form-group v-slot="{ ariaDescribedby }" class="mt-3 d-inline" style="float: right">
          <b-form-radio-group
            class="d-inline"
            v-model="sortoption"
            :options="sortoptions"
            :aria-describedby="ariaDescribedby"
            name="sorttype"
            plain
          ></b-form-radio-group>
        </b-form-group>
        <div class="mt-2 mb-3">
          <h2 class="font-weight-bold mt-3" style="display: inline">거래 정보</h2>
          <b-form-checkbox-group
            size="sm"
            class="mt-3"
            id="checkbox-group-1"
            v-model="badgeSelected"
            :options="badgeOptions"
            name="badges"
            style="float: right"
          ></b-form-checkbox-group>
        </div>
        <div v-if="selected === 'reg'">
          <select-sido></select-sido>
        </div>
        <!-- 아파트별 -->
        <div v-else class="d-grid gap-3 d-sm-flex mb-5">
          <div class="row">
            <div class="col-9">
              <input
                id="aptNameForm"
                name="aptNameForm"
                type="text"
                class="form-control"
                v-model="aptNameForm"
                placeholder="아파트명을 입력하세요."
                @keyup.enter="getListByAptName()"
              />
            </div>
            <div class="col-3">
              <button id="join_btn" class="btn btn-secondary" @click="getListByAptName()">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </div>
        </div>
        <!-- 리스트 -->
        <div v-if="!clicked">
          <ul class="list-group" style="height: 600px; overflow: scroll">
            <li
              v-for="apt in selectedAptList"
              :key="apt.aptCode"
              class="list-group-item list-group-item-action"
              @click="getAptDealList(apt.aptCode, apt.aptName, apt.lat, apt.lng)"
            >
              <h6>
                <span
                  v-b-popover.hover.top="'100m 반경 이내에 편의점이 존재합니다.'"
                  v-if="apt.convStoreCnt > 0"
                  class="badge badge-primary badge-pill mr-1"
                >
                  편세권
                </span>
                <span
                  v-b-popover.hover.top="'200m 반경 이내에 음식점이 50개 이상입니다.'"
                  v-if="apt.restaurantCnt >= 50"
                  class="badge badge-danger badge-pill mr-1"
                >
                  먹세권
                </span>
                <span
                  v-b-popover.hover.top="'200m 반경 이내에 카페가 10개 이상입니다.'"
                  v-if="apt.cafeCnt >= 10"
                  class="badge badge-success badge-pill mr-1"
                >
                  캎세권
                </span>
                <span
                  v-if="apt.distFromSubway != -1"
                  class="badge badge-warning badge-pill"
                  style="float: right"
                  >{{ apt.subwayName }}</span
                >
              </h6>
              <h5 class="font-weight-bold">
                {{ apt.aptName }}
              </h5>
              <div class="dongname">
                {{ apt.dongName }}
              </div>
              <div class="aptyear">
                건설년도: {{ apt.buildYear }}
                <h5 v-if="apt.distFromSubway === -1">
                  <span class="badge badge-warning badge-lg" style="color: #ffc107; float: right">
                    역까지 5km 이상
                  </span>
                </h5>
                <h5 v-else>
                  <span
                    v-if="apt.distFromSubway < 200"
                    class="badge badge-primary badge-lg"
                    style="float: right"
                    >{{ apt.distFromSubway }}m</span
                  >
                  <span
                    v-else-if="apt.distFromSubway < 400"
                    class="badge badge-info"
                    style="float: right"
                    >{{ apt.distFromSubway }}m</span
                  >
                  <span
                    v-else-if="apt.distFromSubway < 600"
                    class="badge badge-success"
                    style="float: right"
                    >{{ apt.distFromSubway }}m</span
                  >
                  <span
                    v-else-if="apt.distFromSubway < 800"
                    class="badge badge-danger"
                    style="float: right"
                    >{{ apt.distFromSubway }}m</span
                  >
                  <span
                    v-else-if="apt.distFromSubway < 1000"
                    class="badge badge-secondary"
                    style="float: right"
                    >{{ apt.distFromSubway }}m</span
                  >
                  <span v-else class="badge badge-dark" style="float: right"
                    >{{ apt.distFromSubway }}m</span
                  >
                </h5>
              </div>
              <div class="aptyear font-weight-bold" style="color: red">
                평균거래가: {{ apt.dealAmountAvg | comma }}만
              </div>
            </li>
          </ul>
        </div>
        <!-- 상세보기 -->
        <div v-else>
          <h3 class="aptdetailName">
            <b-icon icon="arrow-left" @click="goToList"></b-icon> {{ selectedAptName }}
          </h3>
          <div v-if="dealList === ''">
            <h1>거래정보가 없습니다.</h1>
          </div>
          <div v-else style="height: 550px; overflow: scroll">
            <table class="table">
              <tbody v-for="(deal, index) in dealList" :key="deal.no">
                <tr>
                  <th
                    style="text-align: center; line-height: 10"
                    rowspan="4"
                    class="font-weight-bold"
                  >
                    {{ index + 1 }}
                  </th>
                  <td>거래금액:</td>
                  <td>{{ deal.dealAmount }}만</td>
                </tr>
                <tr>
                  <td>전용면적:</td>
                  <td>{{ deal.area }}</td>
                </tr>
                <tr>
                  <td>층수:</td>
                  <td>{{ deal.floor }}</td>
                </tr>
                <tr>
                  <td>최근 거래일:</td>
                  <td>{{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="col-9">
        <kakao-map></kakao-map>
      </div>
    </div>
  </div>
</template>

<script>
import KakaoMap from "@/components/KakaoMap.vue";
import SelectSido from "@/components/SelectSido.vue";
import http from "@/api/Http.js";
export default {
  components: {
    KakaoMap,
    SelectSido,
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data() {
    return {
      clicked: false,
      selected: "reg",
      options: [
        {
          text: "지역별",
          value: "reg",
        },
        {
          text: "아파트별",
          value: "apt",
        },
      ],
      notsortedAptList: [],
      dealList: [],
      aptNameForm: "",
      selectedAptName: "",
      sortoptions: [
        {
          text: "역 거리순",
          value: "subway",
        },
        {
          text: "거래가순",
          value: "price",
        },
      ],
      sortoption: "price",
      badgeOptions: [
        {
          text: "편세권",
          value: "convBadge",
        },
        {
          text: "먹세권",
          value: "restBadge",
        },
        {
          text: "캎세권",
          value: "cafeBadge",
        },
      ],
      badgeSelected: [],
    };
  },
  watch: {
    "$store.state.addrCode": function () {
      this.getListByDongCode();
    },
    selected: function () {
      this.clicked = false;
    },
  },
  computed: {
    aptList() {
      if (this.sortoption === "subway") {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.notsortedAptList.sort((a, b) => {
          return a.distFromSubway - b.distFromSubway;
        });
      } else {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        return this.notsortedAptList.sort((a, b) => {
          return a.dealAmountAvg - b.dealAmountAvg;
        });
      }
    },
    // eslint-disable-next-line vue/return-in-computed-property
    selectedAptList() {
      let list = this.aptList;
      for (let i = 0; i < this.badgeSelected.length; i++) {
        const element = this.badgeSelected[i];
        if (element === "convBadge") {
          list = list.filter((apt) => apt.convStoreCnt > 0);
        }
        if (element === "restBadge") {
          list = list.filter((apt) => apt.restaurantCnt >= 50);
        }
        if (element === "cafeBadge") {
          list = list.filter((apt) => apt.cafeCnt >= 10);
        }
      }
      return list;
    },
  },
  methods: {
    getListByDongCode() {
      this.clicked = false;
      let code = this.$store.state.addrCode;
      if (this.selected === "reg") {
        if (this.dongCode === "") {
          this.$alertify.warning("지역정보 미입력!!");
        } else {
          http
            .get("/apt/dongcode/" + code)
            .then(({ data }) => {
              this.setDealAmountAvg(data);
            })
            .catch((error) => {
              console.log(error);
            });
        }
      } else {
        alert("reg error!!");
      }
    },
    getListByAptName() {
      this.clicked = false;
      if (this.selected === "apt") {
        if (this.dongCode === "") {
          this.$alertify.warning("아파트이름을 입력하세요");
        } else {
          http
            .get("/apt/name/" + this.aptNameForm)
            .then(({ data }) => {
              this.setDealAmountAvg(data);
            })
            .catch((error) => {
              console.log(error);
            });
        }
      } else {
        alert("apt error!!");
      }
    },
    setDealAmountAvg(data) {
      this.notsortedAptList = [];
      data.forEach((element) => {
        let json = element;
        http
          .get("/apt/deal/avg/" + element.aptCode)
          .then((response) => {
            json.dealAmountAvg = response.data;
            this.notsortedAptList.push(json);
          })
          .catch((error) => {
            console.log(error);
          });
      });
    },
    getAptDealList(aptNo, aptName, lat, lng) {
      this.clicked = true;
      this.selectedAptName = aptName;
      http
        .get("/apt/" + aptNo)
        .then(({ data }) => {
          this.dealList = data;
          this.setCurMarker(lng, lat);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setCurMarker(lng, lat) {
      this.$store.dispatch("setCurMarker", {
        x: lng,
        y: lat,
      });
    },
    goToList() {
      this.clicked = false;
    },
  },
  created() {
    this.setCurMarker("", "");
  },
  mounted() {
    if (this.dongCode != "") {
      this.getListByDongCode();
    }
  },
};
</script>
<style scoped>
/* .aptdetailName {
  font-family: "Nanum Brush Script", cursive;
  font-
} */
</style>
