<template>
  <div class="flex items-center justify-center min-h-screen bg-gradient-to-br from-blue-100 to-blue-300">
    <div class="bg-white shadow-lg rounded-2xl p-8 max-w-md w-full text-center">
      <h1 class="text-3xl font-bold mb-4">Welcome to the Music Inventory System</h1>
      <p class="mb-6 text-gray-600">Please sign in with your school account to continue.</p>

      <button
        class="w-full py-2 px-4 bg-blue-600 hover:bg-blue-700 text-white font-semibold rounded-lg shadow-md transition duration-200"
        @click="login"
      >
        Sign in with Google
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
function login() {
  window.location.href = 'http://localhost:8080/oauth2/authorization/google';
}
</script>
