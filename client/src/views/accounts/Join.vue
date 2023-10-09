<template>
  <div class="px-5 join">
    <h2 class="text-xl font-medium">{{ $t('joinLabel1') }}</h2>
    <p class="mb-5 text-sm text-zinc-400">{{ $t('joinLabel2') }}</p>
    <form @submit.prevent="submit">
      <!-- input -->
      <div v-for="input in inputData" :id="input.id" class="mb-5">
        <label class="block mb-1 text-sm text-zinc-600">{{ $t(input.labelLang) }}</label>
        <input
          v-model="input.model.value"
          :type="input.type"
          class="input"
          :placeholder="$t(input.placeholder)"
          autocomplete="0"
        />
      </div>
      <!-- profile image -->
      <div>
        <label class="block mb-1 text-sm text-zinc-600">{{$t('joinLabel8')}}</label>
        <div class="flex items-center gap-3">
          <div
            class="flex justify-center overflow-hidden rounded-full bg-zinc-100 w-[50px] h-[50px]"
          >
            <img
              v-if="previewProfileImage"
              :src="previewProfileImage"
              class="object-cover rounded-full aspect-square"
            />
            <UserIcon v-else class="w-[30px] opacity-10" />
          </div>
          <input
            @change="profileInputChange($event)"
            ref="profileInput"
            type="file"
            class="sr-only"
          />
          <div
            @click="clickProfile"
            class="flex items-center gap-1.5 px-4 py-2 text-xs border rounded text-zinc-500"
          >
            <PhotoIcon class="w-4" />
            <span>{{$t('joinFileUploadTxt')}} </span>
          </div>
        </div>
      </div>
      <!-- button -->
      <button class="mt-5">{{$t('joinBtnTxt')}}</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { join } from '@/api';
import { UserIcon, PhotoIcon } from '@heroicons/vue/20/solid';

const router = useRouter();

const name = ref(null);
const email = ref(null);
const password = ref(null);
const passwordConfirm = ref(null);
const birthDay = ref(null);
const profileInput = ref([]);
const profileImage = ref(null);
const previewProfileImage = ref(null);
const inputData = [
  {
    id: 1,
    title: '이름',
    type: 'text',
    placeholder: 'joinPlaceholder1',
    model: name,
    labelLang : "joinLabel3",
  },
  {
    id: 2,
    title: '이메일',
    type: 'email',
    placeholder: 'joinPlaceholder2',
    model: email,
    labelLang : "joinLabel4",
  },
  {
    id: 3,
    title: '비밀번호',
    type: 'password',
    placeholder: 'joinPlaceholder3',
    model: password,
    labelLang : "joinLabel5",
  },
  {
    id: 4,
    title: '비밀번호 확인',
    type: 'password',
    placeholder: 'joinPlaceholder4',
    model: passwordConfirm,
    labelLang : "joinLabel6",
  },
  {
    id: 5,
    title: '생년월일',
    type: 'date',
    placeholder: 'joinPlaceholder5',
    model: birthDay,
    labelLang : "joinLabel7",
  },
];

const clickProfile = () => {
  profileInput.value.click();
};

const profileInputChange = (e) => {
  const files = e.target.files;
  const reader = new FileReader();
  profileImage.value = files;
  reader.onload = (data) => {
    previewProfileImage.value = data.target.result;
  };
  reader.readAsDataURL(files[0]);
};

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
    const { status, data } = await join({
      name: name.value,
      email: email.value,
      password: password.value,
      birthDay: birthDay.value,
      profileImage: profileImage.value ? profileImage.value[0] : null,
      role: 'USER',
    });
    if (status === 200 && data.resultCode !== '0000') {
      alert(data.resultMsg);
    }
    if (status === 200 && data.resultCode == '0000') {
      alert('회원가입이 완료되었습니다.');
      router.push('/login');
    }

    console.log(data);
  } catch (error) {
    console.log(error);
  }
};
</script>
