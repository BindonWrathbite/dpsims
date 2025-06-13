<template>
  <div class="flex items-center justify-center min-h-screen bg-gradient-to-br from-blue-100 to-blue-300">
    <div class="bg-white shadow-lg rounded-2xl p-8 max-w-md w-full text-center">
      <h2 class="text-2xl font-bold mb-4">Log Out?</h2>
      <p class="mb-6 text-gray-600">Are you sure you want to sign out?</p>
      <div class="flex justify-center gap-4">
        <Button
          label="Cancel"
          class="p-button-outlined"
          @click="$router.push('/')"
        />
        <Button
          label="Log Out"
          icon="pi pi-sign-out"
          class="p-button-danger"
          @click="auth.logout"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/authStore'
const auth = useAuthStore()
</script>
