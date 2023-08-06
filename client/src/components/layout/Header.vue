<template>
  <header class="fixed top-0 bg-white flex justify-between items-center w-full h-[60px] z-[100]">
    <router-link to="/">
      <img src="/svg/logo.svg" alt="Nadry" class="w-[90px] pl-5" />
    </router-link>
    <!-- <h1 class="font-semibold text-2xl text-emerald-500 pl-5">Nadry</h1> -->
    <router-link to="/menu" class="block p-[18px]">
      <IconMenu />
    </router-link>
  </header>
</template>

<script setup>
import IconMenu from '@/components/common/icons/IconMenu.vue';

const openMenu = () => {
  // console.log('open menu');
};
</script>
