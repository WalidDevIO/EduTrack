<template>
<VContainer class="h-100">
    <VSheet :elevation="5" class="h-75 pa-5 rounded-lg">
      <h2 class="mb-2">{{ message }}</h2>
      <VForm>
        <VTextField v-model="username"/>
        <VTextField v-model="password" type="password"/>
        <VBtn @click="auth(username, password)">{{ message }}</VBtn>
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
    auth: {
        type: Function,
        required: true
    },
    message: {
      type: String,
      default: "Se connecter"
    }
})
</script>
