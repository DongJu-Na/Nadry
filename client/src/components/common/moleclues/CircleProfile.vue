<template>
  <div class="relative">
    <!-- alarm -->
    <div
      class="absolute top-[-5px] right-[-5px] flex justify-center items-center bg-emerald-500 w-5 h-5 rounded-full text-xs font-semibold text-white"
    >
      {{ alarm }}
    </div>
    <!-- profile image -->
    <img
      ref="profileImage"
      class="object-cover rounded-full aspect-square"
      src=""
      :width="props.size"
    />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useMainStore } from '@/store';
const store = useMainStore();

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
