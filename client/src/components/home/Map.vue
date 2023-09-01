<template>
  <div class="flex flex-col my-20">
    <h2 class="text-lg font-semibold mb-3 px-5">주변을 둘러 보세요</h2>
    <div id="map" class="w-full h-[500px]">
      <!-- input -->
      <div class="absolute z-[99] p-3 w-full">
        <div class="bg-white w-full rounded-md p-3 shadow-md flex items-center">
          <i class="las la-search text-xl mr-2 text-zinc-400"></i>
          <input type="text" placeholder="지금, 경주를 검색해보세요!" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';

let map;

const getLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      // console.log(position);
      const myPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
      map.setCenter(myPosition);
    });
  } else {
    console.log('getLocation error');
  }
};

const loadMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };
  map = new kakao.maps.Map(container, options);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    loadMap();
  } else {
    getLocation();
    const script = document.createElement('script');
    script.onload = () => kakao.maps.load(loadMap);
    script.src =
      '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + import.meta.env.VITE_APP_KAKAO_KEY;
    document.head.appendChild(script);
  }
});
</script>
