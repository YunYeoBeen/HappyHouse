<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 10%; margin-right: 10%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">
        Q4. 오랜만에 생활용품을 사기 위해 이케아에 왔다. 하지만 예산 때문에 바른 자세로 업무를 할 수
        있도록 돕는 노트북 거치대와 침대 맡을 따스한 분위기로 바꿔줄 푹신한 쿠션 중 하나만 구매할 수
        있다
      </h1>
      <div class="mbti-font">
        <b-card class="mbticard" style="margin-left: 25%" @click="select1" text-variant="black">
          - 노트북 거치대
        </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" style="margin-left: 25%" @click="select2" text-variant="black">
          - 베개
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
      this.mbti.N--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page5");
    },
    select2() {
      this.mbti.N++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page5");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
