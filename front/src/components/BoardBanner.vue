<template>
  <div class="card w-75">
    <div class="card-header">
      <i class="bi bi-megaphone-fill me-2" style="color: red"></i>
      <span class="fw-bold">공지사항</span>
      <router-link to="/board"><i class="bi bi-plus-circle float-end" /></router-link>
    </div>
    <ul
      class="list-group list-group-flush"
      v-for="(board, index) in boards"
      :key="index"
      @click="moveDetail(board.no)"
    >
      <li class="list-group-item">{{ board.title | maxLength(24, "...") }}</li>
    </ul>
  </div>
</template>

<script>
import Http from "@/api/Http";
export default {
  filters: {
    maxLength(val, length, endstring) {
      if (val.length > length) return val.slice(0, length) + endstring;
      else return val;
    },
  },
  computed: {
    boards() {
      return this.$store.state.boards;
    },
  },
  methods: {
    moveDetail(num) {
      this.$router.push({ name: "boarddetail", params: { no: num } });
    },
    getBoardsTop() {
      Http.get("/board/top").then((res) => {
        this.$store.state.boards = res.data;
      });
    },
  },
  created() {
    this.getBoardsTop();
  },
};
</script>
