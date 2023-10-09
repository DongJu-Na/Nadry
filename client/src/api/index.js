import axios from 'axios';
import { useMainStore } from '@/store';
import { setInterceptors } from './interceptors';
import { i18n } from '../i18n/i18n';

// token 필요한 instance
const createInstance = () => {
  const instance = axios.create();
  // instance.defaults.baseURL = import.meta.env.VITE_APP_API_URL;
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
  // instance.defaults.baseURL = import.meta.env.VITE_APP_API_URL;
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

// 카카오 로그인
export const userKakaoLogin = (payload) => {
  return instanceWithNoAuth.post('/api/v1/kakao/authenticate', payload);
};

// 회원가입
export const join = (payload) => {
  const formData = new FormData();
  formData.append('name', payload.name);
  formData.append('email', payload.email);
  formData.append('password', payload.password);
  formData.append('birthDay', payload.birthDay);
  formData.append('profileImage', payload.profileImage);
  formData.append('role', payload.role);
  return instanceWithNoAuth.post('/api/v1/auth/register', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
};

// 상품 조회 - market products
// param : { "page": 1, "size": 10, "sort": ["name"]} 그냥 객체로 전송 시 모든 데이터 조회
export const getProducts = () => {
  return instanceWithNoAuth.get('/api/v2/product/list');
};

// 관광사진 검색
export const getGalleryList = (payload) => {
  return instanceWithNoAuth.post('/api/v1/photoGallery/galleryList1', payload);
};

// 여행지 검색
export const searchTrip = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/searchKeyword1/' + i18n.global.locale, payload);
};

// 여행지 상세 조회 - Intro
export const getTripDetailIntro = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailIntro1/' + i18n.global.locale, payload);
};
// 여행지 상세 조회 - Info
export const getTripDetailInfo = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailInfo1/' + i18n.global.locale, payload);
};
// 여행지 상세 조회 - Image
export const getTripDetailImage = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailImage1/' + i18n.global.locale, payload);
};
// 여행지 상세 조회 - Common
export const getTripDetailCommon = (payload) => {
  return instanceWithNoAuth.post('/api/v1/tour/detailCommon1/' + i18n.global.locale, payload);
};

// 여행리뷰 조회
// param : {"contentId" : "2894451" , "page": 1, "size": 10, "sort": ["name"]}  contentId는 필수  , page 는 생략 시 contentId로 모든 데이터 조회
export const getTripReview = (payload) => {
  return instanceWithNoAuth.get('/api/v2/review/list' + makeDynamicUrl(payload));
};

// 여행리뷰 등록
// param : {"rvId" : "" , "contentId": "2894451", "content": "먹태깡 재고가 많아서 좋아요.", "reviewRating": "4.5" , "reviewImage" : file}  rvId 는 수정 시 필수 등록 시에는 사용 안하는 값 reviewImage는 파일로 전송주시면 됩니다(사진있을시)
export const postTripReview = (payload) => {
  // instance.defaults.baseURL = '/api';
  // console.log(payload);
  const formData = new FormData();
  formData.append('contentId', payload.contentId);
  formData.append('content', payload.content);
  formData.append('contentTypeId', payload.contentTypeId);
  formData.append('areaCode', payload.areaCode);
  formData.append('reviewRating', payload.reviewRating);
  formData.append('reviewImage', payload.reviewImage);
  formData.append('posX', payload.posX);
  formData.append('posY', payload.posY);
  formData.append('realPosX', payload.realPosX);
  formData.append('realPosY', payload.realPosY);
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
// param : {"rvId" : "" }   rvId 리뷰번호 필수
export const postTripLike = (payload) => {
  // instance.defaults.baseURL = '/api';
  const headers = { 'Content-type': 'application/json', Accept: '*/*' };
  return instance.post('/api/v2/review/reviewLike', payload, headers);
};

// 나의 리뷰 조회 - review
// param : { "page": 1, "size": 10, "sort": ["createDate"]} 그냥 객체로 전송 시 모든 데이터 조회
export const getMyTripReview = (payload) => {
  return instance.get('/api/v2/review/myList' + makeDynamicUrl(payload));
};

// 베스트 리뷰 조회 - (기준 날짜 내림차순 , 좋아요 수 내림차 순)
// param : { "page": 1, "size": 10} 그냥 객체로 전송 시 모든 데이터 조회
export const getBestTripReview = (payload) => {
  return instance.get('/api/v2/review/bestList' + makeDynamicUrl(payload));
};

// 찜
// 이미 찜한 경우 같은 API 호출 시 찜 삭제
export const addWish = (payload) => {
  return instance.post('/api/v2/dibs/', payload);
};

// 찜 여부 체크
export const getWishDetail = (payload) => {
  return instance.post('/api/v2/dibs/contentDibsCheck', payload);
};

// 찜 여행지 목록
export const getWishList = (payload) => {
  return instance.get('/api/v2/dibs/dibsList' + makeDynamicUrl(payload));
};

// 날짜 조회 - 초단기실황조회
export const getUltraSrtNcst = (payload) => {
  return instanceWithNoAuth.post('/api/v1/Fcst/getUltraSrtNcst', payload);
};

// 스탬프 수집 리스트
export const getMyStampList = () => {
  return instance.get('/api/v2/stamp/myStampList');
};
