<template>
    <v-toolbar color="#0d0d0d">
        <v-toolbar-title>Dashboard Etudiant</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn icon>
            <v-icon color="white">mdi-export</v-icon>
        </v-btn>
    </v-toolbar>
    <VContainer>
        <VRow>
            <InfosEtudiant />
        </VRow>
        <VRow>
            <VCol><Message /></VCol>
            <VCol><DataTableUe/></VCol>
        </VRow>
    </VContainer>
</template>

<script setup>

</script>