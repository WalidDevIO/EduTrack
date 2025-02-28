<template>
    <VSheet elevation="5" class="mt-6 h-75 pa-5 rounded-lg w-100">
        <VContainer class="h-100">
            <VDataTable class="h-100" :items="formations" :headers="headers">
                <template v-slot:item.responsable="{item}">
                    <a :href="`mailto:${item.mail}`">{{ item.responsable }}</a>
                </template>
            </VDataTable>
        </VContainer>
    </VSheet>
</template>

<script setup>
import { computed } from 'vue';

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
]

const detailHeaders = [
    ...baseHeaders,
    {
        key: 'nbopt',
        title: "Nombre d'UE optionnelles à choisir"
    }
]

const props = defineProps({
    detail: {
        type: Boolean,
        default: false
    },
    formations: {
        type: Array,
        default: () => []
    }
})
</script>