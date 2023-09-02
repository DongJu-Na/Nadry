<template>
  <svg
    xmlns="http://www.w3.org/2000/svg"
    viewBox="0 0 24 24"
    :width="size + 'px'"
    :height="size + 'px'"
    :fill="color"
  >
    <path
      d="M12,2C6.486,2,2,6.038,2,11c0,5.259,5.04,9.508,11,8.953V22c0.02-0.007,0,1,1,0.451l0.54-0.346 c1.132-0.726,6.807-4.628,7.403-10.15C21.98,11.641,22,11.322,22,11C22,6.038,17.514,2,12,2z"
    />
  </svg>
</template>

<script setup>
const props = defineProps({
  color: {
    type: String,
    default: '#333',
  },
  size: {
    type: Number,
    default: 24,
  },
});
</script>
