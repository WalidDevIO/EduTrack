<template>
    <VExpansionPanel>
        <VExpansionPanelTitle>
            <h3>UEs</h3>
        </VExpansionPanelTitle>
        <VExpansionPanelText>
            <DataTable :items="ues" title="Les Unités d'Enseignements" :headers="adminUesHeaders" no-data-text="Aucune UEs disponible">
                <template v-slot:actions="{item}">
                    <VBtn @click="editUe(item)">
                        Éditer
                    </VBtn>
                </template>
            </DataTable>
            <VContainer class="d-flex justify-end">
                <VBtn prepend-icon="mdi-plus" @click="showDialog = true">
                    Ajouter une UE
                </VBtn>
            </VContainer>
        </VExpansionPanelText>
    </VExpansionPanel>

    <VDialog v-model="showDialog">
        <VContainer>
            <VSheet elevation="5" class="pa-5 rounded-lg">
                <h1 class="mb-2">Ajouter une Unité d'Enseignement</h1>
                <UeForm v-model="ue" @send="saveUe" btnText="Créer l'UE"/>
            </VSheet>
        </VContainer>
    </VDialog>

    <VDialog v-model="showEditDialog">
        <VContainer>
            <VSheet elevation="5" class="pa-5 rounded-lg">
                <h1 class="mb-2">Modifier une Unité d'Enseignement</h1>
                <UeForm v-model="ue" @send="updateUe" btnText="Modifier l'UE"/>
            </VSheet>
        </VContainer>
    </VDialog>

    <Snackbar v-model="showSnackbar" :text="text"/>
</template>

<script setup>
import { ref } from 'vue';
import { adminUesHeaders } from '@/utils/dataTableHeaders';
import { api } from '@/utils/axios';

const showEditDialog = ref(false)
const showDialog = ref(false)
const ue = ref({})
const showSnackbar = ref(false)
const text = ref('')

const props = defineProps({
    ues: {
        type: Array,
        required: true
    }
})

const emit = defineEmits(['new', 'edit'])

const editUe = (item) => {
    ue.value = item
    showEditDialog.value = true
}

const saveUe = async () => {
    api.post('/ues/', ue.value)
        .then(r => {
            if(r.status === 201) {
                showDialog.value = false
                emit('new', ue.value)
                ue.value = {}
            } 
        })
        .catch(err => {
            if(err.response) {
                text.value = err.response.data.detail
                showSnackbar.value = true
            }
        })
}

const updateUe = async () => {
    api.put(`/ues/${ue.value._id}`, ue.value)
        .then(r => {
            if(r.status === 200) {
                showEditDialog.value = false
                emit('edit', ue.value)
                ue.value = {}
            } 
        })
        .catch(err => {
            if(err.response) {
                text.value = err.response.data.detail
                showSnackbar.value = true
            }
        })
}
</script>