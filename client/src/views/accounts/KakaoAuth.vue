<template>
  <div class="flex justify-center items-center text-zinc-300 h-[500px] text-xs">카카오 인증</div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { userKakaoLogin } from '@/api';
import { useMainStore } from '@/store';

const route = useRoute();
const router = useRouter();
const store = useMainStore();

onMounted(async () => {
  await router.isReady();
  console.log(route.query);
  const { status, data } = await userKakaoLogin(route.query);

  if (status === 200 && data) {
      // user store 저장
      store.user.setUserInfo(data.data);
      // 토큰 저장
      store.user.setAccessToken(data.access_token);
      router.push('/');
  }
  
});
</script>
