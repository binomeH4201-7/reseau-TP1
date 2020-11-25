# TP 1 : Serveur Multicast, protocole UDP

## Introduction
Ce projet est fourni avec un Makefile qui permet de compiler et exécuter le projet en ligne de commande. Il est également possible de réaliser ces actions via un IDE. Les principales commandes sont :
    * `all : génère les éxécutables`
    * `clean : supprime les éxécutables`
    * `start-multicast : lance le serveur`
    * `help : affiche une aide sommaire`

## I/Complier le projet
La commande `make` permet de compiler le projet en ligne de commande.

## II/Démarrer un client Multicast
La commande `make start-multicast` permet de lancer un client multicast. Par défaut, le port est le port `5100`, et l'adresse est `224.0.0.1`. Il est possible de préciser un port et une adresse avec la commande :

`make start-server port=<numero-port>`

## III/Communiquer
La communication Multicast est très basique. Il suffit d'écrire dans l'invite de commande, d'appuyer sur `entrée` et le message sera diffuser à tous. Une interface graphique et un historique ont été implémentés dans la partie TCP.
