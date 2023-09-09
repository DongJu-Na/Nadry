<template>
  <div>로그아웃</div>
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
