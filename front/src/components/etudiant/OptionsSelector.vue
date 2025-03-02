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
                <DataTable title="Les options" :items="showedUes" :headers="adminUesHeaders" no-data-text="Aucune UEs">
                    <template v-slot:actions="{item}">
                        <VRow class="d-flex flex-row justify-center">
                            <!-- Si pas inscrit et peut choisir bouton pour s'inscrire -->
                            <VBtn v-if="infos.student.coursesId.length < infos.formation.options && !infos.student.coursesId?.includes(item._id)" @click="emit('subscribe', item._id)" color="green">S'inscrire</VBtn>
                            <!-- Si inscrit bouton pour de désinscrire -->
                            <VBtn v-else-if="infos.student.coursesId?.includes(item._id)" color="red" @click="emit('unsubscribe', item._id)">Se désinscrire</VBtn>
                            <!-- Si pas inscrit mais ne peut plus choisir indication -->
                            <template v-else>
                                <p class="text-center"><VIcon color="red">mdi-close</VIcon>Aucune action possible</p>
                            </template>
                        </VRow>
                    </template>
                </DataTable>
        </VSheet>
    </VDialog>
</template>

<script setup>
import { computed, ref } from 'vue';
import { adminUesHeaders } from '@/utils/dataTableHeaders';

const emit = defineEmits(['subscribe', 'unsubscribe'])

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