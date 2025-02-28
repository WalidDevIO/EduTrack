<template>
  <div class="message-container" v-if="messages">
    <div class="message-list">
      <ListeMessage @message-selected="handleSelect" :messages="messages" />
    </div>
    <div class="message-details">
      <DetailsMessage :messageSelected="messageSelected" />
    </div>
  </div>
</template>
  
<script setup>
import { onMounted, ref } from 'vue';
import { api } from '@/utils/axios';

const messages = ref()
const messageSelected = ref()
 
const handleSelect = (message) => {
  messageSelected.value = message
}

onMounted(() => {
    api.get('/students/messages').then(r => messages.value = r.data).catch(console.error)
})
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