//TODO - w sumie cała logika do pomysłów i osób jest ta sama - zrobić coś z tym
//TODO - ujednolicić cudzysłowia
<script setup>
    import { getBrightColor } from '@/assets/brightColors';
    import { apiDelete } from '@/api/requests';
    import { useDialogStorage } from '@/store/dialogs';

    const props = defineProps({
        number: Number,
        description: String,
        pid: String,
        selfLink: String
    });

    const dialogStorage = useDialogStorage();
    const emit = defineEmits(['update', 'edit'])

    const deleteIdea = async () => {
        const url = new URL(props.selfLink);
        const resJson = await apiDelete(`${url.pathname}`);

        emit('update')
    }

    const updateIdea = async () => {
        emit('edit', props.selfLink, props.description);
        dialogStorage.changeDialogOpen(true, "edit");
    }
</script>

<template>
    <v-card
        class="root ma-4"
        :class="'bg-' + getBrightColor()"
    >
        <v-card class="number">{{ props.number }}</v-card>
        <div class="description">{{ props.description }}</div>
        <v-spacer></v-spacer>
        <v-btn
            icon="mdi-pencil"
            elevation="0"
            class="idea-button"
            @click="updateIdea"
        />
        <v-btn
            icon="mdi-delete"
            elevation="0"
            class="idea-button"
            @click="deleteIdea"
        />
    </v-card>
</template>

<style scoped>
    .root {
        padding: 1.5rem;
        gap: 1.5rem;
        display: flex;
        align-items: center;
        height: 6rem;
    }

    .number {
        min-width: 2.5rem;
        aspect-ratio: 1/1;
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: bold;
        font-size: 1.25rem;
    }

    .description {
        font-size: 1.25rem;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .idea-button {
        color: rgb(50, 50, 50, 0.25);
        transition: color 0.2s;
        background-color: transparent;
        margin: -0.5rem
    }

    .idea-button:hover {
        color: rgb(50, 50, 50, 1);
    }
</style>