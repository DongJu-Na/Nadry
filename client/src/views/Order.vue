<template>
  <div class="mt-[80px] px-5 mb-[50px]">
    <div v-if="isLoaded && orders.length > 0" class="flex flex-col gap-5">
      <div v-for="item in orders" class="flex items-center w-full gap-3">
        <img
          :src="`${storageUrl}${item.thumbnailUrl}`"
          class="w-[100px] aspect-square object-cover rounded"
        />
        <div class="flex-1 w-full overflow-hidden">
          <div class="mb-2 font-semibold truncate">{{ item.name }}</div>
          <div class="flex items-center gap-1 mb-1">
            <span
              class="bg-rose-500 w-[16px] h-[16px] leading-none flex justify-center items-center rounded-full text-2xs text-white"
              >P</span
            >
            <span class="text-sm font-medium">{{ item.point.toLocaleString('ko-KR') }} 사용</span>
          </div>
          <div class="text-sm text-zinc-400">{{ transDate(item.orderDate) }}</div>
        </div>
      </div>
    </div>
    <div v-else class="py-10 text-sm text-center text-zinc-400">주문 내역이 없습니다.</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getMyOrderList } from '@/api';
import dayjs from 'dayjs';

const router = useRouter();
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;
const orders = ref(null);
const isLoaded = ref(false);

const fetchMyOrder = async () => {
  try {
    const {
      status,
      data: { data },
    } = await getMyOrderList();
    console.log('fetchMyOrders: ', data);
    if (status === 200 && data) {
      console.log(data);
      orders.value = data;
      isLoaded.value = true;
    }
  } catch (error) {
    console.log(error);
  }
};

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 hh:mm');
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchMyOrder();
});
</script>

<style lang="scss" scoped></style>
