<script setup>
    //TODO - może to powinien być layout or sth
    //TODO - drag and drop
    import PersonTile from "@/components/Tiles/PersonTile.vue";
    import AddTile from "@/components/Tiles/AddTile.vue";
    import { ref, onMounted } from 'vue';
    import { getBrightColorsArray } from '../../assets/brightColors';
    import AddTileDialog from './AddTileDialog.vue';
    import { backendURL } from "@/assets/constants";
    import { useRouter } from 'vue-router';

    const people = ref([]);
    const colors = ref([]);
    const router = useRouter();

    onMounted(async () => {
        const res = await fetch(`${backendURL}/api/people`);
        let data;
        //try {
            data = await res.json();
        /*}
        catch(error) {
            router.push('/login');
        }*/
        console.log(data);

        const peopleArray = data._embedded.personModelList;
        console.log(peopleArray);
        colors.value = getBrightColorsArray(peopleArray.length);

        people.value = peopleArray;
    })

</script>

<template>
    <AddTileDialog/>
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