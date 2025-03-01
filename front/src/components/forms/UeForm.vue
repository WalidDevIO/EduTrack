<template>
    <VSheet> 
        <VForm v-model="isFormValid">
            <VTextField v-model="ue.nom" placeholder="Nom de l'UE" :rules="[v => !!v || 'Un nom d\'UE est requis !']"/>
            <VTextField v-model="ue.responsable" placeholder="Responsable de l'UE" :rules="[v => !!v || 'Un reponsable d\'UE est requis !']"/>
                <VTextField v-model="ue.mail" placeholder="Mail du responsable de l'UE" :rules="[
                    v => !!v || 'Un mail de responsable d\'UE est requis !',
                    v => !v || /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'L\'adresse mail du reponsable doit être valide'
                ]"/>

            <VRow>
                <VCheckbox v-model="ue.option" label="Option ?"/>
            </VRow>

            <template v-if="ue.option">
                <VLabel for="capacite">
                    Capacité d'accueil de l'UE (-1 pour Infini)
                </VLabel>
                <VTextField type="number" id="capacite" v-model="ue.capacite" :rules="[v => Number.isInteger(Number.parseInt(v)) || 'Ce champs doit être un nombre !']"/>
            </template>

            <VBtn @click.prevent="emit('send')" class="mt-4" :disabled="!isFormValid">
                Ajouter l'UE
            </VBtn>
        </VForm>
    </VSheet>   
</template>

<script setup>
import { ref, watch } from 'vue';
const isFormValid = ref(false)
const ue = defineModel()

const emit = defineEmits(['send'])

watch(() => ue.value.option, (newValue) => {
    if(!newValue) {
        delete ue.value.capacite
        delete ue.value.option
    }
})
</script>