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
- `DB_ROOT_PASSWORD`: Mot de passe root de la base de données MySQL.

### Exemple de configuration dans un fichier `.env` :
```env
DB_HOST=db
DB_PORT=3306
DB_ROOT_PASSWORD=root
```

## Base de Données

L'application utilise une base de données MySQL nommée **`auth`**. Vous devez vous assurer que cette base de données est configurée et accessible à l'application.

## Installation

### 1. Cloner le projet
```bash
git clone https://github.com/ton-projet/auth-api.git
cd auth-api
```

### 2. Construire l'application
```bash
./gradlew build
```

### 3. Lancer l'application en utilisant Docker

Assurez-vous que Docker est installé et exécuté sur votre machine, puis lancez l'application avec la commande suivante :

```bash
docker run -p 8080:8080 --env-file .env auth-api
```

### 4. Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:8080/api`.

## Docker Configuration

L'application utilise Docker pour le déploiement. Vous pouvez la lancer facilement avec un fichier `Dockerfile` fourni dans le projet.

```Dockerfile
FROM openjdk:17-jdk-bullseye

WORKDIR /app
COPY ./build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Variables d'Environnement Docker

Pour configurer les variables d'environnement avec Docker, vous pouvez utiliser un fichier `.env` ou définir les variables directement dans la commande Docker. Assurez-vous que les variables sont définies comme spécifié dans la section des **Variables d'Environnement**.

## License

Ce projet est sous licence MIT.