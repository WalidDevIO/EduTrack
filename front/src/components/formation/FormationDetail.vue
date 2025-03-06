<template>
    <Loader v-model="loading"/>
    <VContainer>
        <h1 class="mb-6">Formation {{ formation.nom }}</h1>
        <VSheet elevation="5" class="pa-5 rounded-lg">
            <VRow class="d-flex align-center justify-center ma-4">
                <h3>Liste des etudiants</h3>
                <VSpacer/>
                <VBtn class="mr-2" @click="attributeGroups">Attribuer les groupes automatiquement</VBtn>
                <VBtn @click="onlyNotYetRegistered = !onlyNotYetRegistered">{{ onlyNotYetRegistered ? "Tous les étudiants" : "Étudiants en attente" }}</VBtn>
            </VRow>
            <DataTableEtudiant :students="finalStudents" :headers="adminStudentHeaders([formation], true)" :title="`Étudiants de ${formation.nom}`">
                <template v-slot:actions="{item}">
                    <template v-if="!item.academicYearRegistered">
                        <VBtn class="mr-2" color="green" @click="studentValidation(item.id, true)">
                            Accepter
                        </VBtn>
                        <VBtn class="mr-2" color="red" @click="studentValidation(item.id, false)">
                            Refuser
                        </VBtn>
                    </template>
                    <template v-else>
                         <VBtn class="mr-2" @click="attributeGroup(item)">
                            Attribuer groupe manuellement
                         </VBtn>
                        <VBtn class="mr-2" color="red" @click="studentValidation(item.id, false)">
                            Virer
                        </VBtn>
                    </template>
                </template>
            </DataTableEtudiant>
        </VSheet>
        <VSheet elevation="5" class="pa-5 rounded-lg mt-4">
            <FormationForm :title="`Modifier la formation ${formation.nom}`" v-model="formation" :ues="ues" @send="editFormation" btn-text="Mettre à jour"/>
        </VSheet>
    </VContainer>

    <ManualGroupAttribution v-if="student" @change="handleChangeGroup" :student="student" v-model:show="showGroupAttributionModal"/>

    <Snackbar v-model="showSnackbar" text="Une erreure est survenue" />
</template>

<script setup>
import { api } from '@/utils/axios';
import { adminStudentHeaders } from '@/utils/dataTableHeaders';
import { computed, onMounted, ref } from 'vue';

const showSnackbar = ref(false)
const showGroupAttributionModal = ref(false)

const loading = ref(true)
const formation = ref({})
const students = ref([])
const ues = ref([])
const student = ref()

const onlyNotYetRegistered = ref(false)

const finalStudents = computed(() => onlyNotYetRegistered.value ? students.value.filter(etu => !etu.academicYearRegistered) : students.value)

const props = defineProps({
    id: {
        type: String,
        required: true
    }
})

const studentValidation = async (studentId, accept) => {
    loading.value = true
    const ok = await api.post(`/formations/${props.id}/${accept ? 'accept' : 'deny'}/${studentId}`).then(() => true).catch(() => false)
    if(ok) {
        if(accept) students.value.find(etu => etu.id === studentId).academicYearRegistered = true
        else students.value = students.value.filter(etu => etu.id !== studentId)
    } else {
        showSnackbar.value = true
    }
    loading.value = false
}

const editFormation = async () => {
    loading.value = true
    const ok = await api.put(`/formations/${props.id}`, formation.value)
    if(!ok) {
        showSnackbar.value = true
    }
    loading.value = false
}

const attributeGroups = async () => {
    loading.value = true
    const ok = await api.post(`/formations/${props.id}/attribute-groups`).then(() => true).catch(() => false)
    if(ok) await api.get(`/formations/${props.id}/students`).then(r => students.value = r.data)
    else showSnackbar.value = true
    loading.value = false
}

const attributeGroup = (item) => {
    student.value = item
    showGroupAttributionModal.value = true
}

const handleChangeGroup = (groups) => {
    student.dw = groups.td
    student.pw = groups.tp
}

onMounted(async () => {
    await api.get(`/formations/${props.id}`)
        .then(r => formation.value = r.data)
        .catch(err => alert(err.response ? err.response.data.detail : "Erreur lors du contact de l'API"))

    await api.get(`/formations/${props.id}/students`)
        .then(r => students.value = r.data)
        .catch(err => alert(err.response ? err.response.data.detail : "Erreur lors du contact de l'API"))

    await api.get(`/ues`)
        .then(r => ues.value = r.data)
        .catch(err => alert(err.response ? err.response.data.detail : "Erreur lors du contact de l'API"))
        
    loading.value = false
})
</script>