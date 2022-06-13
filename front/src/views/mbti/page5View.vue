<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q5. 새 집을 구하고 가구를 구매하는 당신, 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black">
          - 본래 목적에 충실하게! 성능이다.
        </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black">
          - 보기 좋은 떡이 맛도 좋다! 디자인이다.
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
      this.mbti.T++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page6");
    },
    select2() {
      this.mbti.T--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page6");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
