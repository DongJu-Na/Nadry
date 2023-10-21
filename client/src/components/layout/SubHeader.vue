<template>
  <header
    ref="header"
    class="fixed top-0 flex justify-between items-center w-full h-[60px] z-[100] transition-all duration-300"
    :style="`width:var(--app-width)`"
  >
    <!-- back button -->
    <a @click="back" class="flex items-center p-[18px] text-sm">
      <i class="text-2xl las la-angle-left"></i>
      <span>뒤로</span>
    </a>
    <!-- title -->
    <h1 class="absolute left-0 right-0 mx-auto text-sm font-semibold w-fit">
      {{ title }}
    </h1>
    <!-- menu -->
    <router-link to="/menu" class="block p-[18px]">
      <IconMenu />
    </router-link>
  </header>
</template>

<script setup>
import { onMounted, ref, onUnmounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import IconMenu from '@/components/common/icons/IconMenu.vue';

const router = useRouter();
const route = useRoute();

const title = ref(null);

const header = ref(null);

// 뒤로가기
const back = () => {
  router.go(-1);
};

// 화면 스크롤 : 상단 바에 bg-white 클래스 추가
const watchScrollY = () => {
  if (window.scrollY > 100) {
    header.value.classList.add('bg-white');
  } else {
    header.value.classList.remove('bg-white');
  }
};

watch(route, () => {
  if (route.meta.title) {
    title.value = route.meta.title;
  }
});

onMounted(async () => {
  await router.isReady();

  // route.meta.title 존재 시: 상단 바에 타이틀 출력
  if (route.meta.title) {
    title.value = route.meta.title;
  }
  // window scroll 이벤트 추가
  window.addEventListener('scroll', watchScrollY);
});

onUnmounted(() => {
  // window scroll 이벤트 제거
  window.removeEventListener('scroll', watchScrollY);
});
</script>
