.PHONY: all, help, clean

JC = javac
JE = java
JCP = -classpath ./classes/
JOPT = -d ./classes -sourcepath ./src

SOURCES = ${wildcard src/*.java}
CLASSES = ${SOURCES:src/%.java=classes/%.class}

MULTICAST = EchoMultiCast
DEFAULT_PORT = 5100
DEFAULT_ADDRESS = 224.0.0.1

port ?= $(DEFAULT_PORT)
address ?= $(DEFAULT_ADDRESS)

all : $(CLASSES)
	@echo "Compilation du projet terminée."

classes/%.class : src/%.java
	@echo "compilation de <$<>"
	@$(JC) $(JOPT) $<

clean :
	@echo "Suppression des fichiers compilés."
	@rm ./classes/*.class -vf

start-multicast : all
	@echo "Multicast d'adresse $(address) lancé sur le port $(port)"
	@$(JE) $(JCP) $(MULTICAST) $(address) $(port)
	
help :
	@echo "============================================================"
	@echo "Pour compiler l'application :"
	@echo "     make"
	@echo "============================================================"
	@echo "Pour lancer un multicast: "
	@echo "     make start-multicast"
	@echo "============================================================"
	@echo "Port par défaut: $(DEFAULT_PORT)"
	@echo "Adresse: $(DEFAULT_ADDRESS)"
	@echo "Pour lancer sur un autre port et/ou une autre adresse:"
	@echo "     make start-server port=<numero-port> address=<address>"
	@echo "============================================================"
	@echo "Pour nettoyer les dossiers :"
	@echo "     make clean"
	@echo "============================================================"
	@echo "Pour plus d'information quant à l'utilisation se référer"
	@echo "au fichier Readme.md ainsi qu'au contenu du dossier doc/"
	@echo "============================================================"
