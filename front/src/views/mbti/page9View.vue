<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q9. 기대하던 영화가 개봉하면, 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black">
          - 빨리 극장으로 달려가 영화를 보고 내용을 분석하다.
        </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black">
          - 친구들과 약속을 잡은 뒤, 함께 영화를 보고 이야기를 나눈다.
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
      this.mbti.E--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/resultpage");
    },
    select2() {
      this.mbti.E++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/resultpage");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
