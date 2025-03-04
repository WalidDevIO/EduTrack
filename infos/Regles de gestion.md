- taille groupe de TP / TD <= taille max groupe TP/ TP
- Etudiant = 1 groupe TD et 1 groupe TP au maximum
- nbEtudiant d'une UE opt < capacité UE opt
- Nb d'options pour un étudiant = nbr opt défini dans la formation
- UE capacite défini à -1 = capacité infinie
- Etudiant est forcément associé a une UE associée à sa formation

## API

- authAPI => Gère l'authentification
- messagerieAPI => Gère la messagerie
- etudiantAPI => Gère les étudiants
- responsableAPI => Gère les responsables
- formationAPI ( Groupe, UE, Formation ) => Gère formations, groupes et UEs
- CoreAPI => Gère la logique entre les différentes APIs et le front (sera la seule exposée)
- EtudiantParserAPI => Parse des CSV d'étudiants
- FormationScraperAPI => Scrap les formations sur le site de l'UBO