<template>
    <Loader v-model="loading" />
    <Navbar />
    <VContainer v-if="!loading">
        <VRow>
            <InfosEtudiant :infos="infos"/>
        </VRow>
        <VRow>
            <Message/>
        </VRow>
        <VRow>
            <DataTableUe :ues="studentUes"/>
        </VRow>
    </VContainer>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { api } from '@/utils/axios';
import { onMounted, ref, computed } from 'vue';
import { useRouter } from 'vue-router';

const infos = ref()
const loading = ref(true)
const authStore = useAuthStore()
const router = useRouter()

const ues = ref([])

const studentUes = computed(() => ues.value.filter(ue => !ue.option || infos.value.student.coursesId?.includes(ue._id)))

onMounted(async () => {
    if(authStore.role !== "student") {
        router.push('/')
    }
    await api.get("/students/me").then(r => infos.value = r.data)
    if(!infos.value.student.academicYearId) {
        router.push('/etudiant/formations')
        return
    }

    await api.get(`/formations/${infos.value.formation._id}/ues`).then(r => ues.value = r.data)
    loading.value = false
})
</script>