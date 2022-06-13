<template>
  <div>
    <main class="flex-shrink-0">
      <!-- Header-->
      <b-jumbotron header>
        <div class="container px-5">
          <div class="row justify-content-center">
            <div class="col-lg-8 col-xxl-6">
              <div class="text-center my-5">
                <h1 class="fw-bolder mb-3 font-weight-bold">자유게시판</h1>
                <p class="lead fw-normal text-muted mb-4">
                  유저들 간의 자유로운 토론을 위한 자유게시판입니다.
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
            <b-table
              striped
              hover
              :items="Qnas"
              :fields="fields"
              @row-clicked="moveDetail"
              :per-page="perPage"
              :current-page="currentPage"
            >
              <template #cell(index)="data">
                {{ data.index + 1 }}
              </template>
            </b-table>
            <router-link
              v-if="userInfo"
              :to="{
                name: 'qnaregist',
              }"
              class="btn btn-warning col-1"
              style="margin-left: 965px"
              ><b-icon icon="pencil" />작성
            </router-link>
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
      </section>
    </main>
  </div>
</template>

<script>
import Http from "@/api/Http";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "writer",
          label: "작성자",
          sortable: true,
        },
        {
          key: "title",
          label: "제목",
          sortable: true,
        },
        {
          key: "writedate",
          label: "작성날짜",
          sortable: true,
        },
        {
          key: "readcnt",
          label: "조회수",
          sortable: true,
        },
        {
          key: "totalComment",
          label: "댓글수",
          sortable: true,
        },
      ],
      Qnas: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.Qnas.length;
    },
  },
  methods: {
    getQnas() {
      Http.get("/qna")
        .then((response) => (this.Qnas = response.data))
        .catch((error) => alert(error));
    },
    moveDetail(item) {
      this.$router.push({
        name: "qnadetail",
        params: { id: item.id },
      });
    },
  },
  created() {
    this.getQnas();
  },
};
</script>
