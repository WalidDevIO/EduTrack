<template>
<VContainer class="h-100">
    <VSheet elevation="5" class="h-75 pa-5 rounded-lg">
      <h2 class="mb-2">{{ message }}</h2>
      <VForm v-model="isFormValid">
        <VTextField v-if="!registerMode" v-model="username" placeholder="Nom d'utilisateur" :rules="[mandatoryFieldRule]"/>
        <VTextField v-if="registerMode" v-model="student.firstname" placeholder="Prénom" :rules="[mandatoryFieldRule]"/>
        <VTextField v-if="registerMode" v-model="student.surname" placeholder="Nom" :rules="[mandatoryFieldRule]"/>
        <VTextField v-if="registerMode" v-model="student.adress" placeholder="Adresse" :rules="[mandatoryFieldRule]"/>
        <VTextField v-model="password" type="password" placeholder="Mot de passe" :rules="[mandatoryFieldRule]"/>
        <VTextField v-if="registerMode" v-model="passwordConfirmation" type="password" placeholder="Confirmation du mot de passe" :rules="[mandatoryFieldRule, v => v === password || 'Les mots de passe ne correspondent pas!']"/>
        <VBtn @click="handleSubmit">{{ message }}</VBtn>
      </VForm>
    </VSheet>
  </VContainer>
</template>

<script setup>
import { VTextField } from 'vuetify/components/VTextField';
import { ref, computed } from 'vue';

const isFormValid = ref(false)

const username = ref("")
const password = ref("")
const passwordConfirmation = ref("")
const student = ref({})

const message = computed(() => props.registerMode ? "S'inscrire" : "Se connecter" )

const mandatoryFieldRule = v => !!v || 'Ce champs est obligatoire!'

const handleSubmit = () => {
  if(!isFormValid.value) return;
  if(props.registerMode) {
    props.auth({
      student: student.value,
      password: password.value
    })
  } else {
    props.auth({
      username: username.value,
      password: password.value
    })
  }
}

const props = defineProps({
    auth: {
        type: Function,
        required: true
    },
    registerMode: {
      type: String,
      default: false
    }
})
</script>
