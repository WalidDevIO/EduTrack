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
            <template v-for="(item, index) in items">
                <v-list-subheader v-if="item.header" :key="item.header" inset>
                {{ item.header }}
                </v-list-subheader>

                <v-divider v-else-if="item.divider" :key="index" inset></v-divider>

                <v-list-item
                v-else
                :key="item.titre"
                @click="selectMessage(item)" 
                >
                <template v-slot:title>
                    <div v-html="item.titre"></div>
                </template>

                <template v-slot:subtitle>
                    <div v-html="item.contenu"></div>
                </template>
                </v-list-item>
            </template>
        </v-list>
    </v-card>
  </template>

<script>
    export default {
    data : () => ({
        items: [
            {
                header : '24/02/2025'
            },
            {
                divider : true,
            },
            {
                id: 1,
                titre : "Message 1",
                contenu : "Contenu du message 1",
                auteur : "Auteur 1"
            },
            {
                id: 2,
                titre : "Message 2",
                contenu : "Contenu du message 2",
                auteur : "Auteur 2"
            },
            {
                id: 3,
                titre : "Message 3",
                contenu : "Contenu du message 3",
                auteur : "Auteur 3"
            },
            {
                header: '25/02/2025'
            },
            {
                divider : true,
            },
            {
                id: 4,
                titre : "Message 4",
                contenu : "Contenu du message 4",
                auteur : "Auteur 4"
            },
            {
                id: 5,
                titre : "Message 5",
                contenu : "Contenu du message 5",
                auteur : "Auteur 5"
            }
        ]
    }),
    methods: {
        selectMessage(item) {
        this.$emit("message-selected", item); // Envoie le message cliqué au parent
        }
    }
}
</script>