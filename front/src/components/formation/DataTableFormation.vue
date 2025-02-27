<template>
    <VDataTable :items="formations" :headers="headers">

    </VDataTable>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const formations = ref([])

const headers = computed(() => props.detail ? detailHeaders : baseHeaders)

const baseHeaders = [
    {
        key: 'nom',
        title: 'Nom de la formation'
    },
    {
        key: 'responsable',
        title: 'Responsable de la formation'
    },
    {
        key: 'mail',
        title: 'Email du responsable de la formation'
    },
]

const detailHeaders = [
    ...baseHeaders,
    {
        key: 'nbopt',
        title: "Nombre d'UE optionnelles à choisir"
    }
]

onMounted(() => {
    fetch('/formationsFake.json')
        .then(r => r.json())
        .then(data => formations.value = data)
})
</script>