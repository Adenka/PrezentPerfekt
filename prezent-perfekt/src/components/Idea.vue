<script setup>
    import { getBrightColor } from '@/assets/brightColors';

    const props = defineProps({
        number: Number,
        description: String,
    });
</script>

<template>
    <v-card
        class="root ma-4"
        :class="'bg-' + getBrightColor()"
    >
        <v-card class="number">{{ props.number }}</v-card>
        <div class="description">{{ props.description }}</div>
        <v-spacer></v-spacer>
        <div class="icon-wrapper">
            <v-btn
                icon="mdi-pencil"
                elevation="0"
                class="idea-button"
            />
        </div>
        <v-btn
            icon="mdi-delete"
            elevation="0"
            style="background-color: transparent;"
        />
    </v-card>
</template>

<style scoped>
    .root {
        padding: 1.5rem;
        gap: 1.5rem;
        display: flex;
        align-items: center;
    }

    .number {
        height: 2.5rem;
        aspect-ratio: 1/1;
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: bold;
        font-size: 1.25rem;
    }

    .description {
        font-size: 1.25rem;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .idea-button {
        visibility: hidden;
    }

    .idea-button:hover div {
        visibility: visible;
    }
</style>