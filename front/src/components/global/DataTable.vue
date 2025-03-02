<template>
    <VSheet elevation="5" class="mt-6 pa-5 rounded-lg w-100">
        <VContainer>
            <h2>{{ title }}</h2>
            <VDataTable :items="items" :headers="headers" :search="search">
                <template v-slot:top>
                    <VTextField
                        v-model="search"
                        placeholder="Rechercher"
                        prepend-inner-icon="mdi-magnify"
                        variant="outlined"
                        single-line
                        hide-details
                    />
                </template>
                <template v-slot:item.responsable="{item}">
                    <a :href="`mailto:${item.mail}`">{{ item.responsable }}</a>
                </template>
                <template v-slot:item.actions="{item}">
                    <slot name="actions" :item="item">
                    </slot>
                </template>
                <template v-slot:item.option="{item}">
                    <v-checkbox-btn class="ml-3 not-clickable" v-model="item.option" readonly/>
                    <p>{{ item.option ? `Place : ${item.capacite == -1 ? "Infini" : item.capacite}` : undefined }}</p>
                </template>
                <template v-slot:no-data>
                    {{ noDataText }}
                </template>
            </VDataTable>
        </VContainer>
    </VSheet>
</template>

<script setup>
import { ref } from 'vue';
const search = ref('')
const props = defineProps({
    items: {
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
    noDataText: {
        type: String,
        required: true
    }
})
</script>

<style scoped>
::v-deep(.not-clickable i),
::v-deep(.not-clickable input) {
    cursor: default !important;
    pointer-events: none;
}
</style>
