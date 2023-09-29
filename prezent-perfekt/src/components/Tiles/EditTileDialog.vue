<script setup>
    import Dialog from '@/components/Dialog/Dialog.vue';
    import { apiPut } from '@/api/requests';

    const props = defineProps({
        link: String,
        name: String
    })

    const emit = defineEmits(['update']);

    const changeTile = async (personName) => {
        const payload = {
            name: personName
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
        title="Edit person"
        label="New name"
        dialog-name="edit"
        :initial-input-value="props.name"
        @submit="changeTile"
    />
</template>

<style scoped>

</style>