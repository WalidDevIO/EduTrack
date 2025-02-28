<template>
    <Loader v-model="loading" />
    <v-toolbar color="#0d0d0d">
        <v-toolbar-title>Dashboard Etudiant</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon>
            <v-icon color="white" @click="authStore.logout">mdi-export</v-icon>
        </v-btn>
    </v-toolbar>
    <VContainer v-if="!loading">
        <VRow>
            <InfosEtudiant :infos="infos"/>
        </VRow>
        <VRow>
            <Message/>
        </VRow>
        <VRow>
            <DataTableUe/>
        </VRow>
    </VContainer>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { api } from '@/utils/axios';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const infos = ref()
const loading = ref(true)
const authStore = useAuthStore()
const router = useRouter()

onMounted(async () => {
    if(authStore.role !== "student") {
        router.push('/login')
    }
    await api.get("/students/me").then(r => infos.value = r.data)
    loading.value = false
})
</script>