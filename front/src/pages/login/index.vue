<script setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const authStore = useAuthStore()

const router = useRouter()

const handleLogin = async (credentials) => {
    if(await authStore.login(credentials)) {
        if(authStore.role === "admin") {
            router.push('/admin/dashboard')
        } else {
            router.push('/etudiants/dashboard/')
        }
    }
}

onMounted(() => {
    if(authStore.isLogged) router.push('/')
})
</script>

<template>
    <AuthForm :auth="handleLogin"/>
</template>