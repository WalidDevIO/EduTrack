<template>
    <v-overlay :model-value="loading" class="align-center justify-center" :persistent="true">
        <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
</template>

<script setup>
const loading = defineModel()
</script>