<template>
    <AuthForm :auth="register" :registerMode="true"/>

    <VDialog v-model="showDialog">
        <VCard>
            <VCardTitle>
                {{ text !== "Erreur" ? "Inscription réussie" : "Erreur" }}
            </VCardTitle>
            <VCardText>
                {{ text }}
            </VCardText>
            <VCardActions class="d-flex justify-end">
                <VBtn @click="close">Fermer</VBtn>
            </VCardActions>
        </VCard>
    </VDialog>
</template>

<script setup>
import { api } from '@/utils/axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const showDialog = ref(false)
const text = ref()
const router = useRouter()

const register = (data) => {
    api.post('/students/register', data)
        .then(r => {
            showMessage("Votre nom d'utilisateur est : " + r.data.username)
        })
        .catch(() => {
            showMessage("Erreur")
        })
}

const close = () => {
    showDialog.value = false
    if(text.value !== "Erreur") {
        router.push('/login')
    }
}

const showMessage = (message) => {
    text.value = message
    showDialog.value = true
}
</script>