<template>
  <div v-if="product">
    <dl>
      <dt class="mb-1 text-sm font-medium text-zinc-400">{{ product.shortDescription }}</dt>
      <dd class="text-xl font-bold">{{ product.name }}</dd>
    </dl>
    <div class="flex items-center gap-1 mt-3">
      <span
        class="bg-lime-500 w-[16px] h-[16px] leading-none flex justify-center items-center rounded-full text-2xs text-white"
        >P</span
      >
      <span class="font-medium">{{ product.point }}</span>
    </div>
    <ul class="mt-3 text-sm font-medium text-zinc-400">
      <li class="flex gap-5">
        <div>상품 형태</div>
        <div>{{ product.productType }}</div>
      </li>
    </ul>
    <button class="mt-5" @click="clickOrder">주문하기</button>
    <div class="mt-[3rem]">
      <img :src="storageUrl + product.thumbnailUrl" class="rounded-md" />
    </div>
    <div class="mt-[2rem] break-keep whitespace-pre-line leading-[1.5] text-zinc-600">
      {{ product.description }}
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { onMounted, ref } from 'vue';
import { addOrder } from '@/api';
import { useMainStore } from '@/store';

// store
const store = useMainStore();

// route
const route = useRoute();
const router = useRouter();

// product detail
const product = ref(null);
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const getProductDetail = (id) => {
  let product = null;
  store.market.products.forEach((item) => {
    if (item.pdId == id) product = item;
  });
  return product;
};

const checkLogin = () => {
    if (!store.user.isLoggedIn) {
      alert('로그인이 필요합니다.');
      router.push('/login');
      return false;  
    }
    return true;
};

const clickOrder = async () => {
  if(!checkLogin()){
    return false;
  }
  
  // console.log(detailCommon.value.title, detailCommon.value.firstimage);
  const payload = {
    pdId : route.params.id
  };

  console.log(payload);

    try {
      const {
        status,
        data: { data, resultMsg },
      } = await addOrder(payload);
      // console.log(data, resultMsg);
      if (status === 200 ) {
        alert(resultMsg);
        console.log(resultMsg);
      }
    } catch (error) {
      console.log(error);
    }
  };

onMounted(async () => {
  await router.isReady();

  if (route.params.id) {
    product.value = getProductDetail(route.params.id);
  }
});
</script>
