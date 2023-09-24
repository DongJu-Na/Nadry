<template>
  <div class="flex flex-col my-10">
    <!-- title -->
    <div class="flex items-center justify-between w-full px-5 mb-3">
      <h2 class="text-lg font-semibold">지금 뜨는 리뷰</h2>
      <router-link to="/review" class="px-1 text-zinc-400">
        <span class="mr-1 text-sm font-medium">전체보기</span>
        <i class="las la-arrow-right"></i>
      </router-link>
    </div>

    <!-- list -->
    <div class="">
      <Swiper
        :slides-per-view="2.5"
        :space-between="10"
        :slides-offset-before="20"
        :slides-offset-after="20"
        :loop="true"
      >
        <SwiperSlide v-for="review in reviews" :key="review.id">
          <div class="overflow-hidden bg-zinc-50 rounded-xl">
            <div
              class="absolute bottom-0 flex items-center justify-between w-full p-3 text-xs leading-none text-white drop-shadow-sm"
            >
              <div>{{ review.createBy }}</div>
              <div class="flex items-center">
                <i class="mr-1 text-base leading-none las la-thumbs-up"></i>
                <span>{{ review.likes }}</span>
              </div>
            </div>
                <img
                    v-if="review.reviewImageUrl"
                    :src="storageUrl + review.reviewImageUrl"
                    class="w-full h-[220px] object-cover"
                  />
                  <img
                    v-else
                    src="/svg/empty_face.svg"
                    class="w-full h-[220px] object-cover"
                  />
          </div>
        </SwiperSlide>
      </Swiper>
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import { getBestTripReview } from '@/api';

const reviews = ref([]);
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const fetchReviews = async () => {
  try {
    const payload = {
      page: 1,
      size: 10,
      sort: ['createDate'],
    };
    const {
      status,
      data: { data },
    } = await getBestTripReview(payload);
    if (status === 200 && data) {
      reviews.value = data;
    }
  } catch (error) {
    console.log(error);
  }
};

onBeforeMount(() => {
  fetchReviews();
});
</script>
