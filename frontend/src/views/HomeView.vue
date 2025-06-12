<template>
  <div class="p-6">
    <!-- Show nothing while loading -->
    <div v-if="loading" class="text-center text-gray-500 mt-20">
      Checking authentication...
    </div>

    <!-- Show login if unauthenticated -->
    <div v-else-if="!user" class="max-w-md mx-auto bg-white shadow-lg rounded-2xl p-8 text-center mt-20">
      <h2 class="text-2xl font-bold mb-4">Welcome to the Music Inventory System</h2>
      <p class="mb-6 text-gray-600">Please sign in with your school account to continue.</p>
      <Button
        label="Sign in with Google"
        icon="pi pi-google"
        class="w-full p-button-raised p-button-rounded"
        @click="login"
      />
    </div>

    <!-- Show dashboard if authenticated -->
    <div v-else>
      <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        <Card class="cursor-pointer" @click="$router.push('/instruments')">
          <template #title>Instrument Inventory</template>
          <template #content>
            <p>View and manage all musical instruments.</p>
          </template>
        </Card>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '@/stores/authStore'
import { storeToRefs } from 'pinia'

const auth = useAuthStore()
const { user, loading } = storeToRefs(auth)

function login() {
  window.location.href = 'http://localhost:8080/oauth2/authorization/google'
}
</script>
