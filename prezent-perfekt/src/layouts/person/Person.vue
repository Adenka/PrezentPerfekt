<template>
    <v-app>
        <app-bar-drawer />
        <default-bar :drawerVisible="true" />
        <default-view />
    </v-app>
</template>

<script setup>
    import DefaultBar from '@/components/AppBar/AppBar.vue'
    import DefaultView from './View.vue'
    import AppBarDrawer from '@/components/AppBar/AppBarDrawer.vue'
</script>
