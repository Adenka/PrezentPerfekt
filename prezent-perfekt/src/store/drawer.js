import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useDrawerStore = defineStore('dialog', () => {
  const isDrawerOpen = ref(false)

  const toggleDrawer = () => {
    isDrawerOpen.value = !isDrawerOpen.value
  }

  return {
    isDrawerOpen,
    toggleDrawer,
  }
})