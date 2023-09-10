<script setup>
    import Idea from '@/components/Idea.vue'
    import { ref, onMounted } from 'vue';
    import { useRoute } from "vue-router";

    const personName = ref("");
    const ideas = ref([]);

    onMounted(async () => {
        const route = useRoute();
        console.log(route.params.pid);

        const personRes = await fetch(`http://localhost:8080/people/${route.params.pid}`);
        const personData = await personRes.json();
        console.log(personData);

        personName.value = personData.name;
        console.log(personName.value);

        const ideasRes = await fetch(`http://localhost:8080/people/${route.params.pid}/ideas`);
        const ideasData = await ideasRes.json();
        console.log(ideasData);

        ideas.value = ideasData;
    })
</script>

<template>
    <div class="root">
        <h1 class="ma-4">{{personName}}</h1>
        <v-row>
            <v-col
                cols="12"
                md="6"
                lg="4"
                xl="3"
                v-for="(idea, index) in ideas"
                :key="idea.id"
            >
                <Idea
                    :number="index + 1"
                    :description="idea.title"
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