<template>
  <div class="flex justify-center items-center text-zinc-300 h-full text-xs">
    
 <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">
                    스탬프명
                </th>
                <th scope="col" class="px-6 py-3">
                    설명
                </th>
                <th scope="col" class="px-6 py-3">
                    수집일자
                </th>
            </tr>
        </thead>
        <tbody>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
              v-for="stamp in myStamps"
              :key="stamp.stId"
            >
                <td scope="row" class="flex items-center px-6 py-4 text-gray-900 whitespace-nowrap dark:text-white">
                    <img class="w-10 h-10 rounded-full" :src="storageUrl + stamp.stampImageUrl" alt="stamp">
                </td>
                <td class="px-6 py-4">
                    {{stamp.stampName}}
                </td>
                <td class="px-6 py-4">
                    <div class="flex items-center">
                        {{transDate(stamp.stampCollectDate)}}
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
import { getMyStampList } from '@/api';
import dayjs from 'dayjs';

const router = useRouter();
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;
const myStamps = ref(null);

const fetchMyStamps = async () => {
  try {
    const {
      status,
      data: { data },
    } = await getMyStampList();
    console.log('fetchMyStamps: ', data);
    if (status === 200 && data) {
      console.log(data);
      myStamps.value = data;
    }
  } catch (error) {
    console.log(error);
  }
};

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 HH:mm');
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchMyStamps();
});
</script>

<style lang="scss" scoped></style>
