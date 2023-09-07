<script setup>
    import Idea from '@/components/Idea.vue'

    const ideas = [
        {
            id: 1,
            description: 'Hełm Mandalorianina',
        },
        {
            id: 2,
            description: 'Koszulka',
        },
        {
            id: 3,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 4,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 5,
            description: 'Hełm Mandalorianina',
        },
        {
            id: 6,
            description: 'Koszulka',
        },
        {
            id: 7,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 8,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 9,
            description: 'Hełm Mandalorianina',
        },
        {
            id: 10,
            description: 'Koszulka',
        },
        {
            id: 11,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 12,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 13,
            description: 'Hełm Mandalorianina',
        },
        {
            id: 14,
            description: 'Koszulka',
        },
        {
            id: 15,
            description: 'Vorinclex, Voice of Hunger',
        },
        {
            id: 16,
            description: 'Vorinclex, Voice of Hunger',
        },
    ]
</script>

<template>
    <div class="root">
        <h1 class="ma-4">Antek</h1>
        <v-row>
            <v-col
                cols="12"
                md="6"
                lg="4"
                xl="3"
                v-for="idea in ideas"
                :key="idea.id"
            >
                <Idea
                    :number="idea.id"
                    :description="idea.description"
                />
            </v-col>
        </v-row>
    </div>
</template>

<style scoped>
    .root {
        margin: 2rem;
    }
</style>