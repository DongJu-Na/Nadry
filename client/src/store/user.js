import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore(
  'user',
  () => {
    // states
    const isLoggedIn = ref(false);
    const token = ref(null);

    const userInfo = ref({
      name: null,
      teamId: null,
      teamName: null,
      teamDescription: null,
    });

    // reset
    function $reset() {
      isLoggedIn.value = false;
      token.value = null;
      userInfo.value = {
        name: null,
        teamName: null,
        teamDescription: null,
      };
    }

    // actions
    function userLogin() {
      isLoggedIn.value = true;
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
      userLogin,
      userLogout,
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
