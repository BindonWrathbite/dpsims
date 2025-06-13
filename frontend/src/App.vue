<template>
  <div class="min-h-screen bg-gray-50 text-gray-900">
    <!-- Top Banner -->
    <header class="bg-white shadow flex justify-between items-center px-6 py-4">

      <!-- Left side: Home button + Title -->
      <div class="flex items-center gap-2">
        <!-- Button to home screen -->
        <Button
          icon="pi pi-home"
          severity="info"
          rounded
          @click="$router.push('/')" />

        <h1 class="text-xl font-bold">DPS Music Inventory Management</h1>
      </div>

      <!-- Show user info + logout when logged in -->
      <div v-if="user" class="flex items-center gap-4">
        <span class="text-sm text-gray-700">Welcome, {{ user.name.split(' ')[0] }}</span>
        <Button
          icon="pi pi-sign-out"
          label="Logout"
          class="p-button-sm p-button-text"
          @click="$router.push('/logout-confirm')"
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
const { user } = storeToRefs(auth)
</script>
