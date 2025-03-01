<script setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const authStore = useAuthStore()
const router = useRouter()

onMounted(() => {
        if(authStore.role !== "admin") router.push('/')
})

</script>

<template>
    <Navbar/>
    <GestionAdmin/>
</template>