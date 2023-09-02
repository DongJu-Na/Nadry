<template>
  <div v-if="detailCommon && detailImage" class="">
    <!-- primary image -->
    <img :src="detailImage[0].originimgurl" alt="" />
    {{ detailIntro }}
    <div class="p-5">
      <!-- extra -->
      <!-- <div class="flex">
        <div>
          <img src="" alt="" />
          <span>찜하기</span>
        </div>
        <div>
          <img src="" alt="" />
          <span>공유하기</span>
        </div>
      </div> -->
      <!-- tag -->
      <!-- <div class="flex text-emerald-500">
        <span>#경주</span>
        <span>#유적지</span>
        <span>#핫플</span>
      </div> -->
      <!-- title -->
      <h1>{{ detailCommon.title }}</h1>
      <div>{{ detailCommon.add1 }}</div>
      <div>{{ detailCommon.add2 }}</div>
      <div v-html="detailCommon.homepage"></div>
      <!-- rating -->
      <div>
        <div>4.9</div>
        <div>*****</div>
      </div>
      <div>387개의 리뷰가 있습니다</div>
      <!-- description -->
      <div v-html="detailCommon.overview" class="mt-10 break-keep"></div>
      <!-- etc information -->
      <div class="mt-10">
        <h3>이용안내</h3>
      </div>
      <!-- reviews -->
      <div class="mt-10">
        <h3>리뷰</h3>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {
  getTripDetailIntro,
  getTripDetailInfo,
  getTripDetailImage,
  getTripDetailCommon,
} from '@/api';
import { useMainStore } from '@/store';

const store = useMainStore();

const route = useRoute();
const router = useRouter();

const detailIntro = ref(null);
const detailInfo = ref(null);
const detailImage = ref(null);
const detailCommon = ref(null);

// const contentId = computed(() => {
//   return route.params.id;
// });

const fetchTripData = async () => {
  // 로딩중 출력
  store.state.setLoading(true);
  // Intro
  try {
    const payload = {
      contentId: route.params.id,
      contentTypeId: route.params.type,
    };
    const {
      status,
      data: {
        response: {
          body: {
            items: { item },
          },
        },
      },
    } = await getTripDetailIntro(payload);
    console.log(status, item[0]);
    if (status === 200 && item[0]) {
      detailIntro.value = item[0];
    }
  } catch (error) {
    console.log(error);
  }

  // Info

  // Image
  try {
    const payload = {
      contentId: route.params.id,
      imageYN: 'Y',
      subImageYN: 'Y',
    };
    const {
      status,
      data: {
        response: {
          body: {
            items: { item },
          },
        },
      },
    } = await getTripDetailImage(payload);
    console.log(status, item);
    if (status === 200 && item) {
      detailImage.value = item;
    }
  } catch (error) {
    console.log(error);
  }

  // Common
  try {
    const payload = {
      contentId: route.params.id,
      contentTypeId: route.params.type,
      defaultYN: 'Y',
      firstImageYN: 'Y',
      areacodeYN: 'Y',
      catcodeYN: 'Y',
      addrinfoYN: 'Y',
      mapinfoYN: 'Y',
      overviewYN: 'Y',
    };
    const {
      status,
      data: {
        response: {
          body: {
            items: { item },
          },
        },
      },
    } = await getTripDetailCommon(payload);
    console.log(status, item[0]);
    if (status === 200 && item[0]) {
      detailCommon.value = item[0];
    }
  } catch (error) {
    console.log(error);
  }

  // 로딩중 출력
  store.state.setLoading(false);
};

onMounted(async () => {
  await router.isReady();

  fetchTripData();

  // console.log(contentId.value);
});
</script>
