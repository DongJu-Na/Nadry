import { createWebHistory, createRouter } from 'vue-router';
import { useMainStore } from '@/store';

const routes = [
  {
    path: '/',
    name: 'root',
    redirect: '/home',
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Home.vue'),
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/accounts/Login.vue'),
  },
  {
    path: '/kakao-auth',
    name: 'kakao auth',
    component: () => import('@/views/accounts/KakaoAuth.vue'),
  },
  {
    path: '/logout',
    name: 'logout',
    component: () => import('@/views/accounts/Logout.vue'),
  },
  {
    path: '/join',
    name: 'join',
    component: () => import('@/views/accounts/Join.vue'),
  },
  {
    path: '/trips',
    name: 'trips',
    component: () => import('@/views/Trip.vue'),
    children: [
      {
        path: '',
        name: 'trip list',
        component: () => import('@/views/trip/List.vue'),
        props: true,
      },
      {
        path: ':id/:type',
        name: 'trip detail',
        component: () => import('@/views/trip/Detail.vue'),
        props: true,
        meta: {
          header: false,
          title: '여행 상세',
        },
      },
    ],
  },
  {
    path: '/stamp',
    name: 'stamp',
    component: () => import('@/views/Stamp.vue'),
    meta: {
      auth: true,
    },
  },
  {
    path: '/reviews',
    name: 'reviews',
    component: () => import('@/views/Review.vue'),
    meta: {
      auth: true,
    },
  },
  {
    path: '/wish',
    name: 'wish',
    component: () => import('@/views/Wish.vue'),
    meta: {
      auth: true,
    },
  },
  {
    path: '/market',
    name: 'market',
    component: () => import('@/views/Market.vue'),
    children: [
      {
        path: '',
        name: 'product list',
        component: () => import('@/views/Market/List.vue'),
        props: true,
        meta: {
          header: false,
          title: '마켓',
        },
      },
      {
        path: ':id',
        name: 'product detail',
        component: () => import('@/views/Market/Detail.vue'),
        props: true,
        meta: {
          header: false,
          title: '마켓',
        },
      },
    ],
  },
  {
    path: '/menu',
    name: 'menu',
    component: () => import('@/views/Menu.vue'),
  },
  {
    path: '/:pathMatch(.*)*',
    name: '404',
    component: () => import('@/views/404.vue'),
  },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  // console.log(to, from);
  const store = useMainStore();
  if (to.name === 'login') {
    if (store.user.isLoggedIn) {
      alert('이미 로그인 되어 있습니다.');
      next(from);
    } else {
      next();
    }
  }

  window.scrollTo(0, 0);
  next();
});
