<template>
  <div id="mbti" class="mbti-background">
    <b-jumbotron header class="mb-0">
      <div class="container px-5">
        <div class="row justify-content-center">
          <div class="col-lg-8 col-xxl-6">
            <div class="text-center my-5">
              <h1 class="fw-bolder mb-3 font-weight-bold">나의 주거선택 유형은?</h1>
              <p class="lead fw-normal text-muted mb-4">
                사람마다 선호하는 주거 기준은 모두 달라요! 당신에게 맞는 주거유형을 몇 가지 질문들을
                통해 알아보세요!
              </p>
            </div>
          </div>
        </div>
      </div>
    </b-jumbotron>
    <div class="background">
      <b-button
        class="mbti-font btn btn-warning"
        @click="moveStart"
        size="large"
        style="font-size: 50px; width: 40%; margin-top: 31%; height: 20%"
        >Get Started</b-button
      >
      <transition :name="transitionName"> </transition>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      transitionName: "",
      bgImg: "@/assets/mbti.jpg",
    };
  },
  methods: {
    moveStart() {
      this.$router.push({ name: "page1View" });
    },
  },
  watch: {
    $route(to, from) {
      if (to.meta.page == null || from.meta.page == null) {
        this.transitionName = "fade";
      } else {
        this.transitionName = to.meta.page > from.meta.page ? "next" : "prev";
      }
      console.log(this.transitionName);
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
.mbti-font {
  font-family: "Nanum Brush Script", cursive;
}
#mbti {
  display: grid;
  grid-template-rows: min-content;
  min-height: 100%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif,
    "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #fafafa;
}
.background {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("../assets/mbti.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-position: center;
}
html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}
nav {
  display: flex;
  align-items: center;
  justify-content: space-around;
  background-color: #424242;
  position: sticky;
  bottom: 0;
  z-index: 1;
}
a {
  color: white;
  text-decoration: none;
  text-transform: uppercase;
  font-weight: bold;
  padding: 1em 0;
  margin: 0 1em;
  border-bottom: 2px solid transparent;
}
a.router-link-exact-active {
  border-color: inherit;
}
main h1 {
  color: black;
}
main {
  min-height: 100%;
  display: grid;
  grid-template: "main";
  flex: 1;
  background-color: white;
  position: relative;
  z-index: 0;
  overflow-x: hidden;
}
main > * {
  grid-area: main;
  background-color: white;
  position: relative;
}
main > :first-child {
  z-index: 1;
}

/* 전환효과 (Slide) */
.next-leave-to {
  animation: leaveToLeft 500ms both cubic-bezier(0.165, 0.84, 0.44, 1);
  z-index: 0;
}
.next-enter-to {
  animation: enterFromRight 500ms both cubic-bezier(0.165, 0.84, 0.44, 1);
  z-index: 1;
}
.prev-leave-to {
  animation: leaveToRight 500ms both cubic-bezier(0.165, 0.84, 0.44, 1);
  z-index: 1;
}
.prev-enter-to {
  animation: enterFromLeft 500ms both cubic-bezier(0.165, 0.84, 0.44, 1);
  z-index: 0;
}
@keyframes leaveToLeft {
  from {
    transform: translateX(0);
  }
  to {
    transform: translateX(-25%);
    filter: brightness(0.5);
  }
}
@keyframes enterFromLeft {
  from {
    transform: translateX(-25%);
    filter: brightness(0.5);
  }
  to {
    transform: translateX(0);
  }
}
@keyframes leaveToRight {
  from {
    transform: translateX(0);
  }
  to {
    transform: translateX(100%);
  }
}
@keyframes enterFromRight {
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
}

/* 전환효과 (Fade) */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.8s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
