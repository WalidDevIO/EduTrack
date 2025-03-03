# API Core de Gestion des Étudiants, Formations et Unités d'Enseignement

Cette API Core est une API de secours, développée principalement pour tester notre front-end en temps réel. Elle n'est pas exhaustive et est encore en développement. Ce n'est pas une API que nous devions créer à l'origine, mais nous avons pris l'initiative de la développer afin de rendre l'intégration et le test du front-end plus faciles et plus rapides.

Elle permet de gérer la partie **formation** et **unités d'enseignement (UE)** de l'application et se connecte aux API **auth**, **student** et **messages**. En gros, elle sert de **pont** pour gérer ces différents services et permet d'interagir avec eux via un point d'entrée unique.

## Fonctionnalités

- **Gestion des formations** : Permet de créer, modifier et gérer les formations.
- **Gestion des unités d'enseignement (UE)** : Permet de créer, modifier et attribuer des unités d'enseignement à des formations.
- **Connexion avec les API Auth, Student et Messages** : L'API Core relie les fonctionnalités d'authentification, de gestion des étudiants et des messages dans un même endroit.
- **Gestion des données des formations et UE** : Cette API interagit avec une base MongoDB pour stocker et gérer les données liées aux formations et unités d'enseignement.

## Installation

L'API Core est **dockerisée** et fonctionne avec **Express**. Voici comment l'installer et la mettre en fonctionnement.

### Prérequis

- **Docker** pour faire tourner l'application.
- **Node.js** (avec **pnpm** installé) pour le développement local.
- **MongoDB** pour la gestion des formations et des unités d'enseignement.

### Étapes d'installation

1. **Cloner le projet** :

```bash
git clone https://gitlab-depinfo-2024.univ-brest.fr/e22000812/edutrack.git
cd edutrack/expressApi
```

2. **Installer les dépendances** :

Cette API utilise **pnpm** pour la gestion des dépendances. Si vous ne l'avez pas déjà installé, vous pouvez le faire en suivant [la documentation officielle de pnpm](https://pnpm.io/). Une fois installé, vous pouvez installer les dépendances avec la commande suivante :

```bash
pnpm i
```

3. **Construire l'application (si nécessaire)** :

Si vous souhaitez compiler l'application, vous pouvez utiliser la commande suivante pour la construire :

```bash
pnpm build
```

4. **Lancer l'application en mode développement** :

Si vous souhaitez démarrer l'application en mode développement pour tester en temps réel, utilisez :

```bash
pnpm dev
```

5. **Servir l'application** :

Si vous voulez servir l'application en production, vous pouvez utiliser `serve` après avoir construit l'application :

```bash
pnpm i -g serve
serve build
```

### Configuration

L'API nécessite une base de données MongoDB pour la gestion des formations et des unités d'enseignement. Il n'y a pas beaucoup de configuration nécessaire, à part l'accès à cette base.

- **Accès à MongoDB** : Vous devez avoir une instance MongoDB fonctionnelle, à laquelle l'API pourra se connecter pour stocker les données des formations et unités d'enseignement.
  
- **Configuration des URL des API externes** :
  - **API Auth** : L'URL de l'API d'authentification.
  - **API Student** : L'URL de l'API de gestion des étudiants.
  - **API Messages** : L'URL de l'API de gestion des messages.

Les URL de ces API doivent être spécifiées dans les variables d'environnement ou dans les fichiers de configuration appropriés de l'API Core.

### Exemple de fichier `.env` (si nécessaire) :

```env
MONGO_DB=core #Par défaut: core
MONGO_URI=mongodb://localhost:27017 #Par défaut: mongodb://mongodb:27017
API_AUTH_URL=http://localhost:8080/api/auth #Par défaut: http://authapi:8080/api/auth
API_STUDENT_URL=http://localhost:8080/api/student #Par défaut: http://studentapi:8080/api/students
API_MESSAGES_URL=http://localhost:8080/api/messages  #Par défaut: http://messageapi:8080/api/messages
```

### Docker

L'API est dockerisée, ce qui permet de la lancer rapidement dans un conteneur Docker. Vous pouvez l'exécuter en utilisant les commandes Docker standard, mais pour plus de simplicité, vous pouvez utiliser **pnpm** en local.

#### Construire l'image docker
Assurez-vous que Docker est installé et exécuté sur votre machine, puis lancez la commande suivante :
```bash
docker build . -t core-api
```

#### Lancer l'image docker
```bash
docker run -p 3000:3000 --env-file .env core-api
```

#### Accéder à l'API
Une fois l'application lancée, vous pouvez accéder à l'API à l'adresse suivante : `http://localhost:3000/api`.

## Limitations

- Cette API n'est **pas exhaustive** et ne couvre pas toutes les fonctionnalités possibles.
- Il est principalement destiné à des fins de **tests du front-end**.
- Il y a peu de logique métier dans cette API, elle sert surtout de pont pour la gestion des données.