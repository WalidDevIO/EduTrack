<script setup>
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const authStore = useAuthStore()

const router = useRouter()

const handleLogin = async (credentials) => {
    if(await authStore.login(credentials)) {
        if(authStore.role === "admin") {
            router.push('/admin/dashboard')
        } else {
            router.push('/etudiant/dashboard/')
        }
    }
}
</script>

<template>
    <LoginForm :auth="handleLogin"/>
</template>