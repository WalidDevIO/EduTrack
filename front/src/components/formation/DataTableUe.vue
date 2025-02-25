<template>
    <VDataTable :items="ues" :headers="headers">
        <template v-slot:item.option="{item}">
            <v-checkbox-btn v-model="item.option" readonly/>
        </template>
    </VDataTable>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const ues = ref([])

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

onMounted(() => {
    fetch('/ueFake.json')
        .then(r => r.json())
        .then(data => ues.value = data)
})
</script>