<template>
  <div v-for="review in reviews" :key="review.rvId" class="flex flex-col mt-5">
    <div>{{ review.createBy }}</div>
    <img v-if="review.reviewImageUrl" :src="`${storageUrl}${review.reviewImageUrl}`" alt="" />
    <!-- <div>{{ review.reviewImageUrl }}</div> -->
    <div>{{ review.content }}</div>
    <div>{{ review.likes }}</div>
    <!-- <div v-if="review.reviewGrade">{{ review.reviewGrade[0].rating }}</div> -->
    <div>좋아요</div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';
import { getTripReview } from '@/api';

const route = useRoute();
const reviews = ref(null);

const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;
// const storageUrl = 'http://218.236.144.129:8080';

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

onBeforeMount(() => {
  fetchReviews();
});
</script>
