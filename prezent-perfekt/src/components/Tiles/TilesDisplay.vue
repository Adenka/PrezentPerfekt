<script setup>
    //TODO - może to powinien być layout or sth
    //TODO - drag and drop
    import PersonTile from "@/components/Tiles/PersonTile.vue";
    import AddTile from "@/components/Tiles/AddTile.vue";
    import { ref, onMounted } from 'vue';
    import { getBrightColorsArray } from '../../assets/brightColors';
    import AddTileDialog from './AddTileDialog.vue';
    import { backendURL } from "@/assets/constants";
    import { apiGet } from "@/api/requests";
    import { extractLink } from "@/api/apiUtils";
    import EditTileDialog from "./EditTileDialog.vue";
    import { useDialogStorage } from '@/store/dialogs';

    const dialogStorage = useDialogStorage();

    const people = ref([]);
    const colors = ref([]);

    const editedData = ref({});

    onMounted(() => fetchPeople());

    const fetchPeople = async () => {
        const data = await apiGet(`${backendURL}/api/people`);

        const peopleArray = extractLink(data, "personModelList");
        
        colors.value = getBrightColorsArray(peopleArray.length);

        people.value = peopleArray;
    }

    const setClickedData = (link, name) => {
        editedData.value = {
            "link": link,
            "name": name
        }

        dialogStorage.changeDialogOpen(true, "edit");
    }

</script>

<template>
    <AddTileDialog
        dialogName="add"
    />
    <EditTileDialog
        dialogName="edit"
        :link="editedData.link"
        :name="editedData.name"
        @update="fetchPeople"
    />
    <div class="root">
        <h1 class="title">
            Nearest and dearest
        </h1>
        <div class="tile-wrapper">
            <PersonTile
                v-for="(person, index) in people"
                :pid="person.pid"
                :key="person.pid"
                :personName="person.name"
                :color="colors[index]"
                :link="person._links.self.href"
                @edit="setClickedData"
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