<template>
  <div class="flex flex-col px-5 my-20">
    <!-- title -->
    <div class="flex justify-between items-center w-full mb-5">
      <h2 class="text-lg font-semibold">마켓</h2>
      <a href="" class="text-zinc-400 px-1">
        <span class="text-sm font-medium mr-1">내 포인트</span>
        <span class="text-sm font-medium text-black">35,000</span>
      </a>
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
