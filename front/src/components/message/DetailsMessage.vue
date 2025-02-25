<template>
    <v-card class="details-card" v-if="messageSelected">
      <v-card-title class="headline">{{ messageSelected.text }}</v-card-title>
      <v-card-text>
        <p><strong>Lu:</strong> {{ messageSelected.readed ? "Oui" : "Non" }}</p>
      </v-card-text>
    </v-card>
  </template>
  
<script>
    export default {
        props: {
            messageSelected: Object  // Reçoit le message en prop
    }
};
</script>

<style scoped>
.details-card {
  padding: 20px;
  margin-top: 20px;
}
</style>