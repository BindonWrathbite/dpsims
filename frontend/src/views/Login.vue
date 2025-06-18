<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100">
    <div class="bg-white p-10 rounded-2xl shadow-lg text-center border border-gray-200 max-w-sm w-full">
      <h1 class="text-3xl font-bold text-black mb-6">Dearborn Music Inventory</h1>
      <p class="mb-4 text-gray-700">Please sign in with your Dearborn Schools account:</p>
      <a :href="`${backendUrl}/oauth2/authorization/google`" class="w-full">
        <Button icon="pi pi-google" label="Sign in with Google" class="p-button-rounded p-button-info w-full" />
      </a>
    </div>
  </div>
</template>

<script setup lang="ts">
import Button from 'primevue/button'
const backendUrl = import.meta.env.VITE_BACKEND_URL;
</script>
