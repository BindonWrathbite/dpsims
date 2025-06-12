import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/instruments',
    name: 'instruments',
    component: () => import('../views/InstrumentsView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
