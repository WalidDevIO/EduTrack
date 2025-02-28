<template>
    <Navbar />
    <Loader v-model="loading"/>
    <VContainer v-if="!loading" class="h-100">
        <VRow class="h-100">
            <DataTableFormation :formations="formations"/>
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

onMounted(async () => {
    if(authStore.role !== "student") {
        router.push('/')
    }
    await api.get("/students/me").then(r => {
        if (r.data.student.academicYearId) {
            router.push('/etudiant/dashboard')
        }
    })

    await api.get('/formations').then(r => formations.value = r.data)

    loading.value = false
})
</script>