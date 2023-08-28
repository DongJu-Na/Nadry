<template>
  <ul class="grid grid-cols-2 gap-x-3 gap-y-8">
    <li v-for="item in products" :key="item.pdId" class="h-full">
      <router-link :to="`/market/${item.pdId}`" class="flex flex-col h-full">
        <img :src="storageUrl + item.thumbnailUrl" class="aspect-square object-cover rounded-lg" />
        <dl class="flex flex-col mt-3">
          <dt class="text-sm font-medium mb-1 truncate">{{ item.name }}</dt>
          <dd class="text-xs text-zinc-400 break-keep mb-1 truncate">
            {{ item.shortDescription }}
          </dd>
          <dd class="flex items-center gap-1 mt-auto">
            <span
              class="bg-lime-500 w-[16px] h-[16px] leading-none flex justify-center items-center rounded-full text-2xs text-white"
              >P</span
            >
            <span class="font-medium">{{ item.point }}</span>
          </dd>
        </dl>
      </router-link>
    </li>
  </ul>
</template>

<script setup>
defineProps({
  products: Array,
});

const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;
</script>
