<template>
  <div class="flex justify-center items-center text-zinc-300 h-full text-xs">
    
 <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-4 py-3">
                    번호
                </th>
                <th scope="col" class="px-5 py-3">
                    사용 포인트
                </th>
                <th scope="col" class="px-5 py-3">
                    잔여 포인트
                </th>
                <th scope="col" class="px-4 py-3">
                    비고
                </th>
                <th scope="col" class="px-4 py-3">
                    사용일자
                </th>
            </tr>
        </thead>
        <tbody>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
              v-for="points in myPoints"
              :key="points.phId"
            >
                <td scope="row" class="flex items-center px-4 py-4 text-gray-900 whitespace-nowrap dark:text-white">
                    {{points.phId}}
                </td>
                <td class="px-5 py-4">
                    {{points.usePoint.toLocaleString('ko-KR')}}
                </td>
                <td class="px-5 py-4">
                    {{points.point.point.toLocaleString('ko-KR')}}
                </td>
                <td class="px-4 py-4">
                    {{points.remarks}}
                </td>
                <td class="px-4 py-4">
                    <div class="flex items-center">
                        {{transDate(points.useDate)}}
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
import { getMyPointHistoryList } from '@/api';
import dayjs from 'dayjs';

const router = useRouter();
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;
const myPoints = ref(null);

const fetchMyPoint = async () => {
  try {
    const {
      status,
      data: { data },
    } = await getMyPointHistoryList();
    console.log('fetchMyPoint: ', data);
    if (status === 200 && data) {
      console.log(data);
      myPoints.value = data;
    }
  } catch (error) {
    console.log(error);
  }
};

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일');
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchMyPoint();
});
</script>

<style lang="scss" scoped></style>
