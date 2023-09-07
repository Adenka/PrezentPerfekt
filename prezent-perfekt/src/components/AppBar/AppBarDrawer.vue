<script setup>
    import { useDrawerStore } from '@/store/drawer';

    const drawerStore = useDrawerStore();

    const people = [
        {
            id: 1,
            name: "Antek",
        },
        {
            id: 2,
            name: "Mama",
        },
        {
            id: 3,
            name: "Kuba",
        },
        {
            id: 4,
            name: "Tata",
        },
        {
            id: 5,
            name: "Asia",
        },
        {
            id: 6,
            name: "Adam",
        },
        {
            id: 7,
            name: "Ewa",
        }
    ];
</script>

<template>
    <v-navigation-drawer
        v-model="drawerStore.isDrawerOpen"
        location="right"
        temporary
    >
        <v-card class="side-drawer">
            <v-card-title class="text-h5 d-flex align-center pa-5">
                Nearest and dearest
            </v-card-title>
            <v-divider></v-divider>
                <v-list>
                    <v-list-item
                        v-for="person in people"
                        :key="person.id"
                        :title="person.name"
                        @click=""
                    ></v-list-item>
                </v-list>
            <div class="drawer-button-wrapper">
                <v-btn
                    color="primary"
                    text
                >
                    Dodaj <v-icon class="heart-icon">mdi-heart-outline</v-icon>
                </v-btn>
            </div>
        </v-card>
    </v-navigation-drawer>
</template>

<style scoped>
    .side-drawer {
        height: 100%;
        display: flex;
        flex-direction: column;
    }
    .user-icon {
        padding: 1.5rem 2rem 1.5rem 1rem;
    }

    .drawer-button-wrapper {
        display: flex;
        justify-content: center;
        align-items: end;
        width: 100%;
        flex: 3;
        padding: 2rem;
    }

    .heart-icon {
        margin-left: 0.5rem;
    }
</style>