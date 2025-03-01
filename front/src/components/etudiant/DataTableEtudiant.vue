<template>
    <VSheet elevation="5" class="mt-6 pa-5 rounded-lg w-100">
        <VContainer>
            <h2>{{ title }}</h2>
            <VDataTable :items="students" :headers="headers" :search="search">
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
                <template v-slot:item.actions="{item}">
                    <slot name="actions" :item="item">
                    </slot>
                </template>
                <template v-slot:no-data>
                    Aucun Étudiants
                </template>
            </VDataTable>
        </VContainer>
    </VSheet>
</template>

<script setup>
import { ref } from 'vue';

const search = ref('')

const props = defineProps({
    students: {
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