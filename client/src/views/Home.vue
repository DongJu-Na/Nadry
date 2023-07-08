<template>
  <!-- greetings -->
  <Greetings />
  <!-- hero banner -->
  <HeroBanner />
  <!-- recommends -->
  <Recommends />
  <!-- reviews -->
  <Reviews />
  <!-- map -->
  <Map />
  <!-- market -->
  <Market />
</template>

<script setup>
import Greetings from '@/components/home/Greetings.vue';
import HeroBanner from '@/components/home/HeroBanner.vue';
import Recommends from '@/components/home/Recommends.vue';
import Reviews from '@/components/home/Reviews.vue';
import Map from '@/components/home/Map.vue';
import Market from '@/components/home/Market.vue';
</script>

<style lang="scss" scoped></style>
