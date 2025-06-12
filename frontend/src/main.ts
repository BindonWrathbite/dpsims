import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { useAuthStore } from '@/stores/authStore'

import App from './App.vue'
import router from './router'

// PrimeVue & Theme
import PrimeVue from 'primevue/config'
import Aura from '@primeuix/themes/aura'
import 'primeicons/primeicons.css'

const app = createApp(App)

// 1. Create and register Pinia
const pinia = createPinia()
app.use(pinia)

// 2. Register PrimeVue with Aura theme
app.use(PrimeVue, {
  theme: {
    preset: Aura
  }
})

// 3. Register router
app.use(router)

// 4. Fetch authenticated user before app renders
const auth = useAuthStore()
auth.fetchUser()

// 5. Mount app
app.mount('#app')
