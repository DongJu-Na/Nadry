<template>
  <div class="px-5 overflow-hidden">
    <div v-if="wishList.length > 0" class="flex flex-col gap-5">
      <div
        v-for="wish in wishList"
        :key="wish.diId"
        class="flex items-center justify-between gap-3"
      >
        <router-link
          :to="`/trips/${wish.contentId}/${wish.contentTypeId}`"
          class="flex items-center flex-1 gap-3"
        >
          <img :src="wish.contentImageUrl" class="object-cover w-[80px] aspect-square rounded-lg" />
          <div>
            <div class="font-medium">{{ wish.contentName }}</div>
            <div class="text-sm text-zinc-400">{{ transDate(wish.dibsDate) }}</div>
          </div>
        </router-link>
        <div>
          <button
            @click="deleteWish(wish)"
            type="button"
            class="flex items-center h-8 gap-1 px-3 py-1 text-xs font-semibold rounded shadow-sm text-rose-500 bg-rose-50"
          >
            <TrashIcon class="w-4" />
            <span>삭제</span>
          </button>
        </div>
      </div>
    </div>
    <!-- no result -->
    <div v-else class="flex items-center justify-center text-zinc-400 h-[300px]">
      <i class="mr-1 text-2xl las la-ban"></i>
      <span class="text-sm">찜 내역이 없습니다.</span>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { getWishList, addWish } from '@/api';
import dayjs from 'dayjs';
import { TrashIcon } from '@heroicons/vue/20/solid';

const route = useRoute();

const wishList = ref([]);

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 HH:mm');
};

const deleteWish = async (wish) => {
  console.log('delete', wish);
  const payload = {
    contentId: wish.contentId,
    contentTypeId: wish.contentTypeId,
    // contentName: detailCommon.value.title,
    // contentImageUrl: detailCommon.value.firstimage,
  };
  console.log(payload);

  try {
    const result = await addWish(payload);
    console.log(result);
    // if (status === 200 && resultMsg) {
    //   alert(resultMsg);
    // }
  } catch (error) {
    console.log(error);
  }
};

const fetchWishList = async () => {
  const payload = {
    pageNo: 1,
    listCnt: 10,
  };

  try {
    const {
      status,
      data: { data },
    } = await getWishList(payload);
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
