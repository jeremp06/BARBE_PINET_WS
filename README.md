# BARBE_PINET_WS
Groupe composé de Brenda Barbe et Pinet Jérémy

Ce projet contient le WS ainsi que le client HTML

Fonctionnel : CRUD Livres, CRUD Categorie

Non fonctionnel : sélection Categorie -> affichage des livres associé (problème multiple binding) et la recherche (problème de temps)

De plus nous avons plusieurs onglets (Categorie et Livre) mais nous avons toujours le même problème de multiple binding, de ce fait pour passer d'un onglet a l'autre il faut forcement recharger la page puis choisir l'onglet voulu. Si vous cliquez sur un autre onglet ou si vous rechargez l'onglet actuel (en cliquant sur l'onglet pas en rechargeant la page) cela a pour effet de vider les données récupérées.

De plus nous avons rencontrés ENORMEMENTS de problèmes avec la liaison knockout + glassfish surtout pour le delete, ce qui nous a fait perdre beaucoup de temps.


PS : Il y a quelques commits apres minuit, mais c'est parce que j'ai ajouté le ReadME.
