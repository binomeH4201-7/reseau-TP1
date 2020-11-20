.PHONY: all, help, cleanCode, cleanHistory, clean, start-server, start-client

JC = javac
JE = java
JCP = -classpath ./classes/
JOPT = -d ./classes

SOURCES = ${wildcard src/*.java}
CLASSES = ${SOURCES:src/%.java=classes/%.class}
HISTORY = ${wildcard history/*.save}

SERVER = src.EchoServerMultiThreaded
CLIENT = src.ClientLauncher
DEFAULT_PORT = 5100

port ?= $(DEFAULT_PORT)

all : $(CLASSES)
	@echo "Compilation du projet terminée."

classes/%.class : src/%.java
	@echo "compilation de <$<>"
	@$(JC) $(JOPT) $<

cleanCode :
	@echo "Suppression des fichiers compilés."
	@rm ./classes/src/*.class -vf

cleanHistory :
	@echo "Suppression des sauvegardes d'historiques."
	@rm $(HISTORY) -vf

clean : cleanCode cleanHistory

start-client :
	$(JE) $(JCP) $(CLIENT)

start-server :
	@echo "Port par défaut : $(DEFAULT_PORT)"
	@echo "Pour lancer le serveur sur un autre port :"
	@echo "     make start-server port=<numero-port>"
	@echo
	@echo "=========================================="
	@echo
	@echo "Serveur lancé sur le port $(port)"
	@$(JE) $(JCP) $(SERVER) $(port)
	
help :
	@echo "======================================================="
	@echo "Pour compiler l'application :"
	@echo "     make"
	@echo "======================================================="
	@echo "Port par défaut du serveur: $(DEFAULT_PORT)"
	@echo "Pour lancer le serveur sur un autre port :"
	@echo "     make start-server port=<numero-port>"
	@echo "========================================================"
	@echo "Le client possède une interface graphique"
	@echo "Pour lancer le client :"
	@echo "     make start-client"
	@echo "========================================================"
	@echo "Pour nettoyer les dossiers :"
	@echo "     make clean"
	@echo "/!\ Cette commande supprime historiques et éxecutables."
	@echo "Pour supprimer uniquement l'historique :"
	@echo "     make cleanHistory"
	@echo "Pour supprimer uniquement les éxecutables :"
	@echo "     make cleanCode"
	@echo "========================================================="
	@echo "Pour plus d'information quant à l'utilisation se référer"
	@echo "au fichier Readme.md ainsi qu'au contenu du dossier doc/"
	@echo "========================================================="
