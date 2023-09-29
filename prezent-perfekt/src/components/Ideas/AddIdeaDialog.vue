<script setup>
    import { useRoute } from "vue-router";
    import { backendURL } from '@/assets/constants';
    import Dialog from '@/components/Dialog/Dialog.vue';
    import { apiPost } from '@/api/requests.js'

    const route = useRoute();

    const emit = defineEmits(['update'])

    const addIdea = async (ideaTitle) => {
        const payload = {
            title: ideaTitle
        }
        
        const resJson = await apiPost(
            `${backendURL}/api/people/${route.params.pid}/ideas`,
            payload
        );

        emit('update');
    }
</script>

<template>
    <Dialog
        title="Add idea"
        label="Title"
        dialog-name="add"
        @submit="addIdea"
    />
</template>

<style scoped>
    
</style>