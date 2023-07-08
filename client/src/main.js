import { createApp } from 'vue';
import piniaPersist from 'pinia-plugin-persist';
import pinia from '@/store';
import { router } from '@/routes';
import App from './App.vue';
import './style.css';

pinia.use(piniaPersist);

createApp(App).use(pinia).use(router).mount('#app');
