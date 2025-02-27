<script setup>
import { useAuthStore } from '@/stores/auth';

const { login, role } = useAuthStore()
</script>

<template>
    <LoginForm :auth="login"/>
</template>