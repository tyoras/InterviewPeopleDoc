# Interview PeopleDoc
==============
Ce test consiste à réaliser une petite web app autour d'un api Rest. 
Le rôle de l'application est de choisir aléatoirement un restaurant où manger le midi parmi une liste.
   

    POST /restaurants
    {'restaurant': 'resto1'}
Ajoute un restaurant à l'application. Ce restaurant est persisté.

    DELETE /restaurants/resto_name
Supprime le restaurant de l'application et de son support de persistance.

    GET /restaurants
Liste les restaurants insérés dans l'application.

    GET/restaurants/random
Choisi de manière aléatoire, l'un des restaurants de l'application.


- *Les tests sont aussi importants que le code ;*
- *Toute fonctionnalité supplémentaire est la bienvenue.*

## Usage
- Install & setup postgres 9.4 avec user : pg_user / secret
- Créer db : restaurantdb
- Création de la table avec : java -jar target/PeopleDoc-0.0.1-SNAPSHOT.jar db migrate restaurant-chooser.yml
- Build du projet : mvn clean install
- java -jar target/PeopleDoc-0.0.1-SNAPSHOT.jar server restaurant-chooser.yml
- Créer des restaurants : curl -X POST -H "Content-Type: application/json" -d '{ "restaurant" : "resto3" }' "http://localhost:8080/restaurants"
- Lister : curl -X GET -H "Accept: application/json" "http://localhost:8080/restaurants"
- Random restaurant : curl -X GET -H "Accept: application/json" "http://localhost:8080/restaurants/random"
- Supprimer des restaurants : curl -X DELETE "http://localhost:8080/restaurants/{restaurantName}"
