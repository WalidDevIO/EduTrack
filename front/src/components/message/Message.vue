<template>
  <div class="message-container">
    <div class="message-list">
      <ListeMessage :messages="messages" />
    </div>
    <div class="message-details">
      <DetailsMessage :messageSelected="messageSelected" />
    </div>
  </div>
</template>
  
<script> 
  export default {
    data() {
        return {
            messageSelected: null,
            messages: []
        };
    },
    methods: {
      /*handlerClick(item) {
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
        fetch('http://localhost:8085/api/messages/student/' + 220202) // Remplacer 220202 par l'id de l'étudiant connecté
          .then(response => response.json())
          .then(data => {
            this.messages = data;
          });
      }*/
    },
    // Récupère les messages lorsque l'on ouvre la page
    created() {
        //this.fetchAllMessages()
    },
  };
</script>

<style scoped>
.message-container {
  display: flex;
  flex-direction: row;
  gap: 20px;
  padding: 20px;
}

.message-list {
  flex: 1;
  border-right: 1px solid #ccc;
  padding-right: 20px;
}

.message-details {
  flex: 2;
  padding-left: 20px;
}
</style>