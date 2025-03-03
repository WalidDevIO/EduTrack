# API de Gestion des Étudiants

Cette API permet de gérer les informations des étudiants. Elle permet la création, mise à jour, suppression et récupération des informations des étudiants dans une base de données **MariaDB** (ou une base compatible MySQL).

## Fonctionnalités
- **Récupérer touts les étudiants** (ou par formation)
- **Récupérer un étudiant** par son ID.
- **Mettre à jour un étudiant** avec ses nouvelles informations.
- **Supprimer un étudiant** à partir de son ID.
- **Ajouter un nouvel étudiant**.
- **Importer une liste d'étudiants**.

## Variables d'Environnement

L'API utilise plusieurs variables d'environnement pour se connecter à la base de données et configurer certains paramètres. Voici les variables nécessaires :

- `DB_HOST`: Hôte du serveur de base de données.
- `DB_PORT`: Port du serveur MariaDB/MySQL.
- `DB_ROOT_PASSWORD`: Mot de passe root de la base de données MariaDB/MySQL.

### Exemple de configuration dans un fichier `.env` :

```env
DB_HOST=db
DB_PORT=3306
DB_ROOT_PASSWORD=root
```

## Base de Données

L'application utilise une base de données MariaDB (ou compatible MySQL). Le nom de la base de données utilisée pour cette API est **`student`**. Vous devez vous assurer que cette base est configurée et accessible à l'application.

## Installation

### 1. Cloner le projet
```bash
git clone https://github.com/ton-projet/student-api.git
cd student-api
```

### 2. Construire l'application
```bash
./gradlew build
```

### 3. Lancer l'application en utilisant Docker

Assurez-vous que Docker est installé et en cours d'exécution sur votre machine, puis lancez l'application avec la commande suivante :

```bash
docker run -p 8081:8081 --env-file .env student-api
```

### 4. Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:8080/api`.

## Docker Configuration

L'application utilise Docker pour le déploiement. Vous pouvez la lancer facilement avec un fichier `Dockerfile` fourni dans le projet.

```Dockerfile
FROM openjdk:17-jdk-bullseye

WORKDIR /app
COPY ./build/libs/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Variables d'Environnement Docker

Pour configurer les variables d'environnement avec Docker, vous pouvez utiliser un fichier `.env` ou définir les variables directement dans la commande Docker. Assurez-vous que les variables sont définies comme spécifié dans la section des **Variables d'Environnement**.

## License

Ce projet est sous licence MIT.