<script setup>
    import { ref } from 'vue';
    import { useDialogStorage } from '@/store/dialogs'
import { watch } from 'vue';

    const props = defineProps({
        title: String,
        label: String,
        dialogName: String,
        initialInputValue: String
    })

    const emit = defineEmits(['submit'])

    const inputValue = ref('');
    const dialogStorage = useDialogStorage();

    watch(() => props.initialInputValue, () => {
        inputValue.value = props.initialInputValue;
    })

    const emitSubmit = () => {
        emit("submit", inputValue.value);
        inputValue.value = '';
        dialogStorage.changeDialogOpen(false, props.dialogName);
    }
</script>

<template>
    <v-dialog
        v-model="dialogStorage.isDialogOpen[props.dialogName]"
        width="50%"
    >
        <v-card>
            <v-card-title class="headline">
                {{props.title}}
            </v-card-title>
            <v-card-text>
                <v-text-field
                    :label="props.label"
                    v-model="inputValue"
                />
            </v-card-text>
            <v-card-actions class="d-flex justify-end buttons">
                <v-btn
                    text
                    @click="dialogStorage.changeDialogOpen(false, props.dialogName)"
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
                    Go!
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