<template>
    <VContainer>
        <VRow>
            <VCol>
                <h3 class="text-center mb-4">Liste des formations</h3>
                <DataTableFormation/>
            </VCol>
            <VCol>
                <h3 class="text-center mb-4">Détail de la formation sélectionnée</h3>
                <VContainer class="d-flex flex-row justify-center align-center h-100">
                    <p>Ici mettre le détail de la formation séléctionnée dans la data table</p>
                </VContainer>
            </VCol>
        </VRow>
        <VRow>
            <VBtn @click="modal = true">Ajouter une formation</VBtn>
        </VRow>
    </VContainer>
    <VDialog v-model="modal">
        <VSheet class="h-75 pa-5 rounded-lg" elevation="5">
            <h1>Ajouter une formation</h1>
            <FormationForm @send="formationSent"/>
        </VSheet>
    </VDialog> 
</template>

<script setup>
import { ref } from 'vue';

const modal = ref(false)

const formationSent = (formation, p1,p2) => {
    console.log(JSON.stringify(formation), p1, p2)
    modal.value = false
}
</script>