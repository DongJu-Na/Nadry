<template>
  <div class="flex my-3">
    <Swiper
      :slides-per-view="1.6"
      :space-between="20"
      :slides-offset-before="20"
      :slides-offset-after="20"
    >
      <SwiperSlide>
        <router-link to="/trips/1">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute p-5 text-white drop-shadow-sm">
              <dt>유네스코 세계유산</dt>
              <dd class="text-2xl font-bold leading-6">종묘</dd>
            </dl>
            <img
              src="https://images.unsplash.com/photo-1532274402911-5a369e4c4bb5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              class="h-[350px] object-cover"
            />
          </div>
        </router-link>
      </SwiperSlide>
      <SwiperSlide>
        <router-link to="/trips/2">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute p-5 text-white drop-shadow-sm">
              <dt>유네스코 세계유산</dt>
              <dd class="text-2xl font-bold leading-6">종묘</dd>
            </dl>
            <img
              src="https://images.unsplash.com/34/BA1yLjNnQCI1yisIZGEi_2013-07-16_1922_IMG_9873.jpg?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              class="h-[350px] object-cover"
            />
          </div>
        </router-link>
      </SwiperSlide>
      <SwiperSlide>
        <router-link to="/trips/3">
          <div class="overflow-hidden bg-zinc-50 rounded-2xl">
            <dl class="absolute p-5 text-white drop-shadow-sm">
              <dt>유네스코 세계유산</dt>
              <dd class="text-2xl font-bold leading-6">종묘</dd>
            </dl>
            <img
              src="https://images.unsplash.com/photo-1559827291-72ee739d0d9a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
              class="h-[350px] object-cover"
            />
          </div>
        </router-link>
      </SwiperSlide>
    </Swiper>
  </div>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
</script>
