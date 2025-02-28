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
        if(await authStore.checkConnection()) {
            //C'est ok son token est valide on le redirige
            loading.value = false
            handleLogged()
        }
    } else {
        //Il est connecté on le redirige
        handleLogged()
    }
    
    //Toujours pas connecté on l'envoie vers login
    if(!authStore.isLogged) router.push('/login')
})

const handleLogged = () => {
    if(authStore.role === "admin") router.push('/admin/dashboard')
    else router.push('/etudiant/dashboard')
}

</script>

<template>
    <Loader v-model="loading"/>
</template>