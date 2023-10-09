<template>
  <div class="flex flex-col items-center justify-center py-20">
    <img src="/svg/page_logout.svg" class="w-2/3" />
    <h1 class="text-lg text-zinc-400">{{ $t('logoutLabel1') }}</h1>
    <router-link to="/" class="mt-5 button">
      <i class="mr-2 text-xl las la-home"></i>
      <span>{{ $t('logoutLabel2') }}</span>
    </router-link>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useMainStore } from '@/store';

const store = useMainStore();

onMounted(() => {
  // logout
  store.user.userLogout();
});
</script>
