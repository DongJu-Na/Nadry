<template>
  <div class="flex justify-center items-center text-zinc-300 h-full text-xs">
    
 <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3 text-center w-24">
                    <span class="sr-only">Image</span>
                </th>
                <th scope="col" class="px-6 py-3 text-center">
                    상품명
                </th>
                <th scope="col" class="px-6 py-3 text-center">
                    포인트
                </th>
                <th scope="col" class="px-6 py-3 text-center">
                    주문일자
                </th>
            </tr>
        </thead>
        <tbody>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
              v-for="order in orders"
              :key="order.odId"
            >
                <td class="w-32 p-4">
                    <router-link :to="`/market/${order.pdId}`">
                      <img :src="storageUrl + order.thumbnailUrl" class="rounded-md w-80" />
                     </router-link>
                </td>
                <td class="px-5 py-4 text-center">
                    {{order.name}}
                </td>
                <td class="px-4 py-4 text-center">
                    {{order.point.toLocaleString('ko-KR')}}
                </td>
                <td class="px-4 py-4 text-center">
                    <div class="flex items-center">
                        {{transDate(order.orderDate)}}
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
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
