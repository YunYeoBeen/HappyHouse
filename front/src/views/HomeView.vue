<template>
  <div>
    <!-- Header-->
    <header class="bg-dark py-5">
      <div class="container px-5">
        <div class="row gx-5 align-items-center justify-content-center">
          <div class="col-lg-8 col-xl-7 col-xxl-6">
            <div class="my-5 text-center text-xl-start">
              <h1 class="display-5 fw-bolder text-white mb-2">정확한 아파트 공공데이터!</h1>
              <p class="lead fw-normal text-white-50 mb-5">
                지금 지역을 선택하고 아파트 실거래가를 조회해보세요.
              </p>
              <select-sido></select-sido>
            </div>
          </div>
          <div class="col-xl-5 col-xxl-6 d-none d-xl-block text-center">
            <img class="img-fluid rounded-3 my-5" src="@/assets/main.png" alt="..." />
          </div>
        </div>
      </div>
    </header>
    <!-- Features section-->
    <section id="features">
      <div class="container px-5 my-5">
        <div class="row gx-5">
          <div class="col-lg-6 mb-5 mb-lg-0">
            <slide-banner></slide-banner>
            <board-banner></board-banner>
          </div>
          <div class="col-lg-6">
            <div class="row gx-5 row-cols-1 row-cols-md-2">
              <div class="col mb-5 h-100">
                <img src="@/assets/card1.png" />
              </div>
              <div class="col mb-5 h-100">
                <img src="@/assets/card2.png" />
              </div>
              <div class="col mb-5 mb-md-0 h-100">
                <img src="@/assets/card3.png" />
              </div>
              <div class="col h-100">
                <img src="@/assets/card4.png" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Testimonial section-->
    <div class="py-5 bg-light">
      <div class="container px-5 my-5">
        <div class="row gx-5 justify-content-center">
          <div class="col-lg-10 col-xl-7">
            <div class="text-center">
              <div class="fs-4 mb-4 fst-italic">
                "무엇이든 이왕이면 쌀 때 사고, 비쌀 때 팔아야하지 않겠습니까!"
              </div>
              <div class="d-flex align-items-center justify-content-center">
                <img
                  class="rounded-circle me-3"
                  src="https://dummyimage.com/40x40/ced4da/6c757d"
                  alt="..."
                />
                <div class="fw-bold">
                  홍유진, 윤여빈, 박영길
                  <span class="fw-bold text-primary mx-1">/</span>
                  CEO, HappyHouse
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SelectSido from "@/components/SelectSido.vue";
import SlideBanner from "@/components/SlideBanner.vue";
import BoardBanner from "@/components/BoardBanner.vue";
export default {
  components: {
    SelectSido,
    SlideBanner,
    BoardBanner,
  },
};
</script>
