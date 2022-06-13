<template>
  <div class="mbti-background" style="height: 100vh">
    <div style="margin-left: 20%; height: 20%; background-color: #f8f6e9">
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1>ㅤㅤ</h1>
      <h1 class="question">
        Q8. 휴일에 맛있는 점심을 만들어 먹은 뒤, 노곤해진 몸으로, 당신이라면?
      </h1>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select1" text-variant="black">
          - 힐링 ASMR을 들으며 창가에 앉아 휴식을 취한다.
        </b-card>
      </div>
      <div class="mbti-font">
        <b-card class="mbticard" @click="select2" text-variant="black">
          - 그림을 그리는 등 자신의 취미 생활이나 자기개발을 한다.
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
      this.$router.push("/mbti/page9");
    },
    select2() {
      this.mbti.N--;
      this.$store.dispatch("setMbti", this.mbti);
      this.$router.push("/mbti/page9");
    },
  },
  created() {
    this.mbti = this.$store.state.mbti;
  },
};
</script>

<style></style>
