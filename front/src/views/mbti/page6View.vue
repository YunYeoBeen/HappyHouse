<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">Q6. 아기자기한 물품들을 모으거나 구경하는 것을, 당신이라면?</h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black"> - 좋아한다 </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black"> - 싫어한다 </b-card>
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
      this.mbti.T--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page7");
    },
    select2() {
      this.mbti.T++;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page7");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
