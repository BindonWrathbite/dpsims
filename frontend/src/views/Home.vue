<script setup lang="ts">
import { computed } from 'vue'
import Login from './Login.vue' // adjust path as needed

const isAuthenticated = computed(() => !!localStorage.getItem('token'))
</script>

<template>
  <div v-if="isAuthenticated">
    <div class="bg-gray-100 min-h-screen">
      <p class="text-red-700">Hello World!</p>
      <i class="pi pi-check" style="color: black"/>
      <Button label="Click Me"
              class="p-button-raised p-button-rounded p-button-outlined p-button-secondary" />
    </div>
  </div>
  <Login v-else />
</template>