<script setup>
    import { ref } from 'vue';
    import { useDialogStorage } from '@/store/dialogs'

    const props = defineProps({
        title: String,
        label: String
    })

    const emit = defineEmits(['submit'])

    const label = ref('');
    const dialogStorage = useDialogStorage();

    const emitSubmit = () => {
        emit("submit", label.value);
        label.value = '';
        dialogStorage.changeDialogOpen(false);
    }
</script>

<template>
    <v-dialog
        v-model="dialogStorage.isDialogOpen"
        width="50%"
    >
        <v-card>
            <v-card-title class="headline">
                {{props.title}}
            </v-card-title>
            <v-card-text>
                <v-text-field
                    :label="props.label"
                    v-model="label"
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
                    @click="emitSubmit"
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