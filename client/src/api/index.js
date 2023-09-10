import axios from 'axios';
import { useMainStore } from '@/store';
import { setInterceptors } from './interceptors';

// token 필요한 instance
const createInstance = () => {
  const instance = axios.create();
  if (import.meta.env.MODE === 'development') {
    instance.defaults.baseURL = '/api';
  } else {
    instance.defaults.baseURL = import.meta.env.VITE_APP_API_URL;
  }
  return setInterceptors(instance);
};

// token 필요없는 instance
const createInstanceWithNoAuth = () => {
  const instance = axios.create();
  if (import.meta.env.MODE === 'development') {
    instance.defaults.baseURL = '/api';
  } else {
    instance.defaults.baseURL = import.meta.env.VITE_APP_API_URL;
  }
  return instance;
};

const makeDynamicUrl = (params) => {
  let queryString = Object.entries(params)
    .map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`)
    .join('&');

  if (queryString) {
    queryString = `?${queryString}`;
  }
  return queryString;
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

// 상품 조회 - market products
// param : { "page": 1, "size": 10, "sort": ["name"]} 그냥 객체로 전송 시 모든 데이터 조회
export const getProducts = () => {
  return instanceWithNoAuth.get('/api/v2/product/list');
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

// 여행리뷰 조회
// param : {"contentId" : "2894451" , "page": 1, "size": 10, "sort": ["name"]}  contentId는 필수  , page 는 생략 시 contentId로 모든 데이터 조회
export const getTripReview = (payload) => {
  return instanceWithNoAuth.get('/api/v2/review/list' + makeDynamicUrl(payload));
};

// 여행리뷰 등록
// param : {"rvId" : "" , "contentId": "2894451", "content": "먹태깡 재고가 많아서 좋아요.", "reviewRating": "4.5" , "reviewImage" : file}  rvId 는 수정 시 필수 등록 시에는 사용 안하는 값 reviewImage는 파일로 전송주시면 됩니다(사진있을시)
export const postTripReview = (payload) => {
  console.log(payload);
  const formData = new FormData();
  formData.append('contentId', payload.contentId);
  formData.append('content', payload.content);
  formData.append('reviewRating', payload.reviewRating);
  return instance.post('/api/v2/review/', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

// 여행리뷰 수정
// param : {"rvId" : "" , "contentId": "2894451", "content": "마음이 변했어요 별로에요.", "reviewRating": "1.0" , "reviewImage" : file}  rvId 는 수정 시에는 필수값
export const putTripReview = (payload) => {
  return instanceWithNoAuth.put('/api/v2/review/', payload);
};

// 여행리뷰 좋아요
// param : {"rvId" : "" , "rlId": "", "contentId": ""}   rvId 리뷰번호 필수  rlId 리뷰좋아요 번호 삭제 시 필수
export const postTripLike = (payload) => {
  return instanceWithNoAuth.post('/api/v2/review/reviewLike', payload);
};


// 나의 리뷰 조회 - review
// param : { "page": 0, "size": 10, "sort": ["createDate"]} 그냥 객체로 전송 시 모든 데이터 조회
export const getMyTripReview = (payload) => {
  return instance.get('/api/v2/review/myList' + makeDynamicUrl(payload));
};