<template>
  <div class="flex justify-center items-center text-zinc-300 h-[500px] text-xs">
    <div></div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { getWishList } from '@/api';

const fetchWishList = async () => {
  try {
    const payload = {
      "pageNo" : 1,
      "listCnt" : 10,
    };

    const result = await getWishList(payload);
    console.log(result);
  } catch (error) {
    console.log(error);
  }
};

onMounted(() => {
  fetchWishList();
});
</script>
