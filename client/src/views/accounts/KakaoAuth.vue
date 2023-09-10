<template>
  <div class="flex justify-center items-center text-zinc-300 h-[500px] text-xs">카카오 인증</div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

onMounted(async () => {
  await router.isReady();
  console.log(route.query);
});
</script>
