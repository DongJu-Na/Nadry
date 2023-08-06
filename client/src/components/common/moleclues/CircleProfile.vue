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
      class="aspect-square object-cover rounded-full"
      src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80"
      :width="props.size"
    />
  </div>
</template>

<script setup>
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
</script>
