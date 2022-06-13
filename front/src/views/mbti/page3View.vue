<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q3. 늦은 밤 집으로 돌아와, 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black">
          - 향초를 켜고 편안한 침대에 누워 하루를 마무리한다.
        </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black">
          - 내일 무엇을 해야하는 지 계획하고 미처 다 하지 못한 업무를 하며 하루를 마무리한다.
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
      this.mbti.N++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page4");
    },
    select2() {
      this.mbti.N--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page4");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
