import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useDialogStorage = defineStore('drawer', () => {
  const isDialogOpen = ref({
    "add": false,
    "edit": false,
  })
  const changeDialogOpen = (event, dialogName) => {
    isDialogOpen.value[dialogName] = event
  }

  return {
    isDialogOpen,
    changeDialogOpen,
  }
})