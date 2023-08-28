<template>
  <ProductList :products="store.market.products" />
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
