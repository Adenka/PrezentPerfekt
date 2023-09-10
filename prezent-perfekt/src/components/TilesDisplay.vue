<script setup>
    import PersonTile from "@/components/PersonTile.vue";
    import AddTile from "@/components/AddTile.vue";
    import { ref, onMounted } from 'vue';

    const people = ref([]);

    onMounted(async () => {
        const res = await fetch("http://localhost:8080/people");

        const data = await res.json();

        console.log(data);

        people.value = data;
    })

</script>

<template>
    <div class="root">
        <h1 class="title">
            Nearest and dearest
        </h1>
        <div class="tile-wrapper">
            <PersonTile
                v-for="person in people"
                :key="person.pid"
                :pid="person.pid"
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