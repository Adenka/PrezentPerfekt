<script setup>
    import Dialog from '@/components/Dialog/Dialog.vue';
    import { apiPut } from '@/api/requests';

    const props = defineProps({
        dialogName: String,
        link: String,
        title: String
    })

    const emit = defineEmits(['update']);

    const changeIdea = async (ideaTitle) => {
        console.log(ideaTitle);
        const payload = {
            title: ideaTitle
        }

        const url = new URL(props.link);

        const resJson = await apiPut(
            `${url.pathname}`,
            payload
        );

        emit('update');
    }
</script>

<template>
    <Dialog
        title="Edit idea"
        label="New title"
        :dialog-name="props.dialogName"
        :initial-input-value="props.title"
        @submit="changeIdea"
    />
</template>

<style scoped>

</style>