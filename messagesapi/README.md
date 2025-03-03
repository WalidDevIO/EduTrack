# Messages API

Cette API permet de gérer les messages pour les étudiants. Elle offre des fonctionnalités pour récupérer, créer, mettre à jour et supprimer des messages associés à un étudiant spécifique.

## Fonctionnalités

- **Récupérer les messages d'un étudiant** : Permet de récupérer tous les messages associés à un étudiant donné.
- **Créer un message** : Permet de créer un nouveau message.
- **Récupérer un message par ID** : Permet de récupérer un message à partir de son ID unique.
- **Mettre à jour un message** : Permet de mettre à jour le contenu d'un message spécifique.
- **Supprimer un message** : Permet de supprimer un message spécifique.

## Variables d'Environnement

Cette API utilise des variables d'environnement pour configurer la connexion à la base de données MongoDB et d'autres paramètres :

- **`DB_HOST`** : Hôte de la base de données MongoDB.
- **`DB_NAME`** : Nom de la base de données MongoDB.

### Exemple de configuration dans un fichier `.env` :
```env
DB_HOST=mongodb #Par défaut: mongodb
DB_NAME=main #Par défaut: main
```

## Base de Données

L'API utilise **MongoDB** comme base de données pour stocker les messages. La base de données est accessible de manière anonyme, sans authentification.

### Base de données MongoDB

- **Hôte** : `mongodb` (en fonction de votre configuration Docker).
- **Base de données** : Les messages sont stockés dans la base `main` de MongoDB.
- **Pas d'authentification requise** : L'accès à MongoDB est configuré sans authentification.

## Installation

### 1. Cloner le projet
```bash
git clone https://gitlab-depinfo-2024.univ-brest.fr/e22000812/edutrack
cd edutrack/messagesapi
```

### 2. Construire l'application
```bash
mvn clean package
```

### 3. Créer l'image de l'application avec Docker

Assurez-vous que Docker est installé et exécuté sur votre machine, puis construisez et lancez l'application avec la commande suivante :

```bash
docker build . -t message-api
```

### 4. Lancer l'image de l'application avec Docker
```bash
docker run -p 8080:8080 --env-file .env message-api
```

### 5. Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:8080/api`.

### Variables d'Environnement Docker

Pour configurer les variables d'environnement avec Docker, vous pouvez utiliser un fichier `.env` ou définir les variables directement dans la commande Docker. Assurez-vous que les variables sont définies comme spécifié dans la section des **Variables d'Environnement**.

## API Endpoints

Vous pouvez trouver une spécification OpenAPI dans `src/main/resources/openapi.yml`
Si vous souhaitez les visualiser sous forme de Swagger, [Cliquez ici](https://editor.swagger.io/)