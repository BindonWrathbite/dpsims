import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import {useAuthStore} from '@/stores/authStore.ts'

import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config';
import Aura from '@primeuix/themes/aura';
import 'primeicons/primeicons.css';

const app = createApp(App)
const pinia = createPinia()

app.use(pinia)
app.use(PrimeVue, {
  theme: {
    preset: Aura
  }
});
app.use(router)

const auth = useAuthStore()
auth.fetchUser()

app.mount('#app')
