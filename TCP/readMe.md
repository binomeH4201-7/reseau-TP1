# TP1 : Chat server
##Introduction
Ce projet est constitué d'un serveur de chat et de modules clients indépendants avec interface graphique.<br/><br/>
Ce projet est fourni avec un Makefile qui permet de compiler et exécuter le projet en ligne de commande. Il est également possible de réaliser ces actions via un IDE. Les principales commandes sont :
  * `all : génère les éxécutables`
  * `clean : supprime les éxécutables et réinitialise les ressources`
  * `cleanCode : supprime les éxécutables`
  * `cleanHistory: supprime les ressources`
  * `start-serveur : lance le serveur`
  * `start-client : lance un client`
  * `help : affiche une aide sommaire`
## I/Compiler le projet
La commande `make` permet de compiler le projet en ligne de commande.

## II/Lancer le serveur
La commande `make start-server` permet de lancer le serveur. Par défaut, le port est le port 5100. Il est possible de préciser un port avec la commande :

## III/Lancer un client
La commande `make start-client` permet de lancer un client. Une interface graphique permet de se connecter au serveur et d'envoyer des messages.
## IV/Interface graphique
L'interface graphique est décomposée en deux blocs principaux :
 * Gauche : Panneau d'affichage des messages
 * Droite : Panneau d'interaction
    * Encart connexion au serveur. Le Pseudonyme est lié à une connexion au serveur et sera visible par les autres utilisateurs.
        Une déconnexion réinitialise le pseudonyme.
    *  Encart chat rooms. Cet encart permet de consulter, créer, rejoindre et quitter une chatroom. Le nom d'un nouvelle chatroom est saisie dans le champ de texte à gauche du bouton "New".
        L'espace blanc en haut de l'encart est une liste dynamique des chat rooms. On rejoint l'une d'entre elle en la sélectionnant dans la liste puis en cliquant sur le bouton "Join". Attention à ne pas cliquer sur le bouton si aucune chat room n'est présente dans la liste.
    * Encart Message : Permet de taper et d'envoyer un nouveau message. Attention : l'envoi d'un message sur plusieurs lignes occasionne une erreur.

## Spécification des informations échangées
### Format d'un message :
 TAG$POST
### Contenu (POST) d'un message :
* pseudonyme
* date - format : "jj/MM/yyyy HH:mm"
* contenu
### En-têtes (TAG) possibles :
| TAGs | POST format | Information Flow Direction | Use |
|-----|-------------|----------------------------|------|
|JOIN|DATE$PSEUDO$CHATROOM|server <- client|Join an existing chatroom|
|LEAVE|DATE$PSEUDO|server <- client|Leave the current chatRoom|
|CREATE|DATE$PSEUDO$CHATROOM|server <- client|Create a chatroom|
|POST|DATE$PSEUDO$MSG|server <- client|Post a message|
|PUBLISH|MSG|server -> client|Publish a message|
|INFORM|CHATROOMNAME|server -> client|Inform about an existing chatroom|

 