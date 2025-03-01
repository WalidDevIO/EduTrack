<template>
    <div class="d-flex" :style="`padding-top: ${registerMode ? '5%' : '10%'};`">
        <v-sheet :elevation="5" class="px-16 pb-16 pt-8 mx-auto w-30 rounded-lg">
            <div class="d-flex justify-center flex-column ma-10">
                <v-row class="d-flex align-items-end ma-auto">
                    <v-img src="/favicon.ico" height="70" class="ma-logo w-30"></v-img>
                    <v-col>
                        <span class="font-weight-bold titre pb-2">EduTrack</span>
                        <v-list-item-subtitle class="subtitle unwrappable-text">
                            Gestion de formations et d'étudiants
                        </v-list-item-subtitle>
                    </v-col>
                </v-row>
            </div>
            <VForm v-model="isFormValid">
                <VTextField v-if="!registerMode" v-model="username" placeholder="Nom d'utilisateur"
                    :rules="[mandatoryFieldRule]" />
                <VTextField v-if="registerMode" v-model="student.firstname" placeholder="Prénom"
                    :rules="[mandatoryFieldRule]" />
                <VTextField v-if="registerMode" v-model="student.surname" placeholder="Nom"
                    :rules="[mandatoryFieldRule]" />
                <VTextField v-if="registerMode" v-model="student.adress" placeholder="Adresse"
                    :rules="[mandatoryFieldRule]" />
                <VTextField v-model="password" type="password" placeholder="Mot de passe"
                    :rules="[mandatoryFieldRule]" />
                <VTextField v-if="registerMode" v-model="passwordConfirmation" type="password"
                    placeholder="Confirmation du mot de passe"
                    :rules="[mandatoryFieldRule, v => v === password || 'Les mots de passe ne correspondent pas!']" />
                <VRow class="mt-4 d-flex flex-row justify-center">
                    <VBtn @click="handleSubmit">{{ message }}</VBtn>
                    <VSpacer />
                    <h5 class="mt-2" v-if="!registerMode">Pas de compte ? <a href="/register">S'inscrire</a></h5>
                </VRow>
            </VForm>
        </v-sheet>
    </div>
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
      type: Boolean,
      default: false
    }
})
</script>