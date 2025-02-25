<template>
    <VForm v-model="isFormValid">
        <VTextField v-model="ue.nom" placeholder="Nom de l'UE" :rules="[v => !!v || 'Un nom d\'UE est requis !']"/>

        <VTextField v-model="ue.responsable" placeholder="Responsable de l'UE" :rules="[v => !!v || 'Un reponsable d\'UE est requis !']"/>

        <VRow>
            <VCheckbox v-model="ue.option" label="Option ?"/>
        </VRow>

        <template v-if="ue.option">
            <VLabel for="capacite">
            Capacité d'accueil de l'UE (-1 pour Infini)
        </VLabel>
        <VTextField type="number" id="capacite" v-model="ue.capacite" :rules="[v => Number.isInteger(Number.parseInt(v)) || 'Ce champs doit être un nombre !']"/>
        </template>

        <VBtn @click.prevent="console.log(JSON.stringify(ue))" class="mt-4" :disabled="!isFormValid">
            Ajouter l'UE
        </VBtn>
    </VForm>    
</template>

<script setup>
import { ref, watch } from 'vue';
const isFormValid = ref(false)
const ue = ref({})

watch(() => ue.option, (newValue) => {
    if(!newValue) {
        delete ue.value.capacite
        delete ue.value.option
    }
})
</script>