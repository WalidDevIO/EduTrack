<template>
<VContainer class="h-100">
    <VSheet :elevation="5" class="h-75 pa-5 rounded-lg">
      <h2 class="mb-2">Se connecter</h2>
      <VForm>
        <VTextField v-model="username"/>
        <VTextField v-model="password" type="password"/>
        <VBtn @click="login(username, password)">Se connecter</VBtn>
      </VForm>
    </VSheet>
  </VContainer>
</template>

<script setup>
import { VTextField } from 'vuetify/components/VTextField';
import { ref } from 'vue';

const username = ref("")
const password = ref("")

const props = defineProps({
    login: {
        type: Function,
        required: true
    }
})
</script>
