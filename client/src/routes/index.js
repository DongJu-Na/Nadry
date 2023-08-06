import { createWebHistory, createRouter } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/Home.vue'),
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/accounts/Login.vue'),
  },
  {
    path: '/join',
    name: 'join',
    component: () => import('@/views/accounts/Join.vue'),
  },
  {
    path: '/trip',
    name: 'trip',
    component: () => import('@/views/Trip.vue'),
  },
  {
    path: '/stamp',
    name: 'stamp',
    component: () => import('@/views/Stamp.vue'),
  },
  {
    path: '/wish',
    name: 'wish',
    component: () => import('@/views/Wish.vue'),
  },
  {
    path: '/market',
    name: 'market',
    component: () => import('@/views/Market.vue'),
  },
  {
    path: '/menu',
    name: 'menu',
    component: () => import('@/views/Menu.vue'),
  },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});
