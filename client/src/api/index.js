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

// market products
export const getProducts = () => {
  return instanceWithNoAuth.get('/api/v1/product/list');
};

// 여행지 검색
export const searchTrip = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/searchKeyword1/Kor', payload);
};

// 여행지 상세 조회 - Intro
export const getTripDetailIntro = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailIntro1/Kor', payload);
};
// 여행지 상세 조회 - Info
export const getTripDetailInfo = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailInfo1/Kor', payload);
};
// 여행지 상세 조회 - Image
export const getTripDetailImage = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailImage1/Kor', payload);
};
// 여행지 상세 조회 - Common
export const getTripDetailCommon = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailCommon1/Kor', payload);
};
