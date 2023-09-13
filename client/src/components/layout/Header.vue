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
    <router-link to="/menu" class="block p-[18px]">
      <IconMenu />
    </router-link>
  </header>
</template>

<script setup>
import IconMenu from '@/components/common/icons/IconMenu.vue';
</script>
