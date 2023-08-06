import axios from 'axios';
import { useMainStore } from '@/store';
import { setInterceptors } from './interceptors';

// token 필요한 instance
const createInstance = () => {
  const instance = axios.create();
  instance.defaults.baseURL = import.meta.env.VITE_APP_API_URL;
  return setInterceptors(instance);
};

// token 필요없는 instance
const createInstanceWithNoAuth = () => {
  const instance = axios.create();
  instance.defaults.baseURL = import.meta.env.VITE_APP_API_URL;
  return instance;
};

const instance = createInstance();
const instanceWithNoAuth = createInstanceWithNoAuth();

// 로그인
export const userLogin = (payload) => {
  return instanceWithNoAuth.post('/api/v1/auth/authenticate', payload);
};

// 회원가입
export const join = (payload) => {
  return instanceWithNoAuth.post('/api/v1/auth/register', payload);
};
