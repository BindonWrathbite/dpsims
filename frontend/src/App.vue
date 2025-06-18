<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Home from "./views/Home.vue"
import { getUserName, } from './api/userApi'

const userName = ref('')

onMounted(async () => {
  try {
    userName.value = await getUserName()
  } catch (error) {
    console.error('Failed to fetch user name:', error)
  }
})
</script>

<template>
  <div class="banner bg-white text-black">
    <span v-if="userName">Welcome, {{ userName }}</span>
  </div>
  <Home />
</template>

<style scoped>
</style>
