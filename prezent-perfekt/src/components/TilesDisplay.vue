<script setup>
    import PersonTile from "@/components/PersonTile.vue";
    import AddTile from "@/components/AddTile.vue";

    const people = [
        {
            id: 1,
            name: "Antek",
        },
        {
            id: 2,
            name: "Mama",
        },
        {
            id: 3,
            name: "Kuba",
        },
        {
            id: 4,
            name: "Tata",
        },
        {
            id: 5,
            name: "Asia",
        },
        {
            id: 6,
            name: "Adam",
        },
        {
            id: 7,
            name: "Ewa",
        }
    ];
</script>

<template>
    <div class="root">
        <h1 class="title">
            Nearest and dearest
        </h1>
        <div class="tile-wrapper">
            <PersonTile
                v-for="person in people"
                :key="person.id"
                :personName="person.name"
            />
            <AddTile />
        </div>
    </div>
</template>

<style scoped>
    .root {
        max-width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .title {
        margin-top: 2rem;
        margin-bottom: 2rem;
    }

    .tile-wrapper {
        display: inline-flex;
        flex-wrap: wrap;
        /*background-color: aqua;*/
        gap: 2rem;
        padding: 2rem;
    }
</style>