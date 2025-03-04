<template>
    <VContainer>
        <h1 class="mb-6">Espace d'administration</h1>
        <VSheet elevation="5" class="pa-5 rounded-lg">
            <h3 class="mb-6">Gestion UEs et formations</h3>
            <VExpansionPanels>
                <UeExpansionPanel @edit="editUe" @new="addUe" :ues="ues" />
                <FormationExpansionPanel @new="addFormation" :formations="formations" :ues="ues" />
            </VExpansionPanels>
        </VSheet>

        <VSheet elevation="5" class="pa-5 rounded-lg mt-6">
            <h3 class="mb-6">Gestion Étudiants</h3>
            <DataTableEtudiant title="Les étudiants" :students="students" :headers="adminStudentHeaders(formations)">
                <template v-slot:actions="{item}">
                    <VContainer>
                        <VBtn class="mr-2" color="orange" @click="editUserPassword(item)">
                            Définir un mot de passe
                        </VBtn>
                    </VContainer>
                </template>
            </DataTableEtudiant>
        </VSheet>

    </VContainer>

    <VDialog v-model="showPasswordModifier">
        <PasswordModifier :username="username" @close="showPasswordModifier = false" />
    </VDialog>
</template>

<script setup>
import { api } from '@/utils/axios';
import { onMounted, ref } from 'vue';
import { adminStudentHeaders } from '@/utils/dataTableHeaders';

const ues = ref([])
const formations = ref([])
const students = ref([])

const addUe = (ue) => ues.value = [...ues.value, ue]
const addFormation = (formation) => [...formations.value, formation]
const editUe = (ue) => { 
    const index = ues.value.findIndex(i => i._id == ue._id)
    ues.value[index] = { ...ues.value[index], ...ue }
}

const showPasswordModifier = ref(false)
const username = ref()

const editUserPassword = (item) => {
    username.value = `e${item.id}`
    showPasswordModifier.value = true
}

onMounted(() => {
    api.get('/ues')
        .then(r => ues.value = r.data)
        .catch(err => alert(err.response ? err.response.data.detail : "Erreur lors du contact de l'API"))

    api.get('/students')
        .then(r => students.value = r.data)
        .catch(err => alert(err.response ? err.response.data.detail : "Erreur lors du contact de l'API"))

    api.get('/formations')
        .then(r => formations.value = r.data)
        .catch(err => alert(err.response ? err.response.data.detail : "Erreur lors du contact de l'API"))
})

</script>