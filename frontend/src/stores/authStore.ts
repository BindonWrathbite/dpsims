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

        if (
          typeof res.data === 'object' &&
          res.data !== null &&
          typeof res.data.name === 'string' &&
          typeof res.data.email === 'string'
        ) {
          this.user = res.data
          console.log('✅ User loaded:', this.user)
        } else {
          console.warn('⚠️ Received invalid user data:', res.data)
          this.user = null
        }

      } catch (err) {
        console.error('❌ User fetch failed:', err)
        this.user = null
      } finally {
        this.loading = false
      }
    },

    logout() {
      window.location.href = 'http://localhost:8080/logout'
    },

    reset() {
      this.user = null
      this.loading = false
    }
  }
})
