<template>
  <div class="flex flex-col mt-10">
    <h2 class="px-5 mb-3 text-lg font-semibold">{{$t('homeSubTitleMsg2')}}</h2>
    <div id="map" class="w-full h-[500px]">
      <!-- input -->
      <div class="absolute z-[99] p-3 w-full">
        <div class="flex items-center w-full p-1 pl-3 bg-white rounded-md shadow-md">
          <i class="text-xl las la-search text-zinc-400"></i>
          <input
            v-model="searchWord"
            @keyup.enter="submit(searchWord)"
            type="text"
            :placeholder="$t('mapPlaceHolader')"
            class="w-full p-2"
          />
        </div>
      </div>
    </div>
    <!-- items -->
    <div class="flex z-[100] -translate-y-10">
      <Swiper
        :slides-per-view="2.5"
        :space-between="10"
        :slides-offset-before="10"
        :slides-offset-after="20"
        :loop="false"
      >
        <SwiperSlide v-for="(item, index) in searchResults" :key="item.id">
          <div
            @click="onCenterForClick(index)"
            class="overflow-hidden bg-white rounded-md shadow-md"
          >
            <img
              v-if="item.firstimage"
              :src="item.firstimage"
              class="object-cover w-full h-[100px]"
            />
            <div v-else class="flex items-center justify-center w-full h-[100px] bg-zinc-50">
              <PhotoIcon class="w-8 text-zinc-200" />
            </div>
            <div class="p-3">
              <div class="text-xs font-medium truncate ...">{{ item.title }}</div>
              <div class="text-2xs text-zinc-400 truncate ...">{{ item.addr1 }}</div>
              <router-link
                :to="`/trips/${item.contentid}/${item.contenttypeid}`"
                class="flex justify-center w-full px-2 py-2 mt-2 text-xs font-semibold text-gray-900 bg-white rounded shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
              >
                {{$t('mapInfoDetail')}}
              </router-link>
            </div>
          </div>
        </SwiperSlide>
      </Swiper>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { searchTrip } from '@/api';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import { PhotoIcon } from '@heroicons/vue/24/solid';

const searchWord = ref('');
const searchResults = ref([]);
const markers = ref([]);

let map;

const onCenterForClick = (index) => {
  map.setCenter(
    new kakao.maps.LatLng(searchResults.value[index].mapy, searchResults.value[index].mapx)
  );
};

const submit = async () => {
  if (!searchWord.value) {
    alert('검색어를 입력하세요.');
    return;
  }

  if (searchResults.value.length > 0) {
    searchResults.value = [];
    markers.value.forEach((marker) => {
      marker.setMap(null);
    });
    markers.value = [];
    console.log(searchResults.value, markers.value);
  }

  try {
    const payload = {
      numOfRows: 30,
      pageNo: 1,
      MobileOS: 'ETC',
      MobileApp: 'AppTest',
      _type: 'json',
      listYN: 'Y',
      contentTypeId: '',
      arrange: 'A',
      areaCode: '',
      sigunguCode: '',
      cat1: '',
      cat2: '',
      cat3: '',
      keyword: searchWord.value,
    };

    await searchTrip(payload).then((result) => {
      const {
        status,
        data: {
          response: { body },
        },
      } = result;

      if (status === 200 && body && body.items.item.length > 0) {
        body.items.item.forEach((item) => {
          searchResults.value.push(item);
          let kakaoMapPosition = new kakao.maps.LatLng(item.mapy, item.mapx);
          let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(item.mapy, item.mapx),
          });
          marker.setMap(map);
          markers.value.push(marker);
        });

        let firstSearchMapPos = new kakao.maps.LatLng(
          body.items.item[0].mapy,
          body.items.item[0].mapx
        );
        map.setCenter(firstSearchMapPos);
      } else {
        alert('검색 결과가 없습니다.');
      }
    });
  } catch (error) {
    console.log(error);
  }
};

const onEnter = () => {
  submit(searchWord.value);
};

const getLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const myPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
      map.setCenter(myPosition);

      // 내위치는 마커로 표시 하지 않아도 될듯
      // const marker = new kakao.maps.Marker({
      //   position: myPosition,
      //   map: map,
      // });
    });
  } else {
    console.log('getLocation error');
  }
};

const loadMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 7,
  };
  map = new kakao.maps.Map(container, options);

  getLocation();
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    loadMap();
  } else {
    const script = document.createElement('script');
    script.src =
      '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + import.meta.env.VITE_APP_KAKAO_KEY;
    script.onload = () => {
      kakao.maps.load(() => {
        kakao.maps.load(loadMap);
      });
    };

    document.head.appendChild(script);
  }
});
</script>
