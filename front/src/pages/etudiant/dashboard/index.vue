<template>
    <div>
        <v-toolbar image="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg">
        <v-app-bar-nav-icon color="white"></v-app-bar-nav-icon>

        <v-toolbar-title>Dashboard Etudiant</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
            <v-icon color="white">mdi-export</v-icon>
        </v-btn>
        </v-toolbar>
    </div>
    <div style="display: flex;">   
        <v-card
        class="mx-auto my-8"
        elevation="16"
        max-width="344"
        >
            <v-card-item>
                <v-card-title>
                    Vos informations personnelles
                </v-card-title>
            </v-card-item>

            <v-card-text>
                <InfosEtudiant />
            </v-card-text>
        </v-card>
        <v-card
        class="mx-auto my-8"
        elevation="16"
        max-width="344"
        >
            <v-card-item>
                <v-card-title>
                    Informations sur votre formation
                </v-card-title>
            </v-card-item>

            <v-card-text>
                <InfosEtudiant />
            </v-card-text>
        </v-card>
    </div>
    <Message />
</template>

<script setup>
import InfosEtudiant from '@/components/etudiant/InfosEtudiant.vue';
import Message from '@/components/message/Message.vue';

</script>