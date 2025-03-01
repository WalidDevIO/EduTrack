<template>
    <VExpansionPanel>
        <VExpansionPanelTitle>
            <h3>Formations</h3>
        </VExpansionPanelTitle>
        <VExpansionPanelText>
            <DataTable :items="formations" title="Les Formations" :headers="adminFormationsHeaders" no-data-text="Aucune formation disponible">
                <template v-slot:actions="{item}">
                    <VBtn>
                        Éditer
                    </VBtn>
                </template>
            </DataTable>
            <VContainer class="d-flex justify-end">
                <VBtn prepend-icon="mdi-plus" @click="showDialog = true">
                    Ajouter une Formation
                </VBtn>
            </VContainer>
        </VExpansionPanelText>
    </VExpansionPanel>

    <VDialog v-model="showDialog" v-if="showDialog">
        <VContainer>
            <VSheet elevation="5" class="pa-5 rounded-lg">
                <h1 class="mb-2">Ajouter une Formation"</h1>
                <FormationForm v-model="formation" @send="saveFormation" :ues="ues" btnText="Créer la formation"/>
            </VSheet>
        </VContainer>
    </VDialog>

    <Snackbar v-model="showSnackbar" :text="text"/>
</template>

<script setup>
import { ref } from 'vue';
import { adminFormationsHeaders } from '@/utils/dataTableHeaders';
import { api } from '@/utils/axios';

const showDialog = ref(false)
const formation = ref({})
const showSnackbar = ref(false)
const text = ref('')

const props = defineProps({
    formations: {
        type: Array,
        required: true
    },
    ues: {
        type: Array,
        required: true
    }
})

const emit = defineEmits(['new'])

const saveFormation = () => {
    api.post('/formations/', formation.value)
        .then(r => {
            if(r.status === 201) {
                emit('new', formation.value)
                formation.value = {}
                showDialog.value = false
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