<template>
    <Loader v-model="loading" />
    <Navbar />
    <VContainer v-if="!loading">
        <VRow>
            <InfosEtudiant :infos="infos"/>
        </VRow>
        <VRow v-if="infos.formation">
            <OptionsSelector :infos="infos" :ues="ues" @subscribe="handleOptions" @unsubscribe="id => handleOptions(id, false)" :text="optionsTextProps"/>
        </VRow>
        <VRow>
            <Message/>
        </VRow>
        <VRow>
            <DataTable :items="studentUes" title="Mes enseignements" :headers="studentUesHeaders" no-data-text="Aucune UEs suivies"/>
        </VRow>
    </VContainer>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { api } from '@/utils/axios';
import { onMounted, ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { studentUesHeaders } from '@/utils/dataTableHeaders';

const infos = ref()
const loading = ref(true)
const authStore = useAuthStore()
const router = useRouter()
const ues = ref([])

const studentUes = computed(() => ues.value.filter(ue => !ue.option || infos.value.student.coursesId?.includes(ue._id)))

const optionsTextProps = computed(() => infos.value.student.coursesId.length < infos.value.formation?.options ? {
    text: "Vous n'avez pas choisi toutes vos options",
    color: "red"
} : {
    text: "Gérer vos options",
    color: "black"
})

const handleOptions = async (id, add = true) => {
    const ok = await api.post(`/students/ues/${add ? 'subscribe' : 'unsubscribe'}/${id}`).then(() => true).catch(() => false)
    if(ok) {
        if(add) infos.value.student.coursesId.push(id)
        else infos.value.student.coursesId = infos.value.student.coursesId.filter(ueId => ueId != id)
    }
}

onMounted(async () => {
    if(authStore.role !== "student") router.push('/')
    await api.get("/students/me").then(r => infos.value = r.data)
    if(!infos.value.student.academicYearId) {
        router.push('/etudiants/formations')
        return
    }

    await api.get(`/formations/${infos.value.formation._id}/ues`).then(r => ues.value = r.data)
    loading.value = false
})
</script>