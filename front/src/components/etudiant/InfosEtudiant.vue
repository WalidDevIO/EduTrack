<template>
    <Loader v-model="loading" />
    <VContainer v-if="!loading">
        <VSheet elevation="5" class="p-4 rounded-lg">
            <VCard>
                <VCardTitle><h5>Informations me concernant</h5></VCardTitle>
                <VCardItem class="mb-4">
                    <VRow>
                        <VCol>
                            <p>Numéro étudiant:  {{ infosEtudiant?.student?.id }}</p>
                            <p>Nom: {{ infosEtudiant?.student?.surname }}</p>
                            <p>Prénom: {{ infosEtudiant?.student?.firstname }}</p>
                            <p>Adresse: {{ infosEtudiant?.student?.adress }}</p>
                        </VCol>
                        <VCol>
                            <p>Formation choisie: {{ infosEtudiant?.formation?.nom ?? "Aucune formation choisie" }}</p>
                            <p>Accepté: {{ infosEtudiant?.student?.academicYearRegistered ? "Oui" : "Non" }}</p>
                            <p>Groupe de TD: {{ infosEtudiant?.student?.dw ?? "Non défini" }}</p>
                            <p>Groupe de TP: {{ infosEtudiant?.student?.pw ?? "Non défini" }}</p>
                        </VCol>
                    </VRow>
                </VCardItem>
            </VCard>
        </VSheet>
    </VContainer>
</template>

<script setup>
import { api } from '@/utils/axios';
import { onMounted, ref } from 'vue';
import Loader from '../global/Loader.vue';

const infosEtudiant = ref()
const loading = ref(true)

onMounted(async () => {
    await api.get("/students/me").then(r => infosEtudiant.value = r.data)
    loading.value = false
})
</script>