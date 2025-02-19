
- API de gestion des messages des étudiants
- BDD: MongoDB
- Techno sous-jacente: Servlet

##### Schéma Mongo

```
[
	{
		_id: ObjectId,
		student: Integer,
		text: String,
		readed: Boolean
	}
]
```

##### Endpoint: 

Se référer à la spécification OpenAPI

##### Côté front

Dans l'espace réservé aux étudiants

Une page est dédiée à la messagerie / Un composant est dédié à la messagerie
	 - Hook (fonction) `onMounted` qui appelle l'endpoint de Core qui renverra la réponse de la requête GET `/messages/{student}`, placer le tableau reçu en réponse dans une référence, effectuer un v-for des messages dans un select par exemple ou sous forme de chat
	- Event Handler (@) `onClick` sur chaque message qui appellera l'endpoint PUT `/messages/{id}`