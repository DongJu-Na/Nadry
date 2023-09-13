<template>
  <header
    class="fixed top-0 left-0 right-0 mx-auto bg-white flex justify-between items-center w-full h-[60px] z-[100]"
    :style="`width:var(--app-width)`"
  >
    <router-link to="/">
      <img src="/svg/logo.svg" alt="Nadry" class="w-[90px] pl-5" />
    </router-link>
    <!-- <h1 class="pl-5 text-2xl font-semibold text-emerald-500">Nadry</h1> -->
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
