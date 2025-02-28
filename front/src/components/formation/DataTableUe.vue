<template>
    <VSheet elevation="5" class="mt-6 h-75 pa-5 rounded-lg w-100">
        <VContainer>
            <h2>Mes enseignements</h2>
            <VDataTable :items="ues" :headers="headers">
                <template v-slot:item.option="{item}">
                    <v-checkbox-btn class="ml-3" v-model="item.option" readonly/>
                    <p>{{ item.option ? `Place : ${item.capacite === -1 ? "Infini" : item.capacite}` : undefined }}</p>
                </template>
                <template v-slot:item.responsable="{item}">
                    <a :href="`mailto:${item.mail}`">{{ item.responsable }}</a>
                </template>
            </VDataTable>
        </VContainer>
    </VSheet>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
    ues: {
        type: Array,
        default: () => []
    }
})

const headers = ref([
    {
        key: 'nom',
        title: 'Nom de l\' UE'
    },
    {
        key: 'responsable',
        title: 'Responsable de l\'UE'
    },
    {
        key: 'option',
        title: 'Optionnelle ?'
    }
])
</script>