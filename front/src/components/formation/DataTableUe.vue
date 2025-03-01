<template>
    <VSheet elevation="5" :class="`mt-6 pa-5 rounded-lg w-100 ${props.class}`">
        <VContainer>
            <h2>{{ title }}</h2>
            <VDataTable :items="ues" :headers="headers">
                <template v-slot:item.option="{item}">
                    <v-checkbox-btn class="ml-3" v-model="item.option" readonly/>
                    <p>{{ item.option ? `Place : ${item.capacite === -1 ? "Infini" : item.capacite}` : undefined }}</p>
                </template>
                <template v-slot:item.responsable="{item}">
                    <a :href="`mailto:${item.mail}`">{{ item.responsable }}</a>
                </template>
                <template v-slot:no-data>
                    Aucune UEs disponible
                </template>
                <template v-slot:item.actions="{item}">
                    <slot name="actions" :item="item">
                    </slot>
                </template>
            </VDataTable>
        </VContainer>
    </VSheet>
</template>

<script setup>
const props = defineProps({
    ues: {
        type: Array,
        default: () => []
    },
    headers: {
        type: Array
    },
    title: {
        type: String,
        required: true
    }
})
</script>