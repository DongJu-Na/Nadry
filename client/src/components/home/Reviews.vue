<template>
  <div class="flex flex-col my-10">
    <!-- title -->
    <div class="flex items-center justify-between w-full px-5 mb-3">
      <h2 class="text-lg font-semibold">지금 뜨는 리뷰</h2>
      <a href="" class="px-1 text-zinc-400">
        <span class="mr-1 text-sm font-medium">전체보기</span>
        <i class="las la-arrow-right"></i>
      </a>
    </div>

    <!-- list -->
    <div class="">
      <Swiper
        :slides-per-view="2.5"
        :space-between="10"
        :slides-offset-before="20"
        :slides-offset-after="20"
      >
        <SwiperSlide v-for="item in items" :key="item.id">
          <div class="overflow-hidden bg-zinc-50 rounded-xl">
            <div
              class="absolute bottom-0 flex items-center justify-between w-full p-3 text-xs leading-none text-white drop-shadow-sm"
            >
              <div>@{{ item.user }}</div>
              <div class="flex items-center">
                <i class="mr-1 text-base leading-none las la-thumbs-up"></i>
                <span>{{ item.like }}</span>
              </div>
            </div>
            <img :src="item.image" class="w-full h-[220px] object-cover" />
          </div>
        </SwiperSlide>
      </Swiper>
    </div>
  </div>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';

const items = [
  {
    id: 1,
    user: 'newscool',
    like: 50,
    image:
      'https://images.unsplash.com/photo-1682685797857-97de838c192e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 2,
    user: 'choree',
    like: 120,
    image:
      'https://images.unsplash.com/photo-1690993660127-1a7cdd87ec9e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 3,
    user: 'hands',
    like: 589,
    image:
      'https://images.unsplash.com/photo-1691142750541-2ec61bb9d23d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 4,
    user: 'newjeans',
    like: 126,
    image:
      'https://plus.unsplash.com/premium_photo-1689963420964-d11bebac5408?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 5,
    user: 'hive',
    like: 8297,
    image:
      'https://images.unsplash.com/photo-1544963823-6267c70c3e11?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
  {
    id: 6,
    user: 'tril',
    like: '6K',
    image:
      'https://images.unsplash.com/photo-1520116468816-95b69f847357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=80',
  },
];
</script>
