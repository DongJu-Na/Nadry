<template>
  <div class="join px-5">
    <h2 class="font-medium text-xl">회원 가입</h2>
    <p class="text-zinc-400 text-sm mb-5">아래 정보들을 입력해주세요</p>
    <form @submit.prevent="submit">
      <!-- input -->
      <div v-for="input in inputData" :id="input.id" class="mb-5">
        <label class="block mb-1 text-sm text-zinc-600">{{ input.title }}</label>
        <input
          v-model="input.model.value"
          :type="input.type"
          class="input"
          :placeholder="input.placeholder"
          autocomplete="0"
        />
      </div>
      <!-- button -->
      <button>회원 가입</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { join } from '@/api';

const name = ref(null);
const email = ref(null);
const password = ref(null);
const passwordConfirm = ref(null);
const birthDay = ref(null);
const inputData = [
  {
    id: 1,
    title: '이름',
    type: 'text',
    placeholder: '이름을 입력하세요',
    model: name,
  },
  {
    id: 2,
    title: '이메일',
    type: 'email',
    placeholder: '이메일을 입력하세요',
    model: email,
  },
  {
    id: 3,
    title: '비밀번호',
    type: 'password',
    placeholder: '비밀번호를 입력하세요',
    model: password,
  },
  {
    id: 4,
    title: '비밀번호 확인',
    type: 'password',
    placeholder: '동일한 비밀번호를 입력하세요',
    model: passwordConfirm,
  },
  {
    id: 5,
    title: '생년월일',
    type: 'date',
    placeholder: '생년월일을 입력하세요',
    model: birthDay,
  },
];

const submit = async () => {
  console.log('join');
  console.log(name.value);
  if (!name.value) {
    alert('이름을 입력하세요');
    return;
  }
  if (!email.value) {
    alert('이메일 입력하세요');
    return;
  }
  if (!password.value) {
    alert('비밀번호를 입력하세요');
    return;
  }
  if (!passwordConfirm.value) {
    alert('비밀번호 확인을 입력하세요');
    return;
  }
  if (!birthDay.value) {
    alert('생년월일을 입력하세요');
    return;
  }

  try {
    const result = await join({
      name: name.value,
      email: email.value,
      password: password.value,
      birthDay: birthDay.value,
      role: 'USER',
    });
    console.log(result);
  } catch (error) {
    console.log(error);
  }
};
</script>
