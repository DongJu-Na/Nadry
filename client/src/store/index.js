import { createPinia } from 'pinia';
import { useUserStore } from '@/store/user';
import { useMarketStore } from '@/store/market';

const pinia = createPinia();

export const useMainStore = () => {
  const userStore = useUserStore();
  const marketStore = useMarketStore();

  return {
    user: userStore,
    market: marketStore,
  };
};

export default pinia;
