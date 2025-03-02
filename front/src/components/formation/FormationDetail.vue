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
                        <!-- TODO: Bouton attribution de groupe manuelle -->
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
</template>

<script setup>
import { api } from '@/utils/axios';
import { adminStudentHeaders } from '@/utils/dataTableHeaders';
import { computed, onMounted, ref } from 'vue';

const loading = ref(true)
const formation = ref({})
const students = ref([])
const ues = ref([])

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
    }
    loading.value = false
}

const editFormation = async () => {
    loading.value = true
    const ok = await api.put(`/formations/${props.id}`, formation.value)
    loading.value = false
}

const attributeGroups = async () => {
    loading.value = true
    const ok = await api.post(`/formations/attribute-groups/${props.id}`).then(() => true).catch(() => false)
    if(ok) await api.get(`/formations/students/${props.id}`).then(r => students.value = r.data)
    loading.value = false
}

onMounted(async () => {
    await api.get(`/formations/${props.id}`).then(r => formation.value = r.data)
    await api.get(`/formations/students/${props.id}`).then(r => students.value = r.data)
    await api.get(`/ues`).then(r => ues.value = r.data)
    loading.value = false
})
</script>