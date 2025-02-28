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
            <Message/>
        </VRow>
        <VRow>
            <DataTableUe/>
        </VRow>
    </VContainer>
</template>