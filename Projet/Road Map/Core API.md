
- Route les appels entre les différentes API
- Fais office d'interface (Seul cette API est accessible publiquement)

Fonctionnement imaginé
- Cette API routera les requêtes reçus aux autres API
	-  Si la requête demandée ne nécessite pas d'authentification
		=> L'API fera simplement office de proxy ?
	-  Si la requête demandée nécessite de l'authentification
		=> L'API vérifie si le Header d'Authorization existe et est valide, si oui router la requête normalement en joignant les infos de l'utilisateur
		Sinon renvoie une erreur HTTP (401 ou 403)

Techno: Express si possible, sinon Spring Boot

À confirmer avec Levy Marques