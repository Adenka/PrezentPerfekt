<script setup>
    import { getBrightColor } from '@/assets/brightColors';

    const props = defineProps({
        pid: Number,
        personName: String,
    });
</script>

<template>
    <v-card
        variant="tonal"
        class="person-tile"
        :class="'bg-' + getBrightColor()"
        @click="this.$router.push(`/person/${props.pid}`)"
    >
        <v-card-title class="tile-title">
            {{ personName }}
        </v-card-title>
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
    .tile-title {
        font-size: 1.5rem;
    }
</style>