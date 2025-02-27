<template>
    infos étudiant
    {{ etudiant.firstname }}
    {{ etudiant.surname }}
</template>

<script>
    export default {
        data : {
            etudiant: {}
        },
        methods: {
            fetchAllInfos() {
                fetch('http://localhost:8081/api/students/' + 1) // Remplacer 220202 par l'id de l'étudiant connecté
                    .then(response => response.json())
                    .then(data => {
                        this.etudiant = data;
                    });
            },
            created(){
                this.fetchAllInfos()
            }
        }
    }
</script>