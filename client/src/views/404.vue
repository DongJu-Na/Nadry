<template>
  <div class="flex flex-col items-center justify-center flex-1 grow-1">
    <img src="/img/404.png" class="w-[50%]" />
    <span class="text-lg text-zinc-400">페이지가 없습니다</span>
    <BackButton />
  </div>
</template>

<script setup>
import BackButton from '@/components/common/atoms/BackButton.vue';
</script>
