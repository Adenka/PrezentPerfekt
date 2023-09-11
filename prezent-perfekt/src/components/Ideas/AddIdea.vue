<script setup>
    import { useDialogStorage } from '@/store/dialogs';

    const dialogStorage = useDialogStorage();

    const props = defineProps({
        pid: String
    });
</script>
<template>
    <v-card
        class="root add-idea ma-4"
        elevation="0"
        @click="dialogStorage.changeDialogOpen(true)"
    >
        <v-icon class="add-icon">mdi-plus-circle</v-icon>
    </v-card>
</template>
<style scoped>
    .root {
        padding: 1.5rem;
        gap: 1.5rem;
        display: flex;
        align-items: center;
    }

    .add-idea {
        border: 5px dotted #b0b0b0;
        height: 6rem;
        display: flex;
        justify-content: center;
    }

    .add-icon {
        font-size: 3rem;
        color: #b0b0b0;
    }
</style>