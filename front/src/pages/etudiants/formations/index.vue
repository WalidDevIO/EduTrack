<template>
    <Navbar />
    <Loader v-model="loading"/>
    <VContainer v-if="!loading" class="h-100">
        <VRow class="h-100">
            <DataTableFormation :formations="formations" :headers="headers">
                <template v-slot:actions="{item}">
                    <VBtn @click="handleSubscribe(item)">Rejoindre</VBtn>
                </template>
            </DataTableFormation>
        </VRow>
    </VContainer>
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

const handleSubscribe = async (item) => {
    const result = await api.post(`/students/subscribe/${item._id}`).then(r => r.data).catch(err => false)
    if(result) {
        router.push('/etudiants/dashboard')
    } else {
        //TODO: Belle affichage erreur
        alert('Erreur')
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
    if(authStore.role !== "student") {
        router.push('/')
    }
    await api.get("/students/me").then(r => {
        if (r.data.student.academicYearId) {
            router.push('/etudiants/dashboard')
        }
    })

    await api.get('/formations').then(r => formations.value = r.data)

    loading.value = false
})
</script>