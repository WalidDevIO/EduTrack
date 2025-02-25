<template>
    <v-card
      class="mx-auto"
      max-width="400"
    >
        <v-toolbar
            color="orange-lighten-1"
        >  
        <v-toolbar-title>Message Board</v-toolbar-title>

        </v-toolbar>
  
        <v-list lines="two">
            <template v-for="message in messages" :key="message.id">
                <li @click="selectMessage(message)">
                    <b v-if="!message.readed">{{ message.text }}</b>
                    <b v-else>{{ message.text }}</b>
                </li>
                <VDivider/>
            </template>
        </v-list>
    </v-card>
  </template>

<script>
    export default {
    methods: {
        selectMessage(item) {
        this.$emit("message-selected", item); // Envoie le message cliqué au parent
        }
    },
    props: {
        messages: {
            type: Array,
            required: true
        }
    }
}
</script>