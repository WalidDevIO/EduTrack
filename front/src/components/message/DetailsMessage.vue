<template>
    <div v-if="messageSelected">
        <h2>{{ messageSelected.titre }}</h2>
        <p><strong>Auteur:</strong> {{ messageSelected.auteur }}</p>
        <p><strong>Date:</strong> {{ messageSelected.date }}</p>
        <p><strong>Contenu:</strong> {{ messageSelected.contenu }}</p>
    </div>
</template>
  
<script>
    export default {
        props: {
            messageSelected: Object  // Reçoit le message en prop
    }
};
</script>