<script setup>
    import Dialog from '@/components/Dialog/Dialog.vue';
    import { apiPut } from '@/api/requests';

    const props = defineProps({
        link: String,
        title: String
    })

    const changeIdea = async (ideaTitle) => {
        const payload = {
            name: ideaTitle
        }

        const url = new URL(props.link);

        const resJson = await apiPut(
            `${url.pathname}`,
            payload
        );
    }
</script>

<template>
    <Dialog
        title="Edit idea"
        label="New title"
        dialog-name=""
        :initial-input-value="props.title"
        @submit="changeTile"
    />
</template>

<style scoped>

</style>