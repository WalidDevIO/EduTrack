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
DB_NAME=student #Par défaut: student
```

## Base de Données

L'application utilise une base de données MySQL (ou MariaDB). Vous devez vous assurer que cette base de données est configurée et accessible à l'application.

## Installation

### 1. Cloner le projet
```bash
git clone https://gitlab-depinfo-2024.univ-brest.fr/e22000812/edutrack.git
cd edutrack/studentapi
```

### 2. Construire l'application
```bash
./gradlew build
```

### 3. Construire l'image Docker
Assurez-vous que Docker est installé et exécuté sur votre machine, puis lancez la commande suivante :
```bash
docker build . -t student-api
```

### 4. Lancer l'application en utilisant Docker
```bash
docker run -p 8080:8080 --env-file .env student-api
```

### 5. Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:8080/api`.

## Docker Configuration

L'application utilise Docker pour le déploiement. Vous pouvez la lancer facilement avec un fichier `Dockerfile` fourni dans le projet.

### Variables d'Environnement Docker

Pour configurer les variables d'environnement avec Docker, vous pouvez utiliser un fichier `.env` ou définir les variables directement dans la commande Docker. Assurez-vous que les variables sont définies comme spécifié dans la section des **Variables d'Environnement**.

## API Endpoints

Vous pouvez trouver une spécification OpenAPI dans `src/main/resources/static/openapi.yaml`
Si vous souhaitez les visualiser sous forme de Swagger, [Cliquez ici](https://editor.swagger.io/)