<template>
  <div class="px-5">
    <!-- tab -->
    <div class="py-3 sticky top-[60px] bg-white z-[9999]">
      <nav class="flex space-x-4" aria-label="Tabs">
        <a
          v-for="(tab, index) in tabs"
          @click="clickTab(index)"
          :key="tab.name"
          :class="[
            tab.current ? 'bg-green-100 text-green-700' : 'text-zinc-400',
            'rounded-md px-3 py-2 text-sm font-medium transition-all',
          ]"
          >{{ tab.name }}</a
        >
      </nav>
    </div>

    <!-- best review -->
    <div
      v-if="tabs[0].current"
      v-for="review in bestReviews"
      :key="review.rvId"
      class="flex flex-col pb-10 mt-10 border-b"
    >
      <div class="flex items-center justify-between mb-5">
        <!-- user profile image -->
        <div
          class="w-[40px] h-[40px] bg-zinc-100 mr-3 rounded-full flex justify-center itencen overflow-hidden border border-zinc-100"
        >
          <img v-if="review.profileUrl" :src="storageUrl + review.profileUrl" />
          <UserIcon v-else class="w-5 opacity-10" />
        </div>
        <div class="flex flex-col leading-none">
          <div class="mb-[4px]">
            <span class="mr-2 font-semibold">{{ review.name }}</span>
            <span>{{ review.email }}</span>
          </div>
          <div class="text-xs leading-none text-zinc-400">{{ transDate(review.createDate) }}</div>
        </div>
        <div class="flex gap-5 ml-auto">
          <!-- rating -->
          <div class="flex items-center gap-1">
            <StarIcon class="w-4 text-yellow-400" />
            <span>{{ review.rating }}</span>
          </div>
          <!-- like -->
          <div
            @click="like(review.rvId)"
            class="flex items-center gap-1 p-2 leading-none transition-all border rounded border-rose-300 text-rose-500 active:scale-95"
          >
            <HandThumbUpIcon class="w-4" />
            <div>{{ review.likes }}</div>
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

    <!-- my review -->
    <div
      v-else-if="tabs[1].current"
      v-for="review in myReviews"
      :key="review.rvId + 'my'"
      class="flex flex-col pb-10 mt-10 border-b"
    >
      <div class="flex items-center justify-between mb-5">
        <!-- user profile image -->
        <div
          class="w-[40px] h-[40px] bg-zinc-100 mr-3 rounded-full flex justify-center itencen overflow-hidden border border-zinc-100"
        >
          <img v-if="review.member.profileUrl" :src="storageUrl + review.member.profileUrl" />
          <UserIcon v-else class="w-5 opacity-10" />
        </div>
        <div class="flex flex-col leading-none">
          <div class="mb-[4px]">
            <span class="mr-2 font-semibold">{{ review.member.name }}</span>
            <span>{{ review.member.email }}</span>
          </div>
          <div class="text-xs leading-none text-zinc-400">{{ transDate(review.createDate) }}</div>
        </div>
        <div class="flex gap-5 ml-auto">
          <!-- rating -->
          <div v-if="review.reviewGrade.length > 0" class="flex items-center gap-1">
            <StarIcon class="w-4 text-yellow-400" />
            <span>{{ review.reviewGrade[0].rating }}</span>
          </div>
          <!-- like -->
          <div
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
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getMyTripReview, getBestTripReview } from '@/api';
import dayjs from 'dayjs';
import { UserIcon, HandThumbUpIcon, StarIcon } from '@heroicons/vue/20/solid';

const router = useRouter();

const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 HH:mm');
};

// 1: 인기리뷰 / 2: 내가 작성한 리뷰
const currentType = ref(1);
const myReviews = ref(null);
const bestReviews = ref(null);

const tabs = ref([
  {
    name: '실시간 인기 리뷰',
    current: true,
  },
  {
    name: '내가 작성한 리뷰',
    current: false,
  },
]);

const clickTab = (index) => {
  console.log(index);
  tabs.value.forEach((tab) => {
    tab.current = false;
  });
  tabs.value[index].current = true;

  if (index === 0) {
    fetchBestReviews();
  } else if (index === 1) {
    fetchMyReviews();
  }
};

const fetchMyReviews = async () => {
  try {
    const payload = {
      page: 1,
      size: 10,
      sort: ['createDate,desc'],
    };
    const {
      status,
      data: { data },
    } = await getMyTripReview(payload);
    console.log('MyTripReview: ', data);
    if (status === 200 && data) {
      myReviews.value = data.review;
    }
  } catch (error) {
    console.log(error);
  }
};

const fetchBestReviews = async () => {
  try {
    const payload = {
      page: 1,
      size: 10,
    };
    const {
      status,
      data: { data },
    } = await getBestTripReview(payload);
    console.log('BestReview: ', data);
    if (status === 200 && data) {
      bestReviews.value = data;
    }
  } catch (error) {
    console.log(error);
  }
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchBestReviews();
});
</script>
