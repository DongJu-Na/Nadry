<template>
  <div v-if="isLoaded" class="mt-[80px] px-5 mb-[50px]">
    <h1 class="mb-1 text-2xl font-semibold text-center text-emerald-600">대한민국 여행 지도</h1>
    <p class="text-sm text-center text-zinc-400">대한민국을 여행하고, 인증 스탬프를 모아보세요!</p>
    <div class="grid grid-cols-3 gap-2 mt-8">
      <div
        v-for="(item, index) in stamps"
        :key="item.id"
        class="relative flex flex-col items-center justify-center"
      >
        <div class="relative">
          <div
            class="absolute w-[27px] h-[28px] flex justify-center items-center text-white font-bold text-sm roboto"
          >
            {{ index + 1 }}
          </div>
          <img
            :src="`/img/${item.id}.png`"
            :class="[checkItem(item) ? '' : 'grayscale opacity-20']"
          />
        </div>
        <div
          v-if="checkItem(item)"
          class="absolute text-white bottom-[5px] text-xs tracking-widest roboto"
        >
          {{ getDate(item) }}
        </div>
        <div v-else class="absolute text-zinc-400 bottom-[5px] text-xs">어서오세요!</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getMyStampList } from '@/api';
import dayjs from 'dayjs';

const router = useRouter();

const myStamps = ref(null);
const isLoaded = ref(false);

const stamps = [
  {
    id: 1,
    name: '서울',
  },
  {
    id: 2,
    name: '인천',
  },
  {
    id: 3,
    name: '대전',
  },
  {
    id: 4,
    name: '대구',
  },
  {
    id: 5,
    name: '광주',
  },
  {
    id: 6,
    name: '부산',
  },
  {
    id: 7,
    name: '울산',
  },
  {
    id: 8,
    name: '세종특별자치시',
  },
  {
    id: 9,
    name: '경기도',
  },
  {
    id: 10,
    name: '강원특별자치도',
  },
  {
    id: 11,
    name: '충청북도',
  },
  {
    id: 12,
    name: '충청남도',
  },
  {
    id: 13,
    name: '경상북도',
  },
  {
    id: 14,
    name: '경상남도',
  },
  {
    id: 15,
    name: '전라북도',
  },
  {
    id: 16,
    name: '전라남도',
  },
  {
    id: 17,
    name: '제주도',
  },
];

const checkItem = (item) => {
  const filtered = myStamps.value.filter((stamp) => stamp.stmId === item.id);
  if (filtered.length > 0) {
    return true;
  } else {
    return false;
  }
};

const getDate = (item) => {
  const filtered = myStamps.value.filter((stamp) => stamp.stmId === item.id);
  return transDate(filtered[0].stampCollectDate);
};

const fetchMyStamps = async () => {
  try {
    const {
      status,
      data: { data },
    } = await getMyStampList();
    console.log('fetchMyStamps: ', data);
    if (status === 200 && data) {
      myStamps.value = data;
      isLoaded.value = true;
    }
  } catch (error) {
    console.log(error);
  }
};

const transDate = (date) => {
  return dayjs(date).format('YYYY/MM/DD');
};

// 문서 로딩 완료 시 : fetch data
onMounted(async () => {
  await router.isReady();
  fetchMyStamps();
});
</script>

<style lang="scss" scoped></style>
