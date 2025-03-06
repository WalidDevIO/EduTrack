<template>
    <v-sheet :elevation="5" class="px-16 pb-16 pt-8 mx-auto w-30 rounded-lg">
        <VForm v-model="formIsValid">
            <VTextField v-model="password" type="password" placeholder="Mot de passe" :rules="[mandatoryFieldRule]" />
            <VTextField v-model="passwordConfirmation" type="password"
                placeholder="Confirmation du mot de passe"
                :rules="[mandatoryFieldRule, v => v === password || 'Les mots de passe ne correspondent pas!']" />
            <VRow class="mt-4 d-flex flex-row justify-center">
                <VBtn :disabled="!formIsValid" @click.prevent="showConfirmDialog = true">Modifier le mot de passe</VBtn>
            </VRow>
        </VForm>
    </v-sheet>

    <VDialog v-model="showConfirmDialog" max-width="400">
        <VCard>
            <VCardText>Êtes-vous sûr de vouloir modifier le mot de passe ?</VCardText>
            <VCardActions>
                <VBtn @click="changePassword">
                    Confirmer
                </VBtn>
                <VBtn variant="outlined" @click="showConfirmDialog = false">
                    Annuler
                </VBtn>
            </VCardActions>
        </VCard>
    </VDialog>
</template>

<script setup>
import { api } from '@/utils/axios';
import { ref } from 'vue';

const mandatoryFieldRule = v => !!v || 'Ce champs est obligatoire!'

const password = ref('')
const passwordConfirmation = ref('')

const formIsValid = ref(false)

const showConfirmDialog = ref(false)

const props = defineProps({
    username: {
        type: String,
        required: true
    }
})

const emit = defineEmits(['close'])

const changePassword = async () => {
    const res = await api.put(`/auth/reset-password/${props.username}`, {password: password.value})
        .then(() => true)
        .catch(() => false)

    if(!res) {
        alert('Erreur')
    } else {
        alert('Succès')
    }

    showConfirmDialog.value = false
    password.value = ""
    passwordConfirmation.value = ""
    emit('close')
}

</script>