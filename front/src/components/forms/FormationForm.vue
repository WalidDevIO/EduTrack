<template>
    <h3 class="mb-6">{{ title }}</h3>
    <VForm v-model="isFormValid">
        <VTextField v-model="formation.nom" placeholder="Nom de la formation" :rules="[v => !!v || 'Un nom de formation est requis !']" label="Nom de la formation"/>
        <VTextField v-model="formation.responsable" placeholder="Responsable de la formation" :rules="[v => !!v || 'Un reponsable de formation est requis !']" label="Responsable de la formation"/>
        <VTextField v-model="formation.mail" placeholder="Email du responsable de la formation" label="Email du responsable de la formation"
            :rules="[
                v => !!v || 'Un mail de responsable de formation est requis !',
                v => !v || /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'L\'adresse mail du reponsable doit être valide'
            ]"
        />

        <VAutocomplete multiple label="UEs" :items="props.ues" :item-value="item => item._id" :item-title="(item) => `${item.nom} - ${item.responsable}`" v-model="formation.ues"/>

        <VTextField label="Nombre min. d'options" type="number" v-model="formation.options" :rules="[v => Number.isInteger(Number.parseInt(v))  && Number.parseInt(v) >= 0 || 'Ce champs doit être un nombre !']" placeholder="Nombre min. d'options"/>
        <VTextField type="number" v-model="formation.td" :rules="[v => Number.isInteger(Number.parseInt(v)) && Number.parseInt(v) > 0 || 'Ce champs doit être un nombre !']" placeholder="Taille max. des groupes de TD" label="Taille max. des groupes de TD"/>
        <VTextField type="number" v-model="formation.tp" :rules="[v => Number.isInteger(Number.parseInt(v)) && Number.parseInt(v) > 0 || 'Ce champs doit être un nombre !']" placeholder="Taille max. des groupes de TP" label="Taille max. des groupes de TP"/>

        <VBtn @click.prevent="emit('send')" class="mt-4" :disabled="!isFormValid">
            {{btnText}}
        </VBtn>
    </VForm>    
</template>

<script setup>
import { ref } from 'vue';
const isFormValid = ref(false)
const formation = defineModel()

const emit = defineEmits(['send'])

const props = defineProps({
    btnText: {
        type: String,
        required: true
    },
    ues: {
        type: Array,
        default: () => []
    },
    title: {
        type: String,
        required: true
    }
})
</script>