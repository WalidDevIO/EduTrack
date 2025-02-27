<script setup>
import DataTableFormation from '@/components/formation/DataTableFormation.vue';
import DataTableUe from '@/components/formation/DataTableUe.vue';

</script>

<template>
    <v-app>
        <v-container>
            <div class="py-5">
                <Message />
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <GestionAdmin />
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <InfosEtudiant />
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <DataTableFormation />
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <FormationForm />
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <LoginForm :auth="() => {}"/>
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <UeForm />
            </div>
            <v-divider></v-divider>
            <div class="py-5">
                <DataTableUe/>
            </div>
        </v-container>
    </v-app>
</template>