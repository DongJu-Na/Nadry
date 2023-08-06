<template>
  <!-- header -->
  <Header />
  <!-- bottom tab -->
  <BottomTab />
  <!-- main -->
  <main class="mt-[80px]">
    <router-view></router-view>
  </main>
  <!-- footer -->
  <Footer />
</template>

<script setup>
import Header from './components/layout/Header.vue';
import Footer from './components/layout/Footer.vue';
import BottomTab from './components/layout/BottomTab.vue';
</script>
