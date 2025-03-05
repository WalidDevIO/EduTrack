<template>
    <VSheet elevation="5" class="mt-6 pa-5 rounded-lg w-100">
        <VContainer v-if="model">
            <VRow>
                <VCol>
                    <ListeMessage @select="handleSelect" @delete="handleDelete" :messages="model" />
                </VCol>
            </VRow>
        </VContainer>
    </VSheet>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { api } from '@/utils/axios';

const model = defineModel()

const handleSelect = (msg) => {
    api.put('/students/messages/read', msg)
    model.value.find(m => m === msg).readed = true
}

const handleDelete = (id) => {
    api.delete(`/students/messages/${id}`)
    model.value = model.value.filter(m => m.id !== id)
}
</script>