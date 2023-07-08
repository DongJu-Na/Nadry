import { createPinia } from 'pinia';
import { useUserStore } from '@/store/user';

const pinia = createPinia();

export const useMainStore = () => {
  const userStore = useUserStore();

  return {
    user: userStore,
  };
};

export default pinia;
