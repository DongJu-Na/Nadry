<template>
  <div class="px-5">
    <!-- tab -->
    <div class="">
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

    <!-- my review -->
        <div v-if="tabs[0].current" v-for="review in bestReviews" :key="review.rvId" class="flex flex-col pb-10 mt-10 border-b">
      {{ review }}
    </div>

    <div v-else-if="tabs[1].current" v-for="review in myReviews" :key="review.rvId" class="flex flex-col pb-10 mt-10 border-b">
      {{ review }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getMyTripReview , getBestTripReview } from '@/api';

const router = useRouter();

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
      sort: ['createDate'],
    };
    const {
      status,
      data: { data },
    } = await getMyTripReview(payload);
    // console.log('MyTripReview: ', data);
    if (status === 200 && data) {
      myReviews.value = data;
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
     console.debug('BestReview: ', data);
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
