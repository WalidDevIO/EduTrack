<template>
    <v-card class="h-100">
      <VCardText>{{ message?.text ?? "" }}</VCardText>
    </v-card>
  </template>
  
<script setup>
const props = defineProps({
  message: Object,
})
</script>