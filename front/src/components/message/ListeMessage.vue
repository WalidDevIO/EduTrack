<template>
    <v-card class="mx-auto message-card" max-width="400">
      <v-toolbar color="orange lighten-1" dark>
        <v-toolbar-title>Message Board</v-toolbar-title>
      </v-toolbar>
  
      <v-list two-line>
        <template v-for="message in messages" :key="message.id">
          <v-list-item @click="selectMessage(message)" class="message-item" :class="{ unread: !message.readed }">
            <v-list-item-content>
              <v-list-item-title v-if="!message.readed" class="font-weight-bold">{{ message.text }}</v-list-item-title>
              <v-list-item-title v-else>{{ message.text }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
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

<style scoped>
.message-card {
  margin-top: 20px;
}

.message-item {
  cursor: pointer;
}

.message-item.unread {
  background-color: #ffeb3b;
}

.message-item:hover {
  background-color: #f5f5f5;
}
</style>