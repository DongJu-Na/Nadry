<template>
  <div class="px-5">
    <!-- user info -->
    <div v-if="store.user.isLoggedIn" class="flex flex-col items-center justify-center mb-10">
      <div
        class="w-[80px] h-[80px] bg-zinc-100 rounded-full flex justify-center itencen overflow-hidden"
      >
        <img v-if="user.profileUrl" :src="user.profileUrl.includes('http') ? user.profileUrl : storageUrl + user.profileUrl" />
        <UserIcon v-else class="w-10 opacity-10" />
      </div>
      <div class="mt-3">{{ user.name }}</div>
      <div class="text-sm text-zinc-400">{{ user.email }}</div>
    </div>

    <!-- menu -->
    <nav class="menu">
      <template v-if="!store.user.isLoggedIn">
        <router-link to="/login">{{ $t('menuLogin') }}</router-link>
        <router-link to="/join">{{ $t('menuRegister') }}</router-link>
      </template>
      <router-link v-if="store.user.isLoggedIn" to="/logout">로그아웃</router-link>
    </nav>
      <button @click="$i18n.locale='ko'"> 한국어 </button>
      <button @click="$i18n.locale='en'"> 영문 </button>
      <button @click="$i18n.locale='chs'"> 중문 간체 </button>
      <button @click="$i18n.locale='cht'"> 중문 번체 </button>
      <button @click="$i18n.locale='jp'"> 일문 </button>
      <button @click="$i18n.locale='de'"> 독어 </button>
      <button @click="$i18n.locale='fr'"> 불어 </button>
      <button @click="$i18n.locale='es'"> 서어 </button>
      <button @click="$i18n.locale='py'"> 노어 </button>
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
