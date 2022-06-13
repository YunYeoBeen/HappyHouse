<template>
  <div style="position: relative; overflow: hidden; width: 100%">
    <div id="map" style="overflow: hidden; position: relative"></div>
    <div style="position: absolute; top: 10px; left: 10px; overflow: hidden; z-index: 1">
      <b-button variant="success" v-if="!toggleMarkers" @click="showMarkers"
        >아파트밀집도 ON</b-button
      >
      <b-button variant="danger" v-else @click="hideMarkers">아파트밀집도 OFF</b-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      curMarker: null,
      clusterer: null,
      toggleMarkers: false,
    };
  },
  methods: {
    initMap() {
      console.log(this.$store.state.focus);
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.$store.state.focus.y, this.$store.state.focus.x),
        level: 5,
        disableDoubleClickZoom: true,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        averageCenter: true,
        minLevel: 5,
      });
    },
    setCenter(x, y) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(y, x);
      // 지도 중심을 이동 시킵니다
      this.map.setCenter(moveLatLon);
    },
    setCurMarker(x, y) {
      if (this.curMarker != null) {
        this.curMarker.setMap(null);
      }
      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(y, x);
      // 마커를 생성합니다
      this.curMarker = new kakao.maps.Marker({
        position: markerPosition,
      });
      this.curMarker.setMap(this.map);
    },
    setMarkerClusterer(data) {
      if (!this.toggleMarkers) {
        let markers = data.positions.map(function (position) {
          return new kakao.maps.Marker({
            position: new kakao.maps.LatLng(position.lat, position.lng),
          });
        });
        this.clusterer.addMarkers(markers);
        this.toggleMarkers = true;
        return;
      } else {
        this.toggleMarkers = false;
        this.clusterer.clear();
      }
    },
    showMarkers() {
      const addressData = require("@/assets/address.json");
      this.setMarkerClusterer(addressData);
    },
    hideMarkers() {
      this.setMarkerClusterer(null);
    },
  },
  watch: {
    "$store.state.focus": function () {
      this.setCenter(this.$store.state.focus.x, this.$store.state.focus.y);
    },
    "$store.state.curMarker": function () {
      this.setCurMarker(this.$store.state.curMarker.x, this.$store.state.curMarker.y);
      this.$store.dispatch("setFocus", {
        x: this.$store.state.curMarker.x,
        y: this.$store.state.curMarker.y,
      });
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=dfb1c7e52045cc96ee393533b2155f3f&libraries=clusterer";
      document.head.appendChild(script);
    }
  },
  created() {
    if (this.$store.state.focus.x === "") {
      this.$store.dispatch("setFocus", {
        x: "127.03528175393517",
        y: "37.501288000000734",
      });
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 800px;
}
</style>
