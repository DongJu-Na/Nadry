<template>
  <a @click="router.back()" class="mt-5 button">
    <i class="mr-2 text-xl las la-arrow-left"></i>
    <span>돌아가기</span>
  </a>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
</script>
