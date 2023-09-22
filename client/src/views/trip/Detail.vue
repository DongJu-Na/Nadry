<template>
  <div v-if="detailCommon" class="w-full mt-[60px]">
    <!-- swiper -->
    <Swiper
      v-if="detailImage"
      slides-per-view="auto"
      :modules="modules"
      :space-between="0"
      :auto-height="true"
      :pagination="{ clickable: true }"
    >
      <SwiperSlide v-for="image in detailImage" :key="image.serialnum">
        <img :src="image.originimgurl" class="w-full" />
      </SwiperSlide>
    </Swiper>
    <!-- no image -->
    <div
      v-else
      class="flex flex-col justify-center items-center bg-zinc-200 w-full h-[300px] text-zinc-500"
    >
      <i class="las la-image text-[2rem]"></i>
      <span class="text-sm">관련 이미지가 없습니다</span>
    </div>

    <!-- infomation -->
    <div class="px-5 mt-10">
      <!-- title -->
      <div class="flex justify-between">
        <div>
          <h1 class="text-2xl">{{ detailCommon.title }}</h1>
          <div class="flex gap-1 mt-2 text-sm text-zinc-400">
            <p>{{ detailCommon.addr1 }}</p>
            <p>{{ detailCommon.addr2 }}</p>
          </div>
          <div v-html="detailCommon.homepage" class="mt-1 text-sm text-blue-600"></div>
        </div>
        <div @click="clickWish" class="flex flex-col items-center gap-[3px] text-rose-600">
          <HeartOutlineIcon class="w-[25px]" />
          <span class="text-xs leading-none">찜하기</span>
        </div>
      </div>
      <!-- rating & reviews -->
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
      <div
        v-if="detailCommon.overview !== '-'"
        v-html="detailCommon.overview"
        class="mt-8 break-keep"
      ></div>

      <!-- intro -->
      <div v-if="detailInfo" class="mt-10">
        <h3 class="mb-3 font-medium">{{ detailInfo.infoname }}</h3>
        <div class="mt-3 text-xs text-zinc-400" v-html="detailInfo.infotext"></div>
      </div>

      <!-- usage -->
      <div class="mt-10">
        <h3 class="mb-3 font-medium">이용안내</h3>
        <div class="mt-3 text-xs text-zinc-400">{{ detailIntro }}</div>
      </div>

      <!-- reviews -->
      <div class="mt-10">
        <h3 class="mb-3 font-medium">리뷰</h3>
        <!-- 리뷰 작성 버튼 -->
        <a @click="writeReview" class="button">
          <i class="las la-pen"></i>
          <span>리뷰 작성</span>
        </a>
        <!-- 리뷰 리스트 -->
        <ReviewList />
      </div>
    </div>
  </div>

  <!-- review modal -->
  <ReviewModal :open="showReviewModal" @close="showReviewModal = false"></ReviewModal>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {
  getTripDetailIntro,
  getTripDetailInfo,
  getTripDetailImage,
  getTripDetailCommon,
  addWish,
} from '@/api';
import { useMainStore } from '@/store';
import { Pagination } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/pagination';
import ReviewModal from '@/components/modal/ReviewModal.vue';
import ReviewList from '@/components/review/ReviewList.vue';
import { HeartIcon as HeartSolidIcon } from '@heroicons/vue/24/solid';
import { HeartIcon as HeartOutlineIcon } from '@heroicons/vue/24/outline';

const modules = [Pagination];

const store = useMainStore();
const route = useRoute();
const router = useRouter();

const detailIntro = ref(null);
const detailInfo = ref(null);
const detailImage = ref(null);
const detailCommon = ref(null);
const imageLoading = ref(false);
const reviews = ref([]);
const showReviewModal = ref(false);

// 찜하기
const clickWish = async () => {
  console.log(detailCommon.value.title, detailCommon.value.firstimage);
  const payload = {
    contentId: route.params.id,
    contentName: detailCommon.value.title,
    contentImageUrl: detailCommon.value.firstimage,
  };

  try {
    const {
      status,
      data: { resultMsg },
    } = await addWish(payload);
    if (status === 200 && resultMsg) {
      alert(resultMsg);
    }
  } catch (error) {
    console.log(error);
  }
};

// review
const writeReview = () => {
  // alert('리뷰 작성');
  showReviewModal.value = true;
};

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
    } = await getTripDetailInfo(payload);
    console.log('detailInfo: ', status, item[0]);
    if (status === 200 && item[0]) {
      detailInfo.value = item[0];
    }
  } catch (error) {
    console.log(error);
  }

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
      imageLoading.value = true;
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

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchTripData();
});
</script>
