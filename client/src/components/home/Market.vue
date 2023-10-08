<template>
  <div class="flex flex-col px-5 my-10">
    <!-- title -->
    <div class="flex items-center justify-between w-full mb-5">
      <h2 class="text-lg font-semibold">{{ $t('homeSubTitleMsg5') }}</h2>
      <!-- <a href="" class="px-1 text-zinc-400">
        <span class="mr-1 text-sm font-medium">내 포인트</span>
        <span class="text-sm font-medium text-black">35,000</span>
      </a> -->
    </div>

    <!-- product list -->
    <ProductList :products="store.market.products" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useMainStore } from '@/store';
import { fetchProducts } from '@/fetch/useDataFetching.js';
import ProductList from '@/components/market/ProductList.vue';

// store
const store = useMainStore();

onMounted(async () => {
  // fetch products -> store
  const { fetchedData, error, loading } = await fetchProducts();
  if (fetchedData) {
    store.market.setProducts(fetchedData.value);
  }
});
</script>
