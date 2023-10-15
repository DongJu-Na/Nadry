<template>
  <div v-for="review in reviews" :key="review.rvId" class="flex flex-col pb-10 mt-10 border-b">
    <!-- profile -->
    <div class="flex items-center justify-between mb-5">
      <!-- user profile image -->
      <div
        class="w-[40px] h-[40px] bg-zinc-100 mr-3 rounded-full border border-zinc-200/10 flex justify-center itencen overflow-hidden"
      >
        <img v-if="review.member.profileUrl" :src="storageUrl + review.member.profileUrl" />
        <UserIcon v-else class="w-5 opacity-10" />
      </div>
      <div class="flex flex-col leading-none">
        <div class="mb-0 text-zinc-600">
          <span class="text-sm font-semibold">{{ review.member.name }}</span>
          <!-- <span>{{ review.member.email }}</span> -->
        </div>
        <div class="text-xs text-zinc-400">{{ transDate(review.createDate) }}</div>
      </div>
      <div class="flex gap-5 ml-auto">
        <!-- rating -->
        <div v-if="review.reviewGrade.length > 0" class="flex items-center gap-1">
          <StarIcon class="w-4 text-yellow-400" />
          <span>{{ review.reviewGrade[0].rating }}</span>
        </div>
        <!-- like -->
        <div
          v-if="store.user.isLoggedIn"
          @click="like(review.rvId)"
          class="flex items-center gap-1 p-2 leading-none transition-all border rounded border-rose-300 text-rose-500 active:scale-95"
        >
          <HandThumbUpIcon class="w-4" />
          <div>{{ review.like }}</div>
        </div>
      </div>
    </div>
    <!-- image -->
    <img
      v-if="review.reviewImageUrl"
      :src="`${storageUrl}${review.reviewImageUrl}`"
      class="mb-5 rounded-md"
    />
    <!-- content -->
    <div class="p-5 text-sm rounded-md bg-zinc-50 text-zinc-500">{{ review.content }}</div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';
import { useMainStore } from '@/store';
import { getTripReview, postTripLike } from '@/api';
import dayjs from 'dayjs';
import { UserIcon, HandThumbUpIcon, StarIcon } from '@heroicons/vue/24/solid';

const store = useMainStore();

const route = useRoute();
const reviews = ref(null);

const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 HH:mm');
};

const fetchReviews = async () => {
  try {
    const payload = {
      contentId: route.params.id,
      page: 1,
      size: 10,
    };
    const {
      status,
      data: {
        data: { review },
      },
    } = await getTripReview(payload);
    console.log('Reviews: ', review);
    if (review) {
      reviews.value = review;
    }
  } catch (error) {
    console.log(error);
  }
};

const like = async (rvId) => {
  console.log(rvId);
  const payload = {
    rvId: rvId,
  };
  try {
    const { status, data } = await postTripLike(payload);
    console.log(status, data);
  } catch (error) {
    console.log(error);
  }
};

onBeforeMount(() => {
  fetchReviews();
});
</script>
