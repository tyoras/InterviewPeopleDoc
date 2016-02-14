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
