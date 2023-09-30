<template>
  <div class="relative">
    <!-- alarm -->
    <!-- <div
      class="absolute top-[-3px] right-[-3px] flex justify-center items-center bg-rose-500 w-5 h-5 rounded-full text-xs font-semibold text-white"
    >
      {{ alarm }}
    </div> -->
    <!-- profile image -->
    <div class="flex justify-center overflow-hidden rounded-full bg-zinc-100 w-[60px] h-[60px]">
      <img
        class="object-cover rounded-full aspect-square"
        v-if="store.user.userInfo.profileUrl"
        :src="store.user.userInfo.profileUrl.includes('http') ? store.user.userInfo.profileUrl : storageUrl + store.user.userInfo.profileUrl"
        :width="props.size"
      />
      <UserIcon v-else class="w-[30px] opacity-10" />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useMainStore } from '@/store';
import { UserIcon } from '@heroicons/vue/20/solid';

const store = useMainStore();
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const props = defineProps({
  alarm: {
    default: 0,
    type: Number,
  },
  size: {
    default: 60,
    type: Number,
  },
});

onMounted(() => {
  console.log(store);
});
</script>
