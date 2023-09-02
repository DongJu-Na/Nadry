<template>
  <Loading />
  <!-- header -->
  <Header v-if="showHeader" />
  <!-- sub header -->
  <SubHeader v-else />
  <!-- bottom tab -->
  <BottomTab />
  <!-- main -->
  <main :class="{ 'pt-[80px]': showHeader }" class="flex flex-col flex-1 min-h-screen">
    <router-view />
  </main>
  <!-- footer -->
  <Footer />
</template>

<script setup>
import { ref, watch, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Header from '@/components/layout/Header.vue';
import SubHeader from '@/components/layout/SubHeader.vue';
import Footer from '@/components/layout/Footer.vue';
import BottomTab from '@/components/layout/BottomTab.vue';
import Loading from '@/components/common/templates/Loading.vue';

const route = useRoute();
const router = useRouter();

const showHeader = ref(true);

// 라우드 변경 시: route.meta.header 값에 따라 showHeader 결정
// 보통 서브페이지의 경우 기본 header 대신 해당 route의 제목과 뒤로가기 버튼 출력
watch(route, async () => {
  await router.isReady();
  if (route.meta.header !== undefined && route.meta.header === false) {
    showHeader.value = false;
  } else {
    showHeader.value = true;
  }
});

// 모바일 브라우저 100vh 이슈 해결
// 실제 height를 1/100 해서 property로 저장
const setViewHeight = () => {
  let vh = window.innerHeight * 0.01;
  document.documentElement.style.setProperty('--vh', `${vh}px`);
};

onMounted(() => {
  setViewHeight();
  window.addEventListener('resize', () => {
    setViewHeight();
  });
});
</script>
