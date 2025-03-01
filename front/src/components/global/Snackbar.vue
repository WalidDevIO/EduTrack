<template>
    <v-snackbar v-model="snackbar" :timeout="timeout">
        {{ text }}
        <template v-slot:actions>
            <v-btn color="#f88f8b" variant="text" @click="snackbar = false">
                Fermer
            </v-btn>
        </template>
    </v-snackbar>
</template>

<script setup>
/* eslint-disable */

const props = defineProps({
    text: String,
    timeout: {
        type: Number,
        default: 3000
    },
});

const snackbar = defineModel()

</script>