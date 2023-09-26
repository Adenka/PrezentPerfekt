<script setup>
    import { useRouter } from 'vue-router';
    import { backendURL } from '@/assets/constants';
    import Dialog from '@/components/Dialog/Dialog.vue';
    import { apiPost } from '@/api/requests';

    const router = useRouter();

    const addTile = async (personName) => {
        const payload = {
            name: personName
        }

        const resJson = await apiPost(
            `${backendURL}/api/people`,
            payload
        );

        console.log(resJson.pid)

        router.push(`/person/${resJson.pid}`);
    }
</script>

<template>
    <Dialog
        title="Add person"
        label="Name"
        @submit="addTile"
    />
</template>

<style scoped>

</style>