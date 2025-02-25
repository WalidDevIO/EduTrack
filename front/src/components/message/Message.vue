<template>
    <div>
      <ListeMessage @message-selected="handlerClick" :messages="messages" />
      <DetailsMessage :messageSelected="messageSelected" />
    </div>
  </template>
  
  <script>
  import ListeMessage from './ListeMessage.vue';
  import DetailsMessage from './DetailsMessage.vue';
  
  export default {
    data() {
        return {
            messageSelected: null,
            messages: []
        };
    },
    methods: {
      handlerClick(item) {
        this.messageSelected = item;
        fetch('http://localhost:8085/api/messages/' + item.id, {
          method: "PUT",
          body: JSON.stringify({...item, readed: true}),
        })
        .then(response => {
          if(response.ok) {
            this.messages.find(m => m.id === item.id).readed = true;
          }
        });
      },
      fetchAllMessages() {
        fetch('http://localhost:8085/api/messages/' + 220202)
          .then(response => response.json())
          .then(data => {
            this.messages = data;
          });
      }
    },
    // Récupère les messages lorsque l'on ouvre la page
    created() {
        this.fetchAllMessages()
    },
  };
  </script>