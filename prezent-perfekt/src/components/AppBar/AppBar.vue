<script setup>
    import { useDrawerStore } from '@/store/drawer';

    const props = defineProps({
        drawerVisible: Boolean,
    });

    const drawerStore = useDrawerStore();
</script>

<template>
    <v-app-bar
        color="black"
    >
        <v-app-bar-title
            @click="this.$router.push('/')"
        >
            Prezent Perfekt
        </v-app-bar-title>
        <v-spacer></v-spacer>
        
        <v-app-bar-nav-icon
            v-if="props.drawerVisible"
            variant="text"
            @click="drawerStore.toggleDrawer"
        />
    </v-app-bar>
</template>
