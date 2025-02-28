<template>
    <VSheet elevation="5" class="mt-6 h-75 pa-5 rounded-lg w-100">
        <VContainer v-if="messages">
            <VRow>
                <VCol>
                    <ListeMessage @select="handleSelect" @delete="handleDelete" :messages="messages" />
                </VCol>
            </VRow>
        </VContainer>
    </VSheet>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { api } from '@/utils/axios';

const messages = ref()

const handleSelect = (msg) => {
    api.put('/students/messages/read', msg)
    messages.value.find(m => m === msg).readed = true
}

const handleDelete = (id) => {
    api.delete(`/students/messages/${id}`)
    messages.value = messages.value.filter(m => m.id !== id)
}

onMounted(() => {
    api.get('/students/messages').then(r => messages.value = r.data).catch(console.error)
})
</script>