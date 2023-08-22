<template>
  <!-- header -->
  <Header v-if="showHeader" />
  <!-- bottom tab -->
  <BottomTab />
  <!-- main -->
  <main class="mt-[80px]">
    <router-view />
  </main>
  <!-- footer -->
  <Footer />
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Header from './components/layout/Header.vue';
import Footer from './components/layout/Footer.vue';
import BottomTab from './components/layout/BottomTab.vue';

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
</script>
