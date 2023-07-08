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
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});
