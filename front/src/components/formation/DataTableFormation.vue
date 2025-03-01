<template>
    <VSheet elevation="5" class="mt-6 pa-5 rounded-lg w-100">
        <VContainer>
            <h2>{{ title }}</h2>
            <VDataTable :items="formations" :headers="headers">
                <template v-slot:item.responsable="{item}">
                    <a :href="`mailto:${item.mail}`">{{ item.responsable }}</a>
                </template>
                <template v-slot:item.actions="{item}">
                    <slot name="actions" :item="item">
                    </slot>
                </template>
                <template v-slot:no-data>
                    Aucune formation disponible
                </template>
            </VDataTable>
        </VContainer>
    </VSheet>
</template>

<script setup>
const props = defineProps({
    formations: {
        type: Array,
        default: () => []
    },
    headers: {
        type: Array,
    },
    title: {
        type: String,
        required: true
    },
})
</script>