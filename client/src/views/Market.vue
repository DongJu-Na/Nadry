<template>
  <div class="mt-[80px] px-5">
    <!-- product list -->
    <div>{{ products }}</div>
  </div>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue';
import { getProducts } from '@/api';

const products = ref(null);

const fetchProducts = async () => {
  try {
    const {
      status,
      data: { data },
    } = await getProducts();
    console.log(status, data);
    if (status === 200 && data) {
      products.value = data;
    }
  } catch (error) {
    console.log(error);
  }
};

onBeforeMount(async () => {
  await fetchProducts();
});
</script>
