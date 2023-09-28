//TODO - delete/rename person
<script setup>
    import { useDialogStorage } from '@/store/dialogs';
    import { apiDelete } from '@/api/requests';

    const dialogStorage = useDialogStorage();

    const props = defineProps({
        pid: String,
        personName: String,
        color: String,
        link: String
    });

    const emit = defineEmits(['edit']);

    const deletePerson = async () => {
        const url = new URL(props.link);
        const resJson = await apiDelete(`${url.pathname}`);
    }

    const emitEdit = () => {
        emit('edit', props.link, props.personName);
        //dialogStorage.changeDialogOpen(true, "edit")
    }
</script>

<template>
    <v-card
        variant="tonal"
        class="person-tile"
        :class="'bg-' + props.color"
        @click.self="this.$router.push(`/person/${props.pid}`)"
    >
        <v-card-title
            class="tile-title"
            @click="this.$router.push(`/person/${props.pid}`)"
        >
            {{ personName }}
        </v-card-title>
        <div class="buttons-wrapper">
            <v-btn
                icon="mdi-pencil"
                elevation="0"
                class="idea-button"
                @click="emitEdit"
            />
            <v-btn
                icon="mdi-delete"
                elevation="0"
                class="idea-button"
                @click="deletePerson"
            />
        </div>
    </v-card>
</template>

<style scoped>
    .person-tile {
        width: 15rem;
        aspect-ratio: 1/1;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .tile-title {
        font-size: 1.5rem;
    }

    .buttons-wrapper {
        display: flex;
        position: absolute;
        bottom: 1rem;
        right: 1rem;
    }

    .idea-button {
        color: rgb(50, 50, 50, 0.25);
        transition: color 0.2s;
        background-color: transparent;
    }

    .idea-button:hover {
        color: rgb(50, 50, 50, 1);
    }
</style>