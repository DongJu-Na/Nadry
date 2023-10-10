<template>
  <header
    class="fixed top-0 left-0 right-0 mx-auto bg-white flex justify-between items-center w-full h-[60px] z-[100]"
    :style="`width:var(--app-width)`"
  >
    <!-- logo -->
    <router-link to="/">
      <img src="/svg/logo.svg" alt="Nadry" class="w-[110px] h-[60px] px-5 py-3" />
    </router-link>

    <!-- menu -->
    <router-link v-if="$route.name !== 'menu'" to="/menu" class="block p-[18px]">
      <IconMenu />
    </router-link>
    <a v-else @click="goToBack" class="block p-[18px] cursor-pointer">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        x="0px"
        y="0px"
        width="24"
        height="24"
        viewBox="0 0 24 24"
      >
        <path
          d="M 4.9902344 3.9902344 A 1.0001 1.0001 0 0 0 4.2929688 5.7070312 L 10.585938 12 L 4.2929688 18.292969 A 1.0001 1.0001 0 1 0 5.7070312 19.707031 L 12 13.414062 L 18.292969 19.707031 A 1.0001 1.0001 0 1 0 19.707031 18.292969 L 13.414062 12 L 19.707031 5.7070312 A 1.0001 1.0001 0 0 0 18.980469 3.9902344 A 1.0001 1.0001 0 0 0 18.292969 4.2929688 L 12 10.585938 L 5.7070312 4.2929688 A 1.0001 1.0001 0 0 0 4.9902344 3.9902344 z"
        ></path>
      </svg>
    </a>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router';
import IconMenu from '@/components/common/icons/IconMenu.vue';

const router = useRouter();

const goToBack = () => {
  router.go(-1);
};
</script>
