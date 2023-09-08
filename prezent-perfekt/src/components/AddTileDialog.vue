<script setup>
    import { useDialogStorage } from '@/store/dialog';

    const dialogStorage = useDialogStorage();
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
                    @click="this.$router.push('/ideas/')"
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