.PHONY: all, cleanCode, cleanHistory, clean, start-server, start-client

JC = javac
JE = java
JCP = -classpath ./classes
JOPT = -d ./classes -sourcepath ./src

SOURCES = ${wildcard src/*.java}
CLASSES = ${SOURCES:src/%.java=classes/%.class}
HISTORY = ${wildcard history/*.save}

SERVER = EchoServerMultiThreaded
CLIENT = ClientLauncher
DEFAULT_PORT = 5100

port ?= $(DEFAULT_PORT)

all : $(CLASSES)
	@echo "Compilation du projet terminée."

classes/%.class : src/%.java
	@echo "compilation de <$<>"
	@$(JC) $(JOPT) $<

cleanCode :
	@echo "Suppression des fichiers compilés."
	@rm $(CLASSES) -vf

cleanHistory :
	@echo "Suppression des sauvegardes d'historiques."
	@rm $(HISTORY) -vf

clean : cleanCode cleanHistory

start-client :
	@$(JE) $(JCP) $(CLIENT)

start-server :
	@echo "Port par défaut : $(DEFAULT_PORT)"
	@echo "Pour lancer le serveur sur un autre port :"
	@echo "     make start-server port=<numero-port>"
	@echo
	@echo "=========================================="
	@echo
	@echo "Serveur lancé sur le port $(port)"
	@$(JE) $(JCP) $(SERVER) $(port)
