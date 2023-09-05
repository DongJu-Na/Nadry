<template>
  <div v-if="detailCommon" class="">
    <!-- primary image -->
    <img v-if="detailImage" :src="detailImage[0].originimgurl" class="w-full" />
    <div
      v-else
      class="flex flex-col justify-center items-center bg-zinc-200 w-full h-[300px] text-zinc-500"
    >
      <i class="las la-image text-[2rem]"></i>
      <span class="text-sm">관련 이미지가 없습니다</span>
    </div>
    <div class="px-5 mt-10">
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
      <h1 class="text-2xl">{{ detailCommon.title }}</h1>
      <div class="flex gap-1 mt-2 text-sm text-zinc-400">
        <p>{{ detailCommon.addr1 }}</p>
        <p>{{ detailCommon.addr2 }}</p>
      </div>
      <div v-html="detailCommon.homepage" class="mt-1 text-sm text-blue-600"></div>
      <!-- rating -->
      <div class="flex gap-3 mt-3 text-xs">
        <div class="flex items-center gap-1">
          <img src="/svg/star.svg" class="w-[16px]" />
          <span class="text-sm font-semibold">5.0</span>
        </div>
        <div class="flex items-center gap-1">
          <img src="/svg/comment.svg" class="w-[16px]" />
          <span class="text-sm font-semibold">30</span>
        </div>
      </div>
      <!-- description -->
      <div v-html="detailCommon.overview" class="mt-8 break-keep"></div>
      <!-- etc information -->
      <div class="mt-10">
        <h3 class="mb-3 font-medium">이용안내</h3>
        <div class="mt-3 text-xs text-zinc-400">{{ detailIntro }}</div>
      </div>
      <!-- reviews -->
      <div class="mt-10">
        <h3 class="mb-3 font-medium">리뷰</h3>
        <a href="" class="button">
          <i class="las la-pen"></i>
          <span>리뷰 작성</span>
        </a>
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
    console.log('detailIntro: ', status, item[0]);
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
    console.log('detailImage: ', status, item);
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
    console.log('detailCommon: ', status, item[0]);
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
