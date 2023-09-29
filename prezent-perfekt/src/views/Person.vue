//TODO - ujednolicić description i title
<script setup>
    import AddIdea from '@/components/Ideas/AddIdea.vue';
    import Idea from '@/components/Ideas/Idea.vue';
    import { ref, onMounted } from 'vue';
    import { useRoute } from "vue-router";
    import AddIdeaDialog from '@/components/Ideas/AddIdeaDialog.vue';
    import { backendURL } from '@/assets/constants';
    import { apiGet, apiPut } from '@/api/requests';
    import { extractLink } from '@/api/apiUtils.js'
    import EditIdeaDialog from '@/components/Ideas/EditIdeaDialog.vue';

    const personName = ref("");
    const ideas = ref([]);
    const editedData = ref({});

    onMounted(async () => {
        fetchName();
        fetchIdeas();
    })

    const route = useRoute();

    const fetchName = async () => {
        const personData = await apiGet(`${backendURL}/api/people/${route.params.pid}`);
        personName.value = personData.name;
    }

    const fetchIdeas = async () => {
        const ideasData = await apiGet(`${backendURL}/api/people/${route.params.pid}/ideas`);
        console.log(ideasData);
        ideas.value = extractLink(ideasData, "ideaModelList")
    }

    const setClickedData = async(link, description) => {
        editedData.value = {
            "link": link,
            "title": description
        }
    }
</script>

<template>
    <div class="root">
        <AddIdeaDialog
            dialogName="add"
            @update="fetchIdeas"
        />
        <EditIdeaDialog
            dialogName="edit"
            :link="editedData.link"
            :title="editedData.title"
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
                    @edit="setClickedData"
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