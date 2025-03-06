<template>
    <Navbar />
    <Loader v-model="loading"/>
    <VContainer v-if="!loading" class="h-100">
        <VRow class="h-100">
            <DataTable :items="formations" :headers="headers" title="Formations disponibles" no-data-text="Aucune formation disponible">
                <template v-slot:actions="{item}">
                    <VBtn @click="handleSubscribe(item)">Demander à rejoindre</VBtn>
                </template>
            </DataTable>
        </VRow>
    </VContainer>

    <Snackbar v-model="showSnackbar" :text="error"/>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { api } from '@/utils/axios';

const loading = ref(true)
const router = useRouter()
const authStore = useAuthStore()
const formations = ref([])

const error = ref()
const showSnackbar = ref(false)

const handleSubscribe = async (item) => {
    const result = await api.post(`/students/subscribe/${item._id}`).then(r => r.data).catch(err => err.response?.data.detail)
    if(result) {
        router.push('/etudiants/dashboard')
    } else {
        error.value = result
        showSnackbar.value = true
    }
}

const headers = [
    {
        key: 'nom',
        title: 'Nom de la formation',
    },
    {
        key: 'responsable',
        title: 'Responsable de la formation',
    },
    {
        title: 'Actions',
        key: 'actions',
        sortable: false,
    },
]

onMounted(async () => {
    await api.get("/students/me").then(r => {
        if (r.data.student.academicYearId) {
            router.push('/etudiants/dashboard')
        }
    })

    await api.get('/formations').then(r => formations.value = r.data)

    loading.value = false
})
</script>