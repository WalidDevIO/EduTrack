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
                <ListeFormation />
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
                <ListeUE />
            </div>
        </v-container>
    </v-app>
</template>