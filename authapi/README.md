# API de Gestion d'Authentification

Cette API gère l'authentification des utilisateurs pour une application. Elle permet aux utilisateurs de s'inscrire, de se connecter, de réinitialiser leur mot de passe, de valider leur jeton d'authentification, et de se désinscrire ou se déconnecter.

## Fonctionnalités

- **Inscription** : Permet à un utilisateur de créer un compte.
- **Connexion** : Permet à un utilisateur de se connecter avec ses identifiants.
- **Réinitialisation du mot de passe** : Permet de changer le mot de passe d'un utilisateur.
- **Validation du jeton** : Permet de vérifier si un jeton d'authentification est valide.
- **Désinscription** : Permet à un utilisateur de supprimer son compte.
- **Déconnexion** : Permet à un utilisateur de se déconnecter.

## Variables d'Environnement

L'API utilise plusieurs variables d'environnement pour se connecter à la base de données et configurer certains paramètres. Voici les variables nécessaires :

- `DB_HOST`: Hôte du serveur de base de données.
- `DB_PORT`: Port du serveur MySQL.
- `DB_USER`: Utilisateur de la base de données MySQL.
- `DB_PASS`: Mot de passe de la base de données MySQL.
- `DB_NAME`: Nom de la base de données MySQL.

### Exemple de configuration dans un fichier `.env` :
```env
DB_HOST=db #Par défaut: db
DB_PORT=3306 #Par déaut: 3306
DB_USER=root #Par défaut: root
DB_PASS=root #Par défaut: root
DB_NAME=auth #Par défaut: auth
```

## Base de Données

L'application utilise une base de données MySQL (ou MariaDB). Vous devez vous assurer que cette base de données est configurée et accessible à l'application.

## Installation

### 1. Cloner le projet
```bash
git clone https://gitlab-depinfo-2024.univ-brest.fr/e22000812/projet_s8_gestion.git
cd projet_s8_gestion/authapi
```

### 2. Construire l'application
```bash
./gradlew build
```

### 3. Construire l'image Docker
Assurez-vous que Docker est installé et exécuté sur votre machine, puis lancez la commande suivante :
```bash
docker build . -t auth-api
```

### 3. Lancer l'application en utilisant Docker
```bash
docker run -p 8080:8080 --env-file .env auth-api
```

### 4. Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:8080/api`.

## Docker Configuration

L'application utilise Docker pour le déploiement. Vous pouvez la lancer facilement avec un fichier `Dockerfile` fourni dans le projet.

### Variables d'Environnement Docker

Pour configurer les variables d'environnement avec Docker, vous pouvez utiliser un fichier `.env` ou définir les variables directement dans la commande Docker. Assurez-vous que les variables sont définies comme spécifié dans la section des **Variables d'Environnement**.