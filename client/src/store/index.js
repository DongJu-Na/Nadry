import { createPinia } from 'pinia';
import { useUserStore } from '@/store/user';
import { useMarketStore } from '@/store/market';
import { useStateStore } from '@/store/state';

const pinia = createPinia();

export const useMainStore = () => {
  const userStore = useUserStore();
  const marketStore = useMarketStore();
  const stateStore = useStateStore();

  return {
    user: userStore,
    market: marketStore,
    state: stateStore,
  };
};

export default pinia;
