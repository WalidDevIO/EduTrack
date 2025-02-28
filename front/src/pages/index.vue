<script setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const authStore = useAuthStore()
const router = useRouter()
const loading = ref(true)

onMounted(async () => {
    //Arrivée sur l'app on check si l'user est co
    if(!authStore.isLogged) {
        //Si il ne l'est pas on vérifie via la méthode checkConnection
        const isTokenStoredValid = await authStore.checkConnection()
        if(!isTokenStoredValid) {
            //Token invalide donc => Redirection page de connexion
            router.push('/login')
            return
        }
    }
    //Redirection vers le bon endroit
    handleLogged()
    loading.value = false
})

const handleLogged = () => {
    if(authStore.role === "admin") router.push('/admin/dashboard')
    else router.push('/etudiant/dashboard')
}

</script>

<template>
    <Loader v-model="loading"/>
</template>