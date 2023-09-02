import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useStateStore = defineStore(
  'state',
  () => {
    // states
    const loading = ref(false);

    // actions
    function setLoading(payload) {
      loading.value = payload;
    }

    return {
      // states
      loading,
      // actions
      setLoading,
    };
  },
  {
    persist: {
      enabled: false,
      strategies: [{ storage: localStorage }],
    },
  }
);
