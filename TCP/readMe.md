# Spécification des informations échangées
## Format d'un message :
 TAG$POST
## Contenu (POST) d'un message :
* pseudonyme
* date - format : "jj/MM/yyyy HH:mm"
* contenu
## En-têtes (TAG) possibles :
| TAGs | POST format | Information Flow Direction | Use |
|-----|-------------|----------------------------|------|
|JOIN|DATE$PSEUDO$CHATROOM|server <- client|Join an existing chatroom|
|LEAVE|DATE$PSEUDO|server <- client|Leave the current chatRoom|
|CREATE|DATE$PSEUDO$CHATROOM|server <- client|Create a chatroom|
|POST|DATE$PSEUDO$MSG|server <- client|Post a message|
|PUBLISH|MSG|server -> client|Publish a message|
|INFORM|CHATROOMNAME|server -> client|Inform about an existing chatroom|

 