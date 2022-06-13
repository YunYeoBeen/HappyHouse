<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q7. 평소 즐겨먹던 음식의 포장지가 리뉴얼되었을 때, 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black"> - 구매한다. </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black"> - 구매하지않는다. </b-card>
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
      console.dir(this.mbti);
      this.mbti.T--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page8");
    },
    select2() {
      this.mbti.T++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page8");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
