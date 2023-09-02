<template>
  <ul class="grid grid-cols-2 gap-x-3 gap-y-8">
    <li v-for="item in products" :key="item.pdId" class="h-full">
      <router-link :to="`/market/${item.pdId}`" class="flex flex-col h-full">
        <img :src="storageUrl + item.thumbnailUrl" class="object-cover rounded-lg aspect-square" />
        <dl class="flex flex-col mt-3">
          <dt class="mb-1 text-sm font-medium truncate">{{ item.name }}</dt>
          <dd class="mb-1 text-xs truncate text-zinc-400 break-keep">
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
