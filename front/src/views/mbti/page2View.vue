<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q2. 배낭 여행을 떠난 당신 , 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black">
          - 현지에서 만난 사람과 거리낌 없이 친해진다.
        </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black">
          - 혼자 천천히 걸으며 사색을 즐긴다.
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mbti: null,
    };
  },
  methods: {
    select1() {
      this.mbti.E++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page3");
    },
    select2() {
      this.mbti.E--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page3");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
