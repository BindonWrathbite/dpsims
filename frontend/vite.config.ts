import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import tailwindcss from '@tailwindcss/vite'
import Components from 'unplugin-vue-components/vite'
import {PrimeVueResolver} from "unplugin-vue-components/resolvers";

export default defineConfig({
  plugins: [
      vue(),
      tailwindcss(),
      Components({
          resolvers: [PrimeVueResolver()]
      })
  ],
    resolve: {
        alias: {
            // This is the alias for the src directory
            '@': '/src',
            '@backend': 'http://localhost:8080',
        }
    }
})
