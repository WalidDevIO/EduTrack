# Front-end de Gestion des Étudiants et Formations

Cette application front-end permet aux administrateurs et aux étudiants d'interagir avec une API core pour gérer les informations des étudiants, des formations, des unités d'enseignement, ainsi que diverses actions administratives et consultatives.

## Fonctionnalités

### Pour l'Administrateur :
- **Accepter / Refuser un étudiant à une formation** : Gérer les candidatures des étudiants à une formation.
- **Consulter les étudiants d'une formation** : Visualiser les étudiants inscrits à une formation spécifique.
- **Réinitialiser les mots de passe des étudiants** : Permet à l'administrateur de réinitialiser le mot de passe d'un étudiant.
- **Attribuer des groupes aux étudiants** : Assigner les étudiants à des groupes (ex : groupes de TD/TP).
- **Créer et modifier des formations** : Permet à l'administrateur de créer de nouvelles formations et de modifier les formations existantes.
- **Créer et modifier des unités d'enseignement** : L'administrateur peut créer et modifier les unités d'enseignement, ainsi que les rendre obligatoires ou optionnelles selon la formation.

### Pour l'Étudiant :
- **Consulter son profil** : Accéder aux informations personnelles de l'étudiant, ses groupes, ses formations, et l'état d'acceptation de sa candidature.
- **Choisir une formation** : Permet à l'étudiant de sélectionner une formation pour laquelle il postule ou est déjà inscrit.
- **Consulter ses options de formation** : Afficher les différentes options disponibles dans la formation.
- **Consulter ses messages** : Accéder aux messages reçus concernant la formation ou son profil.
- **Voir les enseignements qu'il doit suivre selon sa formation** : Afficher les unités d'enseignement et les cours que l'étudiant doit suivre pour sa formation.

Le front-end est accessible sur le port 3000 et interagit avec l'API via le même URL mais sur le port 8080.

## Installation

### 1. Cloner le projet

```bash
git clone https://github.com/ton-projet/front-end-gestion-etudiants.git
cd front-end-gestion-etudiants
```

### 2. Installer les dépendances

Installez les dépendances nécessaires avec npm ou yarn :

```bash
pnpm install
```
ou
```bash
npm install
```

### 3. Lancer l'application en mode développement

Pour démarrer le front-end en mode développement, utilisez la commande suivante :

```bash
pnpm dev
```
ou
```bash
npm dev
```

Cela lancera l'application sur `http://localhost:3000` par défaut.