<template>
    <v-toolbar color="#0d0d0d">
        <v-toolbar-title @click="router.push('/')">Dashboard {{ authStore.role === "admin" ? "Administrateur" : "Étudiant" }}</v-toolbar-title>
        <v-spacer></v-spacer>
        <VBtn v-if="authStore.role === 'admin'" @click="showPasswordModifier = true">
            Changer de mot de passe
        </VBtn>
        <v-btn icon @click="authStore.logout">
            <v-icon color="white">mdi-export</v-icon>
        </v-btn>
    </v-toolbar>

    <VDialog v-model="showPasswordModifier">
        <PasswordModifier username="admin" @close="showPasswordModifier = false"/>
    </VDialog>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const showPasswordModifier = ref(false)
const router = useRouter()
const authStore = useAuthStore()
</script>