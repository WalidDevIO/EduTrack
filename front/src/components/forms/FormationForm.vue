<template>
    <VForm v-model="isFormValid">
        <VTextField v-model="formation.nom" placeholder="Nom de la formation" :rules="[v => !!v || 'Un nom de formation est requis !']"/>

        <VTextField v-model="formation.responsable" placeholder="Responsable de la formation" :rules="[v => !!v || 'Un reponsable de formation est requis !']"/>

        <VTextField v-model="formation.mail" placeholder="Email du responsable de la formation" 
            :rules="[
                v => !!v || 'Un mail de responsable de formation est requis !',
                v => !v || /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'L\'adresse mail du reponsable doit être valide'
            ]"
        />

        <VTextField type="number" v-model="formation.nbopt" :rules="[v => Number.isInteger(Number.parseInt(v))  && Number.parseInt(v) >= 0 || 'Ce champs doit être un nombre !']" placeholder="Nombres d'UE optionnelles"/>

        <VTextField type="number" v-model="formation.tdsize" :rules="[v => Number.isInteger(Number.parseInt(v)) && Number.parseInt(v) > 0 || 'Ce champs doit être un nombre !']" placeholder="Taille max des groupes de TD"/>

        <VTextField type="number" v-model="formation.tpsize" :rules="[v => Number.isInteger(Number.parseInt(v)) && Number.parseInt(v) > 0 || 'Ce champs doit être un nombre !']" placeholder="Taille max des groupes de TP"/>

        <VBtn @click.prevent="emit('send', formation)" class="mt-4" :disabled="!isFormValid">
            Ajouter la formation
        </VBtn>
    </VForm>    
</template>

<script setup>
import { ref } from 'vue';
const isFormValid = ref(false)
const formation = ref({})

const emit = defineEmits(['send'])
</script>