<template>
  <template v-if="isLogged">
    <VContainer class="h-100">
      <VSheet :elevation="5" class="h-75 pa-5 rounded-lg">
        <h2 class="mb-2">Bienvenue</h2>
      </VSheet>
    </VContainer>
  </template> 
  <template v-else>
    <LoginForm :login="login"/>
  </template>
</template>

<script setup>
import LoginForm from '@/components/LoginForm.vue';
import { ref } from 'vue';

const isLogged = ref(false)

const login = (username, password) => {
  console.log(username, password)
  //Requete API
  isLogged.value = true
  fetch("fac/api/coetudiant", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: username,
      password: password
    })
  })
}
</script>
