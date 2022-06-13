<template>
  <div>
    <main class="flex-shrink-0">
      <!-- Header-->
      <b-jumbotron header>
        <div class="container px-5">
          <div class="row justify-content-center">
            <div class="col-lg-8 col-xxl-6">
              <div class="text-center my-5">
                <h1 class="fw-bolder mb-3 font-weight-bold">공지사항</h1>
                <p class="lead fw-normal text-muted mb-4">
                  서비스 이용을 안내하기 위한 공지사항을 게시합니다.
                </p>
              </div>
            </div>
          </div>
        </div>
      </b-jumbotron>
      <!-- About section one-->
      <section id="scroll-target">
        <div class="container px-5 my-5">
          <div class="row" style="padding: 10px">
            <div>
              <b-form-select
                class="mr-2 col-1 mb-2"
                name="search_option"
                v-model="option"
                :options="options"
                style="height: 36px"
              ></b-form-select>
              <input
                class="mr-2 mb-2"
                type="text"
                name="keyword"
                v-model="keyword"
                @keyup.enter.prevent="serachBoard"
                style="height: 33px"
              />
              <button
                class="btn btn-warning mb-2"
                type="button"
                style="height: 36px"
                @click="serachBoard"
              >
                <b-icon icon="search"></b-icon>
              </button>
            </div>
            <b-table
              striped
              hover
              :items="boards"
              :fields="fields"
              @row-clicked="moveDetail"
              :per-page="perPage"
              :current-page="currentPage"
            >
              <template #cell(index)="data">
                {{ data.index + 1 }}
              </template>
            </b-table>
            <div v-if="userInfo">
              <router-link
                :to="{
                  name: 'boardregist',
                }"
                class="btn btn-warning col-1"
                style="margin-left: 965px"
                ><b-icon icon="pencil"></b-icon>작성
              </router-link>
            </div>
            <b-pagination
              class="justify-content-center"
              v-model="currentPage"
              pills
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
            >
            </b-pagination>
          </div>
        </div>
        <!-- 관리자일 때만 글 등록 -->
      </section>
    </main>
  </div>
</template>

<script>
import Constant from "@/common/Constant.js";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "index",
          label: "글번호",
          sortable: true,
        },
        {
          key: "memberId",
          label: "작성자",
          sortable: true,
        },
        {
          key: "title",
          label: "제목",
          sortable: true,
        },
        {
          key: "lastModified",
          label: "작성날짜",
          sortable: true,
        },
        {
          key: "readcnt",
          label: "조회수",
          sortable: true,
        },
      ],
      options: [
        {
          value: null,
          text: "선택",
        },
        {
          value: "id",
          text: "아이디",
        },
        {
          value: "content",
          text: "내용",
        },
      ],
      keyword: "",
      option: null,
      visible: false,
    };
  },
  created() {
    this.serachBoard();
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.boards.length;
    },

    boards() {
      return this.$store.state.boards;
    },
  },
  methods: {
    moveDetail(item) {
      console.log(item.no);
      this.$router.push({
        name: "boarddetail",
        params: { no: item.no },
      });
    },
    serachBoard() {
      this.$store
        .dispatch(Constant.GET_BOARD_SEARCH, { keyword: this.keyword, option: this.option })
        .then(() => {
          this.visible = !this.visible;
        });
    },
  },
};
</script>
