<template>
    <VContainer>
        <VSheet elevation="5" class="h-75 pa-5 rounded-lg">
            <h1 class="mb-6">Espace d'administration</h1>
            <VTabs v-model="selectedTab" grow>
                <VTab value="formationsGestion">Gestion des formations</VTab>
                <VTab value="formationGestion">Gestion d'une formation</VTab>
            </VTabs>
            <VTabsWindow v-model="selectedTab">
                <VTabsWindowItem value="formationsGestion">
                    <GlobalFormation/>
                </VTabsWindowItem>
                <VTabsWindowItem value="formationGestion">
                    Detail formation
                </VTabsWindowItem>
            </VTabsWindow>
        </VSheet>
    </VContainer>
</template>

<script setup>
import { ref } from 'vue';

const selectedTab = ref('formationsGestion')

</script>