<template>
  <div class="px-5">
    <div class="mt-5 mb-10 text-2xl text-center">
      <p class="">{{ $t('menuHelloTxt1') }}</p>
      <p class="font-semibold text-emerald-500">{{ $t('menuHelloTxt2') }}</p>
    </div>
    <!-- user info -->
    <div v-if="store.user.isLoggedIn" class="flex flex-col items-center justify-center mb-10">
      <div
        class="w-[80px] h-[80px] bg-zinc-100 rounded-full flex justify-center itencen overflow-hidden"
      >
        <img
          v-if="user.profileUrl"
          :src="user.profileUrl.includes('http') ? user.profileUrl : storageUrl + user.profileUrl"
        />
        <UserIcon v-else class="w-10 opacity-10" />
      </div>
      <div class="mt-3">{{ user.name }}</div>
      <div class="text-sm text-zinc-400">{{ user.email }}</div>
    </div>

    <!-- menu -->
    <nav class="menu">
      <template v-if="!store.user.isLoggedIn">
        <router-link to="/login">{{ $t('menuLogin') }}</router-link>
        <router-link to="/join">{{ $t('menuRegister') }}</router-link>
      </template>
      <router-link v-if="store.user.isLoggedIn" to="/logout">{{ $t('menuLogout') }}</router-link>
    </nav>

    <div class="grid grid-cols-3 gap-2 mt-8">
      <div>
        <button
          @click="$i18n.locale = 'Kor'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#f5f5f5" d="M2,10h44v30H2V10z"></path>
            <path
              fill="#263238"
              d="M9.4 17.6H17.4V18.3H9.4z"
              transform="rotate(-57.016 13.39 17.965)"
            ></path>
            <path
              fill="#263238"
              d="M9.9 30.1H10.700000000000001V38.1H9.9z"
              transform="rotate(-32.934 10.278 34.086)"
            ></path>
            <path
              fill="#263238"
              d="M35.8 12.9H36.599999999999994V21H35.8z"
              transform="rotate(-33.112 36.177 16.925)"
            ></path>
            <path
              fill="#263238"
              d="M7.8 16.6H15.8V17.3H7.8z"
              transform="rotate(-57.016 11.833 16.951)"
            ></path>
            <path
              fill="#263238"
              d="M35.4 17.9H36.1V21.599999999999998H35.4z"
              transform="rotate(-32.965 35.796 19.773)"
            ></path>
            <path
              fill="#263238"
              d="M36.9 31.9H40.6V32.6H36.9z"
              transform="rotate(-57.016 38.797 32.27)"
            ></path>
            <path
              fill="#263238"
              d="M12.6 33H13.299999999999999V36.7H12.6z"
              transform="rotate(-33.039 13.01 34.891)"
            ></path>
            <path
              fill="#263238"
              d="M38.5 15.9H39.2V19.6H38.5z"
              transform="rotate(-33.238 38.923 17.743)"
            ></path>
            <path
              fill="#263238"
              d="M33.8 29.9H37.5V30.599999999999998H33.8z"
              transform="rotate(-57.016 35.684 30.243)"
            ></path>
            <path
              fill="#263238"
              d="M35.4 30.9H39.1V31.7H35.4z"
              transform="rotate(-56.886 37.242 31.256)"
            ></path>
            <path
              fill="#263238"
              d="M33.1 14.3H33.800000000000004V18H33.1z"
              transform="rotate(-33.217 33.437 16.136)"
            ></path>
            <path
              fill="#263238"
              d="M34.6 35.5H38.300000000000004V36.2H34.6z"
              transform="rotate(-57.344 36.44 35.908)"
            ></path>
            <path
              fill="#263238"
              d="M10.3 29.4H11.100000000000001V33.1H10.3z"
              transform="rotate(-32.765 10.648 31.258)"
            ></path>
            <path
              fill="#263238"
              d="M36.2 12.3H36.900000000000006V16H36.2z"
              transform="rotate(-33.269 36.548 14.107)"
            ></path>
            <path
              fill="#263238"
              d="M31.5 33.5H35.2V34.3H31.5z"
              transform="rotate(-56.886 33.312 33.877)"
            ></path>
            <path
              fill="#263238"
              d="M33 34.5H36.7V35.3H33z"
              transform="rotate(-57.222 34.878 34.889)"
            ></path>
            <path
              fill="#263238"
              d="M6.2 15.6H14.3V16.4H6.2z"
              transform="rotate(-56.845 10.264 15.938)"
            ></path>
            <path
              fill="#263238"
              d="M13 28H13.7V36H13z"
              transform="rotate(-32.948 13.39 32.058)"
            ></path>
            <path
              fill="#f44336"
              d="M20,29c2.2,0,4-1.8,4-4s1.8-4,4-4s4,1.8,4,4c0-4.4-3.6-8-8-8s-8,3.6-8,8C16,27.2,17.8,29,20,29z"
            ></path>
            <path
              fill="#3949ab"
              d="M28,21c-2.2,0-4,1.8-4,4s-1.8,4-4,4s-4-1.8-4-4c0,4.4,3.6,8,8,8s8-3.6,8-8C32,22.8,30.2,21,28,21z"
            ></path>
          </svg>
          한국어
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Eng'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#ECEFF1" d="M1.998 10H45.998V37H1.998z"></path>
            <path
              fill="#F44336"
              d="M2 10H46V13H2zM2 16H46V19H2zM2 22H46V25H2zM2 28H46V31H2zM2 34H46V37H2z"
            ></path>
            <path fill="#3F51B5" d="M2 10H23V25H2z"></path>
            <g>
              <path
                fill="#FFF"
                d="M4.25 12L4.713 12.988 5.75 13.146 5 13.916 5.178 15 4.25 14.488 3.322 15 3.5 13.916 2.75 13.146 3.787 12.988zM8.25 12L8.713 12.988 9.75 13.146 9 13.916 9.178 15 8.25 14.488 7.322 15 7.5 13.916 6.75 13.146 7.787 12.988zM12.25 12L12.713 12.988 13.75 13.146 13 13.916 13.178 15 12.25 14.488 11.322 15 11.5 13.916 10.75 13.146 11.787 12.988zM16.25 12L16.713 12.988 17.75 13.146 17 13.916 17.178 15 16.25 14.488 15.322 15 15.5 13.916 14.75 13.146 15.787 12.988zM20 12L20.463 12.988 21.5 13.146 20.75 13.916 20.928 15 20 14.488 19.072 15 19.25 13.916 18.5 13.146 19.537 12.988zM4.25 20L4.713 20.988 5.75 21.146 5 21.916 5.178 23 4.25 22.488 3.322 23 3.5 21.916 2.75 21.146 3.787 20.988zM8.25 20L8.713 20.988 9.75 21.146 9 21.916 9.178 23 8.25 22.488 7.322 23 7.5 21.916 6.75 21.146 7.787 20.988zM12.25 20L12.713 20.988 13.75 21.146 13 21.916 13.178 23 12.25 22.488 11.322 23 11.5 21.916 10.75 21.146 11.787 20.988zM16.25 20L16.713 20.988 17.75 21.146 17 21.916 17.178 23 16.25 22.488 15.322 23 15.5 21.916 14.75 21.146 15.787 20.988zM20 20L20.463 20.988 21.5 21.146 20.75 21.916 20.928 23 20 22.488 19.072 23 19.25 21.916 18.5 21.146 19.537 20.988zM5.25 16L5.713 16.988 6.75 17.146 6 17.916 6.178 19 5.25 18.488 4.322 19 4.5 17.916 3.75 17.146 4.787 16.988zM9.25 16L9.713 16.988 10.75 17.146 10 17.916 10.178 19 9.25 18.488 8.322 19 8.5 17.916 7.75 17.146 8.787 16.988zM13.25 16L13.713 16.988 14.75 17.146 14 17.916 14.178 19 13.25 18.488 12.322 19 12.5 17.916 11.75 17.146 12.787 16.988zM17.25 16L17.713 16.988 18.75 17.146 18 17.916 18.178 19 17.25 18.488 16.322 19 16.5 17.916 15.75 17.146 16.787 16.988zM21 16L21.463 16.988 22.5 17.146 21.75 17.916 21.928 19 21 18.488 20.072 19 20.25 17.916 19.5 17.146 20.537 16.988z"
              ></path>
            </g>
          </svg>
          English
        </button>
      </div>

      <div>
        <button
          @click="$i18n.locale = 'Chs'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#FF3D00" d="M2 9L2 24 2 39 46 39 46 24 46 9z"></path>
            <path
              fill="#FFEB3B"
              d="M10 13.49L11.181 17.24 15 17.24 11.91 19.367 13.09 23 10 20.755 6.91 23 8.09 19.367 5 17.24 8.819 17.24zM17.474 10.976L17.828 12.101 18.974 12.101 18.047 12.737 18.401 13.828 17.474 13.154 16.547 13.828 16.901 12.737 15.974 12.101 17.12 12.101zM17.5 24L17.854 25.125 19 25.125 18.073 25.762 18.427 26.853 17.5 26.179 16.573 26.853 16.927 25.762 16 25.125 17.146 25.125zM21.5 15L21.854 16.125 23 16.125 22.073 16.762 22.427 17.853 21.5 17.179 20.573 17.853 20.927 16.762 20 16.125 21.146 16.125zM21.5 20L21.854 21.125 23 21.125 22.073 21.762 22.427 22.853 21.5 22.179 20.573 22.853 20.927 21.762 20 21.125 21.146 21.125z"
            ></path>
          </svg>
          中文-简体
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Cht'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#FF3D00" d="M2 9L2 24 2 39 46 39 46 24 46 9z"></path>
            <path
              fill="#FFEB3B"
              d="M10 13.49L11.181 17.24 15 17.24 11.91 19.367 13.09 23 10 20.755 6.91 23 8.09 19.367 5 17.24 8.819 17.24zM17.474 10.976L17.828 12.101 18.974 12.101 18.047 12.737 18.401 13.828 17.474 13.154 16.547 13.828 16.901 12.737 15.974 12.101 17.12 12.101zM17.5 24L17.854 25.125 19 25.125 18.073 25.762 18.427 26.853 17.5 26.179 16.573 26.853 16.927 25.762 16 25.125 17.146 25.125zM21.5 15L21.854 16.125 23 16.125 22.073 16.762 22.427 17.853 21.5 17.179 20.573 17.853 20.927 16.762 20 16.125 21.146 16.125zM21.5 20L21.854 21.125 23 21.125 22.073 21.762 22.427 22.853 21.5 22.179 20.573 22.853 20.927 21.762 20 21.125 21.146 21.125z"
            ></path>
          </svg>
          中文-繁體
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Jpn'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#eceff1" d="M2 9H46V39H2z"></path>
            <path fill="#d50000" d="M24 15A9 9 0 1 0 24 33A9 9 0 1 0 24 15Z"></path>
          </svg>
          日本語
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Ger'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#FFC107" d="M2 29H46V39H2z"></path>
            <path fill="#FF3D00" d="M2 19H46V29H2z"></path>
            <path fill="#455A64" d="M2 9H46V19H2z"></path>
          </svg>
          Deutsch
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Fre'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#ECEFF1" d="M16 9H32V39H16z"></path>
            <path fill="#FF3D00" d="M32 9H46V39H32z"></path>
            <path fill="#3F51B5" d="M2 9H16V39H2z"></path>
          </svg>
          Français
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Spn'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#DD2C00" d="M2 9H46V39H2z"></path>
            <path fill="#FFC107" d="M2 17H46V31H2z"></path>
          </svg>
          Español
        </button>
      </div>
      <div>
        <button
          @click="$i18n.locale = 'Rus'"
          type="button"
          class="flex w-full h-10 gap-1 px-3 text-xs text-gray-700 border"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 48 48"
          >
            <path fill="#FF3D00" d="M2 29H46V39H2z"></path>
            <path fill="#3F51B5" d="M2 19H46V29H2z"></path>
            <path fill="#E0E1E2" d="M2 9H46V19H2z"></path>
          </svg>
          Русский
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useMainStore } from '@/store';
import { UserIcon } from '@heroicons/vue/20/solid';

const store = useMainStore();
const storageUrl = import.meta.env.VITE_APP_STORAGE_URL;

const user = computed(() => {
  return store.user.userInfo;
});
</script>
