import { createApp } from 'vue';
import piniaPersist from 'pinia-plugin-persist';
import pinia from '@/store';
import { router } from '@/routes';
import App from './App.vue';
import Vue3Lottie from 'vue3-lottie';
import './style.css';
import { i18n } from "./i18n/i18n" 

pinia.use(piniaPersist);

createApp(App).use(pinia).use(router).use(Vue3Lottie).use(i18n).mount('#app');
