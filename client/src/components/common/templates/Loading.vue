<template>
  <div
    v-if="store.state.loading"
    class="fixed top-0 bottom-0 left-0 right-0 z-[9999] flex flex-col justify-center items-center bg-[rgba(255,255,255,.9)]"
  >
    <Vue3Lottie :animationData="loading" :width="250" :height="250" />
    <span class="text-sm font-medium text-zinc-500">잠시만 기다려주세요</span>
  </div>
</template>

<script setup>
import { useMainStore } from '@/store';
// import loading from '@/lottie/loading-dots-line.json';
import loading from '@/lottie/tourist-1.json';

const store = useMainStore();
</script>
