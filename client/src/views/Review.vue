<template>
  <div class="flex justify-center items-center text-zinc-300 h-[500px] text-xs">리뷰</div>
  <h1>테스트중</h1>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import {
  getMyTripReview,
} from '@/api';

const router = useRouter();

const myReviewList = ref(null);

const fetchMyReviews = async () => {
  try {
    const payload = {
      page: 1,
      size: 10,
      sort : ["createDate"],
    };
    const {
      status,
      data: {
        data: { review },
      },
    } = await getMyTripReview(payload);
    console.log('MyTripReview: ', review);
    if (review) {
      reviews.value = review;
    }
  } catch (error) {
    console.log(error);
  }
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchMyReviews();
});
</script>
