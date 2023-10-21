<template>
  <div
    class="flex items-center justify-between gap-2 mt-10 text-sm text-zinc-500"
    v-if="items && items.length > 0"
  >
    <div
      @click="searchPage('prev')"
      :disabled="pageInfo.pageNo === 1"
      class="flex items-center justify-center flex-1 border rounded border-zinc-300 h-9"
      :class="[pageInfo.pageNo === 1 ? 'opacity-30' : '']"
    >
      <span class="">{{ $t('pagePrev') }}</span>
    </div>
    <div
      v-for="item in displayedPages()"
      :key="item"
      @click="searchPage(item)"
      :class="{ 'bg-emerald-500 text-white border-none font-semibold ': item === pageInfo.pageNo }"
      class="flex items-center justify-center flex-1 border rounded border-zinc-300 h-9"
    >
      <span>{{ item }}</span>
    </div>
    <div
      @click="searchPage('next')"
      :disabled="pageInfo.totalCount <= pageInfo.pageNo * 10"
      class="flex items-center justify-center flex-1 border rounded border-zinc-300 h-9"
      :class="[pageInfo.totalCount <= pageInfo.pageNo * 10 ? 'opacity-30' : '']"
    >
      <span class="">{{ $t('pageNext') }}</span>
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

  if (props.pageInfo.totalCount <= 10) {
    return [1];
  }
  if (props.pageInfo.totalCount <= 10) {
    return Array.from({ length: props.pageInfo.totalCount }, (_, index) => index + 1);
  }

  try {
    startPage = Math.max(1, props.pageInfo.pageNo - Math.floor(5 / 2));
    endPage = Math.min(startPage + 5 - 1, Math.ceil(props.pageInfo.totalCount / 10));
    console.log(startPage, endPage);
  } catch {
    return [];
  }
  // console.log(Array.from({ length: endPage - startPage + 1 }, (_, index) => startPage + index));
  return Array.from({ length: endPage - startPage + 1 }, (_, index) => startPage + index);
};

const searchPage = (page) => {
  if (page === 'prev') {
    if (props.pageInfo.pageNo === 1) {
      return false;
    }
    emit('prevPage');
    // props.pageInfo.value.pageNo = props.pageInfo.value.pageNo - 1;
  } else if (page === 'next') {
    if (props.pageInfo.totalCount < 10) {
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
