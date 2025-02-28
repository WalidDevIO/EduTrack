<template>
    <LoginForm :auth="register" :registerMode="true"/>
</template>

<script setup>
import { api } from '@/utils/axios';

    const register = (data) => {
        api.post('/students/register', data)
            .then(r => alert(r.data.username))
            .catch(err => {
                alert("Erreur")
            })
    }
</script>