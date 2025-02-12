
- API de gestion des messages des étudiants
- BDD: MongoDB
- Techno sous-jacente: Servlet

##### Schéma Mongo

```
[
	{
		_id: ObjectId,
		numeroEtudiant: Integer,
		text: String,
		lu: Boolean
	}
]
```

##### Endpoint: 

- POST `/messages/:num` : Ajoute un message non lu de texte fourni dans le body de numéro étudiant `:num`
	- Nécessite que l'expéditeur soit le système

- GET `/messages/:num` : Retourne un tableau des messages de numéro étudiant `:num`
	- Nécessite l'authentification de l'étudiant de numéro `:num`

- PUT `/messages/:idMessage` :   Modifie le message d'ObjectId `idMessage` 
	- Pas besoin de body, un appel ici => mettre lu à `true` pour le msg concerné


Si on souhaite faire en sorte qu'un étudiant puisse supprimer des messages

- DELETE `/messages/:idMessage` : Supprime le message d'`idMessage`
	- Nécessite l'authentification de l'étudiant destinataire du message


##### Côté front

Dans l'espace réservé aux étudiants

Une page est dédiée à la messagerie / Un composant est dédié à la messagerie
	 - Hook (fonction) `onMounted` qui appelle l'endpoint de Core qui renverra la réponse de la requête GET `/messages/:num`, placer le tableau reçu en réponse dans une référence, effectuer un v-for des messages dans un select par exemple ou sous forme de chat
	- Event Handler (@) `onClick` sur chaque message qui appellera l'endpoint PUT `/messages/:idMessage`