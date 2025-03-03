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

### Exemple de configuration dans un fichier `.env` :
```env
DB_HOST=mongodb
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
git clone https://github.com/ton-projet/messages-api.git
cd messages-api
```

### 2. Construire l'application
```bash
mvn clean package
```

### 3. Lancer l'application avec Docker

Assurez-vous que Docker est installé et exécuté sur votre machine, puis construisez et lancez l'application avec la commande suivante :

```bash
docker-compose up --build
```

### 4. Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:8080/api`.

## Docker Configuration

L'application utilise Docker et un fichier `Dockerfile` pour le déploiement. Voici la configuration pour la construction du container :

```Dockerfile
FROM walkloly/tomcat-si:latest

EXPOSE 8080

COPY ./target/servlet.war /usr/local/tomcat/webapps/api.war

environment:
      - DB_HOST=mongodb
depends_on:
      - mongodb
```

### Docker Compose

Assurez-vous que vous avez un fichier `docker-compose.yml` pour gérer le déploiement. Voici un exemple pour MongoDB et l'application :

```yaml
version: '3.7'

services:
  mongodb:
    image: mongo:latest
    container_name: mongodb
    ports:
      - "27017:27017"
    networks:
      - app-network

  api:
    build: .
    container_name: messages-api
    ports:
      - "8085:8080"
    environment:
      - DB_HOST=mongodb
    depends_on:
      - mongodb
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

### Variables d'Environnement Docker

Pour configurer les variables d'environnement avec Docker, vous pouvez utiliser un fichier `.env` ou définir les variables directement dans la commande Docker. Assurez-vous que les variables sont définies comme spécifié dans la section des **Variables d'Environnement**.

## API Endpoints

L'API expose les points de terminaison suivants pour gérer les messages :

- **`GET /messages/student/{student}`** : Récupérer tous les messages pour un étudiant donné.
- **`POST /messages`** : Créer un nouveau message.
- **`GET /messages/{id}`** : Récupérer un message par ID.
- **`PUT /messages/{id}`** : Mettre à jour un message par ID.
- **`DELETE /messages/{id}`** : Supprimer un message par ID.

## License

Ce projet est sous licence MIT.