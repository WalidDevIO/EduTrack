<template>
    <VDataTable :items="formations" :headers="headers">

    </VDataTable>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const formations = ref([])

const headers = ref([
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
])

onMounted(() => {
    fetch('/formationsFake.json')
        .then(r => r.json())
        .then(data => formations.value = data)
})
</script>