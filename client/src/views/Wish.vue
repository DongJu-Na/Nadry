<template>
  <div class="px-5 overflow-hidden">
    <div class="flex flex-col gap-5">
      <div
        v-for="wish in wishList"
        :key="wish.diId"
        class="flex items-center justify-between gap-3"
      >
        <router-link :to="`/trips/${wish.contentId}/1`" class="flex items-center flex-1 gap-3">
          <img :src="wish.contentImageUrl" class="object-cover w-[80px] aspect-square rounded-lg" />
          <div>
            <div class="font-medium">{{ wish.contentName }}</div>
            <div class="text-sm text-zinc-400">{{ transDate(wish.dibsDate) }}</div>
          </div>
        </router-link>
        <div>
          <button
            @click="deleteWish(wish.diId)"
            type="button"
            class="flex items-center h-8 gap-1 px-3 py-1 text-xs font-semibold rounded shadow-sm text-rose-500 bg-rose-50"
          >
            <TrashIcon class="w-4" />
            <span>삭제</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getWishList } from '@/api';
import dayjs from 'dayjs';
import { TrashIcon } from '@heroicons/vue/20/solid';

const wishList = ref([]);

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 HH:mm');
};

const deleteWish = (id) => {
  console.log('delete', id);
};

const fetchWishList = async () => {
  try {
    const {
      status,
      data: { data },
    } = await getWishList();
    console.log(status, data);
    if (status === 200 && data) {
      wishList.value = data;
    }
  } catch (error) {
    console.log(error);
  }
};

onMounted(() => {
  fetchWishList();
});
</script>
