<script setup>
    //TODO - zrobić ogólną klasę na Tile
    //TODO - guzik nie może być aktywny jak trawimy request
    import { useDialogStorage } from '@/store/dialogs';

    const dialogStorage = useDialogStorage();
</script>

<template>
    <v-card
        elevation="0"
        class="person-tile add-person-tile"
        @click="dialogStorage.changeDialogOpen(true, 'add')"
    >
        <v-icon class="add-icon">mdi-plus-circle</v-icon>
    </v-card>
</template>

<style scoped>
    .person-tile {
        width: 15rem;
        aspect-ratio: 1/1;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .add-person-tile {
        border: 5px dotted #b0b0b0;
    }

    .add-icon {
        font-size: 5rem;
        color: #b0b0b0;
    }
</style>