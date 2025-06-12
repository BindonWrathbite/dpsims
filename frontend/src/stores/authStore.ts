import { defineStore } from 'pinia'
import axios from 'axios'

interface User {
  name: string
  email: string
  role: string
}

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null as User | null,
    loading: true  // ✅ Add loading state
  }),

  actions: {
    async fetchUser() {
      console.log('📦 Fetching user...')
      this.loading = true
      try {
        const res = await axios.get('/api/user', { withCredentials: true })
        this.user = res.data
      } catch (err) {
        this.user = null
      } finally {
        this.loading = false
      }
    },

    logout() {
      window.location.href = 'http://localhost:8080/logout'
    }
  }
})
