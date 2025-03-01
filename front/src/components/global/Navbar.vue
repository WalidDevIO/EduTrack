<template>
    <v-toolbar color="#0d0d0d">
        <v-toolbar-title>Dashboard {{ authStore.role === "admin" ? "Administrateur" : "Étudiant" }}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon @click="authStore.logout">
            <v-icon color="white">mdi-export</v-icon>
        </v-btn>
    </v-toolbar>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore()
</script>