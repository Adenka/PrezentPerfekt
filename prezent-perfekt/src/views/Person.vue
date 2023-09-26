<script setup>
    import AddIdea from '@/components/Ideas/AddIdea.vue';
    import Idea from '@/components/Ideas/Idea.vue';
    import { ref, onMounted } from 'vue';
    import { useRoute } from "vue-router";
    import AddIdeaDialog from '@/components/Ideas/AddIdeaDialog.vue';
    import { backendURL } from '@/assets/constants';
    import { apiGet } from '@/api/requests';
    import { extractLink } from '@/api/apiUtils.js'

    const personName = ref("");
    const ideas = ref([]);

    onMounted(async () => {
        fetchIdeas();
    })

    const route = useRoute();

    const fetchIdeas = async () => {
        const personData = await apiGet(`${backendURL}/api/people/${route.params.pid}`);
        personName.value = personData.name;
        console.log(personName.value);

        const ideasData = await apiGet(`${backendURL}/api/people/${route.params.pid}/ideas`);
        ideas.value = extractLink(ideasData, "ideaModelList")
    }
</script>

<template>
    <div class="root">
        <AddIdeaDialog
            @update="fetchIdeas"
        />
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
                    :pid="this.$route.params.pid"
                    :selfLink="idea._links.self.href"
                    @update="fetchIdeas"
                />
            </v-col>
            <v-col
                cols="12"
                md="6"
                lg="4"
                xl="3"
            >
                <AddIdea
                    :pid="this.$route.params.pid"
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