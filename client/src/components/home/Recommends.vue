<template>
  <div class="px-5 my-10">
    <h2 class="text-lg font-semibold">어떤 여행을 찾으세요?</h2>
    <ul class="grid grid-cols-4 gap-4 mt-5">
      <li v-for="item in categories" :key="item.id">
        <a href="" class="flex flex-col items-center">
          <img :src="`/svg/recommend_${item.icon}.svg`" />
          <span class="mt-2 text-sm font-medium text-zinc-800">{{ item.name }}</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const categories = ref([
  {
    id: 12,
    name: '관광지',
    icon: 'tower',
  },
  {
    id: 14,
    name: '문화시설',
    icon: 'library',
  },
  {
    id: 15,
    name: '축제/공연',
    icon: 'festival',
  },
  {
    id: 25,
    name: '여행코스',
    icon: 'trail',
  },
  {
    id: 28,
    name: '레포츠',
    icon: 'reports',
  },
  {
    id: 32,
    name: '숙박',
    icon: 'bed',
  },
  {
    id: 38,
    name: '쇼핑',
    icon: 'shop',
  },
  {
    id: 39,
    name: '음식점',
    icon: 'rice',
  },
]);
</script>
