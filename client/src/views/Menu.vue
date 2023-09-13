<template>
  <div class="px-5">
    <!-- user info -->
    <div v-if="store.user.isLoggedIn" class="flex flex-col items-center justify-center mb-10">
      <div
        class="w-[80px] h-[80px] bg-zinc-100 rounded-full flex justify-center itencen overflow-hidden"
      >
        <img v-if="user.profileUrl" :src="storageUrl + user.profileUrl" />
        <UserIcon v-else class="w-10 opacity-10" />
      </div>
      <div class="mt-3">{{ user.name }}</div>
      <div class="text-sm text-zinc-400">{{ user.email }}</div>
    </div>

    <!-- menu -->
    <nav class="menu">
      <template v-if="!store.user.isLoggedIn">
        <router-link to="/login">로그인</router-link>
        <router-link to="/join">회원가입</router-link>
      </template>
      <router-link v-if="store.user.isLoggedIn" to="/logout">로그아웃</router-link>
    </nav>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useMainStore } from '@/store';
import { UserIcon } from '@heroicons/vue/20/solid';

const store = useMainStore();
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const user = computed(() => {
  return store.user.userInfo;
});
</script>
