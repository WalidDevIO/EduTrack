<template>
    <VCard class="h-100">
        <v-toolbar>
            <v-toolbar-title>Mes messages</v-toolbar-title>
        </v-toolbar>

        <VList two-line>
            <template v-if="!messages.length">
                <VListItem>
                    Vous n'avez aucun message
                </VListItem>
            </template>
            <template v-for="(message, idx) in messages" :key="message.id">
                <VListItem @click="emit('select', message)" class="cursor-pointer message">
                    <VRow class="pa-6">
                        <VListItemTitle v-if="!message.readed" class="font-weight-bold">
                            {{ message.text }}
                        </VListItemTitle>
                        <VListItemTitle v-else>
                            {{ message.text }}
                        </VListItemTitle>
                        <VSpacer />
                        <VBtn icon @click="emit('delete', message.id)">
                            <VIcon color="red">mdi-delete</VIcon>
                        </VBtn>
                    </VRow>
                </VListItem>
                <v-divider v-if="idx !== messages.length - 1" color="#000000" opacity="0.4" />
            </template>
        </VList>
    </VCard>
</template>

<script setup>
const props = defineProps({
    messages: {
        type: Array,
        required: true
    }
})

const emit = defineEmits(["select", "delete"])
</script>

<style scoped>
.message:hover {
    background-color: #f5f5f5;
}
</style>