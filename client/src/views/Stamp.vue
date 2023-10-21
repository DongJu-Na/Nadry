<template>
  <div class="mt-[80px] px-5 mb-[50px]">
    <h1>test</h1>
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
