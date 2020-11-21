# Spécification des informations échangées
## Format d'un message :
 TAG$POST
## Contenu (POST) d'un message :
* pseudonyme
* date - format : "jj/MM/yyyy HH:mm"
* contenu
## En-têtes (TAG) possibles :
| TAGs | POST format | Information Flow Direction |
|-----|-------------|----------------------------|
|JOIN|DATE$PSEUDO$CHATROOM|server <- client|
|LEAVE|DATE$PSEUDO|server <- client|
|CREATE|DATE$PSEUDO$CHATROOM|server <- client|
|POST|DATE$PSEUDO$MSG|server <- client|
|PUBLISH|MSG|server -> client|
|INFORM|DATE$PSEUDO$MSG|server -> client|

 