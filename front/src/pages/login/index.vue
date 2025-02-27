<script setup>
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore()
</script>

<template>
    <LoginForm :auth="authStore.login"/>
</template>