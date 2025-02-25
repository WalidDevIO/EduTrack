<template>
    <div v-if="messageSelected">
        <h2>{{ messageSelected.text }}</h2>
        <p><strong>Lu:</strong> {{ messageSelected.readed ? "Oui" : "Non" }}</p>
    </div>
</template>
  
<script>
    export default {
        props: {
            messageSelected: Object  // Reçoit le message en prop
    }
};
</script>