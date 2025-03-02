<template>
    <VSheet elevation="5" class="mt-6 pa-5 rounded-lg w-100 d-flex align-center justify-center">
        <VRow class="d-flex justify-center">
            <p :class="`pt-2 pr-4 text-${text.color} font-weight-bold`">{{ text.text }}</p>
            <VBtn @click="showDialog = true">
                Gérer mes options
            </VBtn>
        </VRow>
    </VSheet>

    <VDialog v-model="showDialog">
        <VSheet elevation="5" class="mt-6 pa-5 rounded-lg w-100 d-flex align-center justify-center ga-2">
                <DataTable title="Les options" :items="showedUes" :headers="adminUesHeaders">
                    <template v-slot:actions="{item}">
                        <!-- Si pas inscrit et peut choisir bouton pour s'inscrire -->
                        <!-- Si inscrit bouton pour de désinscrire -->
                        <!-- Si pas inscrit mais ne peut plus choisir indication -->
                    </template>
                </DataTable>
        </VSheet>
    </VDialog>
</template>

<script setup>
import { computed, ref } from 'vue';
import { adminUesHeaders } from '@/utils/dataTableHeaders';

const showDialog = ref(false)
const props = defineProps({
    infos: {
        type: Object,
        required: true
    },
    ues: {
        type: Object,
        required: true
    },
    text: {
        type: Object,
        required: true
    }
})

const showedUes = computed(() => props.ues.filter(ue => ue.option))
</script>