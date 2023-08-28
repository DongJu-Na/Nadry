import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useMarketStore = defineStore(
  'market',
  () => {
    // states
    const products = ref([]);

    // reset
    function $reset() {
      products.value = [];
    }

    // actions
    function setProducts(payload) {
      console.log('store.market.setMarketProducts', payload);
      products.value = payload;
    }

    return {
      // states
      products,
      // actions
      $reset,
      setProducts,
    };
  },
  {
    persist: {
      enabled: true,
      strategies: [{ storage: localStorage }],
    },
  }
);
