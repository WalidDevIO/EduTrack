<template>
    <div>
      <ListeMessage @message-selected="handlerClick" />
      <DetailsMessage :messageSelected="messageSelected" />
    </div>
  </template>
  
  <script>
  import ListeMessage from './ListeMessage.vue';
  import DetailsMessage from './DetailsMessage.vue';
  
  export default {
    data() {
        return {
            messageSelected: null
        };
    },
    methods: {
      handlerClick(item) {
        this.messageSelected = item;
      }
    }
  };
  </script>