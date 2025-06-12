<template>
  <div class="min-h-screen bg-gray-50 text-gray-900">
    <!-- Top Banner -->
    <header class="bg-white shadow flex justify-between items-center px-6 py-4">
      <h1 class="text-xl font-bold">Dearborn Music Inventory</h1>

      <!-- Show user info + logout when logged in -->
      <div v-if="auth.user" class="flex items-center gap-4">
        <span v-if="user" class="text-sm text-gray-700">Welcome, {{ user.name }}</span>
        <Button
          icon="pi pi-sign-out"
          label="Logout"
          class="p-button-sm p-button-text"
          @click="auth.logout"
        />
      </div>
    </header>

    <!-- Page Content -->
    <main class="p-4">
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/authStore'
import { storeToRefs } from 'pinia'

const auth = useAuthStore()
const { user, loading } = storeToRefs(auth)
</script>
