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
  - SQL pour les informations sur les étudiants et les années de formation.
  - MongoDB pour le stockage des messages.

- **Frontend :**
  - Vue.js pour l'interface client web.

## Nos contributions au projet

- **API Core :** Point d'entrée unique pour toutes les requêtes, gère l'authentification et la redirection vers les API spécifiques.
- **API Etudiant :** Gère les informations et inscriptions des étudiants.
- **API Messagerie :** Gère l'envoi et la réception des messages.

## Membres de l'Équipe

- EL OUAZIZI Walid - Développeur
- MAHIER Romain - Développeur
- DELAVILLE--DE LA PARRA Baptiste - Développeur