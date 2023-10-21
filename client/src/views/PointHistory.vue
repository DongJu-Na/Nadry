<template>
  <div class="mt-[80px] px-5 mb-[50px]">
    <div v-if="isLoaded && myPoints.length > 0" class="flex flex-col gap-3">
      <div v-for="item in myPoints" class="flex items-center w-full p-5 rounded-lg bg-zinc-50">
        <div class="flex-1 w-full overflow-hidden">
          <div class="flex flex-col justify-between">
            <div class="flex items-center justify-between mb-2">
              <div class="text-sm">{{ item.remarks }}</div>
              <div class="text-xs text-right text-zinc-400">{{ transDate(item.useDate) }}</div>
            </div>
            <div class="flex items-end justify-between">
              <div class="flex items-center gap-1">
                <span
                  class="bg-lime-500 w-[16px] h-[16px] leading-none flex justify-center items-center rounded-full text-2xs text-white"
                  >P</span
                >
                <span class="text-xl font-medium leading-none">{{
                  item.usePoint.toLocaleString('ko-KR')
                }}</span>
              </div>
              <div class="text-sm text-lime-600">
                잔여 포인트 :
                <span class="font-semibold">
                  {{ item.point.point.toLocaleString('ko-KR') }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="py-10 text-sm text-center text-zinc-400">포인트 내역이 없습니다.</div>
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
const isLoaded = ref(false);

const fetchMyPoint = async () => {
  const payload = {
    page: 1,
    size: 100,
    sort: ['phId,desc'],
  };

  try {
    const {
      status,
      data: { data },
    } = await getMyPointHistoryList(payload);
    console.log('fetchMyPoint: ', data);
    if (status === 200 && data) {
      console.log(data);
      myPoints.value = data;
      isLoaded.value = true;
    }
  } catch (error) {
    console.log(error);
  }
};

const transDate = (date) => {
  return dayjs(date).format('YYYY년 MM월 DD일 hh:mm:ss');
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchMyPoint();
});
</script>

<style lang="scss" scoped></style>
