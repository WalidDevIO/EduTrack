# Projet de Gestion des Effectifs Étudiants

## Description

Ce projet consiste à développer une application web de gestion des effectifs étudiants pour l'Université de Bretagne Occidentale (UBO). L'application permet aux étudiants de s'inscrire à des années universitaires, de choisir des options et de gérer leurs groupes de TD/TP. Elle offre également des fonctionnalités pour le responsable des formations afin de gérer les inscriptions et les affectations des étudiants.

## Fonctionnalités

- **Pour les étudiants :**
  - Création de compte avec génération automatique de numéro d'étudiant.
  - Choix et validation des années de formation et des options.
  - Visualisation des informations personnelles et académiques.
  - Réception et lecture des messages et notifications.

- **Pour le responsable des formations :**
  - Création et gestion des années de formation.
  - Définition des UE obligatoires et optionnelles.
  - Affectation automatique ou manuelle des étudiants aux groupes de TD/TP.
  - Envoi de notifications aux étudiants concernant les modifications d'affectations.

## Technologies Utilisées

- **Backend :**
  - Java avec Spring pour certaines API.
  - Servlets pour d'autres API.
  - JPA pour l'accès aux données.

- **Bases de données :**
  - SQL pour les informations sur les étudiants et les données d'authentification.
  - MongoDB pour le stockage des messages.

- **Frontend :**
  - Vue.js pour l'interface client web.

## Nos contributions au projet

- **API Authentification :** Gère l'authentification.
- **API Etudiant :** Gère les informations et inscriptions des étudiants.
- **API Messagerie :** Gère l'envoi et la réception des messages.

## Utilisation du projet

### Nous avons opté pour un lancement simple basé sur Docker

### Étape 1:
Assurez vous d'avoir sur votre machine Git, JDK17, Maven, Docker, et pnpm

Clonez le dépôt et rendez-vous dans le dossier racine de celui-ci
```bash
git clone https://gitlab-depinfo-2024.univ-brest.fr/e22000812/edutrack.git
cd edutrack
```

### Étape 2:
Lancez la commande:
```bash
./build.sh
```

Celle-ci aura pour effet de créer les JAR et WAR des APIs et d'installer les dépendances Node du front ainsi que de l'API Core de secours

### Étape 3:
Lancez la commande:
```bash
docker compose up --build
```
Cette dernière va lancer toute la stack qui comprend:
  - L'API Message
  - L'API Étudiant
  - L'API Authentification
  - L'API Core (de secours)
  - L'application Vue.JS
  - Un serveur SGBD MariaDB (pré initialisé)
  - Un server SGBD MongoDB

### Étape 4:
Accéder à l'application VueJS sur le port 80 de votre machine local (`http://localhost`)

La stack n'expose uniquement que deux services
 - Le Front-End sur le port 80
 - L'API Core de secours sur le port 8080

Si vous souhaitez tester un service particulier de la stack, modifiez le fichier `docker-compose.yml` pour l'exposer sur un port
Les ports sur lesquelles tournent les différents services:
 - API Étudiant, Authentification, Messages: 8080
 - Base MariaDB: 3306
 - Base MongoDB: 27017

Les identifiants par défaut du compte administrateur sont: `admin` et `password`.

## Plus d'informations

Si vous souhaitez plus d'informations sur les différents services que nous avons développé, vous pouvez consulter les différents README dans les répertoire propre à ces derniers

## Membres de l'Équipe

- EL OUAZIZI Walid - Développeur
- MAHIER Romain - Développeur
- DELAVILLE--DE LA PARRA Baptiste - Développeur