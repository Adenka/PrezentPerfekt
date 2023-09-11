import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useDialogStorage = defineStore('drawer', () => {
  const isDialogOpen = ref(false)
  const changeDialogOpen = (event) => {
    isDialogOpen.value = event
  }

  return {
    isDialogOpen,
    changeDialogOpen,
  }
})