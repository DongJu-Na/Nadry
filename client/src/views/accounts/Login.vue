<template>
  <div class="px-5">
    <h2 class="font-medium text-xl">로그인</h2>
    <p class="text-zinc-400 text-sm mb-5">이메일과 비밀번호를 입력하세요</p>
    <form @submit.prevent="login">
      <!-- email -->
      <div class="mb-3">
        <label class="block mb-1 text-sm text-zinc-600">이메일</label>
        <input
          v-model="email"
          type="email"
          class="input"
          placeholder="이메일 입력"
          autocomplete="0"
        />
      </div>
      <!-- password -->
      <div class="mb-5">
        <label class="block mb-1 text-sm text-zinc-600">비밀번호</label>
        <input
          v-model="password"
          type="password"
          class="input"
          placeholder="비밀번호 입력"
          autocomplete="0"
        />
      </div>
      <!-- button -->
      <button>
        <ButtonSpinner v-if="isLoading" />
        <span v-else>로그인</span>
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { userLogin } from '@/api';
import { useMainStore } from '@/store';
import { useRouter } from 'vue-router';
import ButtonSpinner from '@/components/common/atoms/ButtonSpinner.vue';

const store = useMainStore();
const router = useRouter();

const email = ref(null);
const password = ref(null);
const isLoading = ref(false);

// 회원 로그인
const login = async () => {
  try {
    const {
      status,
      data: { data },
    } = await userLogin({ email: email.value, password: password.value });
    isLoading.value = true;
    // status 200 & data 존재 시
    if (status === 200 && data) {
      console.log(data);
      isLoading.value = false;
      // user store 저장
      store.user.setUserInfo(data);
      router.push('/');
    }
  } catch (error) {
    console.log(error);
  }
};
</script>
