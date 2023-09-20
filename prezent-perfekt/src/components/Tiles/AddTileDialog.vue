<script setup>
    import { useDialogStorage } from '@/store/dialogs';
    import { ref } from 'vue';
    import { useRouter } from 'vue-router';

    const dialogStorage = useDialogStorage();
    const personName = ref('');
    const router = useRouter();

    const addTileOnClick = async () => {
        const payload = {
            name: personName.value
        }

        const res = await fetch("http://localhost:8080/people", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa('Gusia:stare-skarpety')
            },
            body: JSON.stringify(payload)
        });

        dialogStorage.changeDialogOpen(false);
        //TODO - jakoś dostać pid od backendu
        //router.push(`/person/${pid}`);
    }
</script>

<template>
    <v-dialog
        v-model="dialogStorage.isDialogOpen"
        width="50%"
    >
        <v-card>
            <v-card-title class="headline">
                Add person
            </v-card-title>
            <v-card-text>
                <v-text-field
                    label="Name"
                    v-model="personName"
                />
            </v-card-text>
            <v-card-actions class="d-flex justify-end buttons">
                <v-btn
                    text
                    @click="dialogStorage.changeDialogOpen(false)"
                    class="action-button"
                >
                    Cancel
                </v-btn>
                <v-btn
                    text
                    @click="addTileOnClick"
                    variant="tonal"
                    class="action-button"
                >
                    Add
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<style scoped>
    .headline {
        margin: 0.5rem;
    }

    .buttons {
        margin-right: 1rem;
        margin-bottom: 1rem;
    }

    .action-button {
        width: 5rem;
    }
</style>