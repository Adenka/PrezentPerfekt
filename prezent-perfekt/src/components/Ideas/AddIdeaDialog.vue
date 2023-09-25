<script setup>
    //TODO - zmergować dialogi bo to literally to samo
    import { useDialogStorage } from '@/store/dialogs';
    import { ref } from 'vue';
    import { useRoute } from "vue-router";
    import { backendURL } from '@/assets/constants';

    const dialogStorage = useDialogStorage();
    const ideaTitle = ref('');
    const route = useRoute();

    const emit = defineEmits(['update'])

    const addIdeaOnClick = async () => {
        const payload = {
            title: ideaTitle.value
        }

        const res = await fetch(`${backendURL}/api/people/${route.params.pid}/ideas`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        dialogStorage.changeDialogOpen(false);
        
        emit('update');
    }
</script>

<template>
    <v-dialog
        v-model="dialogStorage.isDialogOpen"
        width="50%"
    >
        <v-card>
            <v-card-title class="headline">
                Add idea
            </v-card-title>
            <v-card-text>
                <v-text-field
                    label="Title"
                    v-model="ideaTitle"
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
                    @click="addIdeaOnClick"
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