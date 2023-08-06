<template>
  <div class="px-5 my-20">
    <h2 class="text-lg font-semibold">추천 여행지</h2>
    <ul class="grid grid-cols-4 gap-4 mt-5">
      <li v-for="item in categories" :key="item.id">
        <a href="" class="flex flex-col items-center">
          <img :src="`/svg/recommend_${item.icon}.svg`" />
          <span class="text-sm font-medium text-zinc-800 mt-2">{{ item.name }}</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const categories = ref([
  {
    id: 1,
    name: '관광지',
    icon: 'tower',
  },
  {
    id: 2,
    name: '문화시설',
    icon: 'library',
  },
  {
    id: 3,
    name: '축제공연',
    icon: 'festival',
  },
  {
    id: 4,
    name: '레포츠',
    icon: 'reports',
  },
  {
    id: 5,
    name: '숙박',
    icon: 'bed',
  },
  {
    id: 6,
    name: '쇼핑',
    icon: 'shop',
  },
  {
    id: 7,
    name: '음식점',
    icon: 'rice',
  },
  {
    id: 8,
    name: '여행코스',
    icon: 'trail',
  },
]);
</script>
