<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q1. 모처럼 맞은 휴일, 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black">
          - 오랜만에 만난 친구들과 수다를 떤다.</b-card
        >
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black">
          - 집에서 커피를 마시며 책을 읽는다.
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mbti: {
        E: 0,
        N: 0,
        T: 0,
      },
    };
  },
  methods: {
    select1() {
      this.mbti.E++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page2");
    },
    select2() {
      this.mbti.E--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page2");
    },
  },
};
</script>

<style></style>
