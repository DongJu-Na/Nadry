<template>
  <div class="flex items-center justify-between px-5 my-5">
    <h2 class="text-2xl font-medium">오늘, 나들이는<br />어디로 떠나볼까요?</h2>
    <CircleProfile v-if="store.user.isLoggedIn" />
  </div>
</template>

<script setup>
import { useMainStore } from '@/store';
import CircleProfile from '@/components/common/moleclues/CircleProfile.vue';

const store = useMainStore();
</script>
