import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore(
  'user',
  () => {
    // states
    const isLoggedIn = ref(false);
    const token = ref(null);

    // user info
    const userInfo = ref(null);

    // reset
    function $reset() {
      isLoggedIn.value = false;
      token.value = null;
      userInfo.value = {
        name: null,
      };
    }

    // actions
    function setUserInfo(payload) {
      isLoggedIn.value = true;
      userInfo.value = payload;
    }

    function userLogout() {
      isLoggedIn.value = false;
    }

    function setAccessToken(value) {
      token.value = value;
    }

    return {
      // states
      isLoggedIn,
      token,
      userInfo,
      // actions
      $reset,
      userLogout,
      setUserInfo,
      setAccessToken,
    };
  },
  {
    persist: {
      enabled: true,
      strategies: [{ storage: localStorage }],
    },
  }
);
