<template>
  <div>
    <main class="flex-shrink-0">
      <b-jumbotron header>
        <div class="container px-5">
          <div class="row justify-content-center">
            <div class="col-lg-8 col-xxl-6">
              <div class="text-center my-5">
                <h1 class="fw-bolder mb-3 font-weight-bold">오늘의 뉴스</h1>
                <p class="lead fw-normal text-muted mb-4">아파트에 관련된 오늘의 뉴스입니다.</p>
              </div>
            </div>
          </div>
        </div>
      </b-jumbotron>
      <section id="scroll-target">
        <div class="container px-5 my-5">
          <div class="row gx-5 align-items-center">
            <div v-if="newsList.length > 0">
              <div class="row justify-content-end" style="padding: 10px">
                <div class="row col-3">
                  <b-form-select
                    v-model="option"
                    :options="options"
                    style="width: 91%"
                    @change="getNews()"
                  ></b-form-select>
                </div>
              </div>
              <div class="col">
                <b-list-group>
                  <table>
                    <th>제목</th>
                    <th>언론사</th>
                    <th>시간</th>
                    <tr v-for="news in newsList" :key="news.link">
                      <td>
                        <a
                          :href="news.link"
                          target="_blank"
                          class="d-flex justify-content-between"
                          style="color: black"
                        >
                          {{ news.title }}
                        </a>
                      </td>
                      <td>
                        <b-badge variant="primary">{{ news.press }}</b-badge>
                      </td>
                      <td>
                        <b-badge variant="warning">{{ news.time }}</b-badge>
                      </td>
                    </tr>
                  </table>
                </b-list-group>
              </div>
            </div>
            <div v-else>
              <div>뉴스를 불러오지 못했습니다.</div>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      newsList: [],
      options: [
        {
          value: 0,
          text: "선택없음",
        },
        {
          value: 1020,
          text: "동아일보",
        },
        {
          value: 1025,
          text: "중앙일보",
        },
        {
          value: 1023,
          text: "조선일보",
        },
      ],
      option: 0,
    };
  },
  methods: {
    getNews() {
      axios
        .get(`http://localhost:8080/news/${this.option}`)
        .then((res) => {
          this.newsList = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  created() {
    this.getNews();
  },
};
</script>

<style scoped>
table {
  width: 100%;
  text-align: left;
}
table th {
  padding: 12px;
  border-bottom: 2px solid darkgray;
  font-size: large;
}
table td {
  padding: 12px;
}
table tr:nth-of-type(even) {
  background-color: #f0f0f0;
}
</style>
