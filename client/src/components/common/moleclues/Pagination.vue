<template>
  <div class="flex items-center justify-between mt-10" v-if="items && items.length > 0">
    <div @click="searchPage('prev')" :disabled="pageInfo.pageNo === 1">
      <span class="">이전</span>
    </div>
    <div
      v-for="item in displayedPages()"
      :key="item"
      @click="searchPage(item)"
      :class="{ 'text-rose-700 ': item === pageInfo.pageNo }"
    >
      <span>{{ item }}</span>
    </div>
    <div @click="searchPage('next')" :disabled="pageInfo.totalCount === pageInfo.pageNo">
      <span class="">다음</span>
    </div>
  </div>
</template>

<script setup>
import { computed, defineEmits } from 'vue';

const props = defineProps({
  items: {
    type: Array,
  },
  pageInfo: {
    type: Object,
  },
});

const emit = defineEmits(['prevPage', 'nextPage', 'changePage', 'fetch']);

const displayedPages = () => {
  let startPage;
  let endPage;

  if(props.pageInfo.totalCount < 5){
    return [1];
  }

  try {
    startPage = Math.max(1, props.pageInfo.pageNo - Math.floor(5 / 2));
    endPage = Math.min(startPage + 5 - 1, props.pageInfo.totalCount);
  } catch {
    return [];
  }
  //console.log(Array.from({ length: endPage - startPage + 1 }, (_, index) => startPage + index));
  return Array.from({ length: endPage - startPage + 1 }, (_, index) => startPage + index);
};

const searchPage = (page) => {
  if (page === 'prev') {
    if(props.pageInfo.pageNo === 1){
      return false;
    }
    emit('prevPage');
    // props.pageInfo.value.pageNo = props.pageInfo.value.pageNo - 1;
  } else if (page === 'next') {
    if(props.pageInfo.totalCount < 5){
      return false;
    }
    emit('nextPage');
    // props.pageInfo.value.pageNo = props.pageInfo.value.pageNo + 1;
  } else {
    emit('changePage', page);
    // props.pageInfo.value.pageNo = page;
  }
  emit('fetch');
};
</script>
