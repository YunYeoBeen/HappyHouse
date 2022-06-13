<template>
  <div class="card w-75 mb-5">
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
      <b-carousel
        id="carousel-1"
        :interval="4000"
        controls
        indicators
        fade
        background="#ababab"
        img-width="1024"
        img-height="250"
        style="text-shadow: 1px 1px 2px #333"
      >
        <b-carousel-slide>
          <template #img>
            <img
              :src="require('@/assets/banner1.png')"
              class="d-block w-100"
              alt="..."
              height="250"
            />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img
              :src="require('@/assets/banner2.png')"
              class="d-block w-100"
              alt="..."
              height="250"
            />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img
              :src="require('@/assets/banner3.png')"
              class="d-block w-100"
              alt="..."
              height="250"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>
  </div>
</template>

<script>
export default {};
</script>
