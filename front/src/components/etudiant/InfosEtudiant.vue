<template>
    <VSheet elevation="5" class="mt-6 h-75 pa-5 rounded-lg w-100">
        <VContainer>
            <h2>Informations</h2>
            <VRow>
                <VCol>
                    <p><b>Numéro étudiant:</b>  {{ infos.student?.id }}</p>
                    <p><b>Nom:</b> {{ infos.student?.surname }}</p>
                    <p><b>Prénom:</b> {{ infos.student?.firstname }}</p>
                    <p><b>Adresse:</b> {{ infos.student?.adress }}</p>
                </VCol>
                <VCol>
                    <p><b>Formation choisie:</b> {{ infos.formation?.nom ?? "Aucune formation choisie" }}</p>
                    <p><b>Accepté:</b> {{ infos.student?.academicYearRegistered ? "Oui" : "Non" }}</p>
                    <p><b>Groupe de TD:</b> {{ infos.student?.dw ?? "Non défini" }}</p>
                    <p><b>Groupe de TP</b>: {{ infos.student?.pw ?? "Non défini" }}</p>
                </VCol>
            </VRow>
        </VContainer>
    </VSheet>
</template>

<script setup>
const props = defineProps({
    infos: {
        type: Object,
        required: true
    }
})
</script>