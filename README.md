# Projet Architecture de Service et Ingénierie logicielle

## Management Automatique des Salles de l'INSA

### Architecture et choix d'implémentation

Voir Rapport: ici mettre le chemin du rapport

### Mise en place

A la suite de soucis d'implémentation de jenkins, dus à nos anti-virus n'acceptant pas l'éxecution de *.war récemment modifiés, nous avons fait le choix de conserver un déploiement d'application par lancement de scripts et éxecutions d'applications java par eclipse.

Afin de déployer notre projet, sont nécessaires:
* Eclipse
* Maven
* Tomcat
* Springboot

#### Etapes de mise en place


Tout d'abord il est nécessaire de déployer l'architecture OM2M, pour cela appliquez les étapes suivantes:


* se rendre dans le dossier >Espace OM2M>GEI (in-cse)
	* sous windows, éxecuter start.bat
	* sous linux, éxecuter start.sh
* se rendre dans le dossier >Espace OM2M>Room1 (mn-cse)
	* sous windows, éxecuter start.bat
	* sous linux, éxecuter start.sh
* se rendre dans le dossier >Espace OM2M>Room2 (mn-cse)
	* sous windows, éxecuter start.bat
	* sous linux, éxecuter start.sh


Ensuite, il est nécessaire de récupérer les différents services java, et les adapter à votre machine:


* lancer eclipse

* cliquer sur File> Open Project From File System
* cliquer sur le bouton [directory]
* cliquer sur le dossier Services>classroomManagement
* cliquer sur le bouton [Finish]
* Dans le dossier classroomManagement dans votre ProjetExplorer, ouvrir pom.xml, dans l'overview sous Parent, s'assurer que la version de spring correspond à la votre
* clic droit sur le dossier classroomManagement dans votre ProjetExplorer, Maven > Update Project  
--
* cliquer sur File> Open Project From File System
* cliquer sur le bouton [directory]
* cliquer sur le dossier Services>scenarioManagement
* cliquer sur le bouton [Finish]
* Dans le dossier scenarioManagement dans votre ProjetExplorer, ouvrir pom.xml, dans l'overview sous Parent, s'assurer que la version de spring correspond à la votre
* clic droit sur le dossier scenarioManagement dans votre ProjetExplorer, Maven > Update Project  
--
* cliquer sur File> Open Project From File System
* cliquer sur le bouton [directory]
* cliquer sur le dossier Services>interfaceManagement
* cliquer sur le bouton [Finish]
* Dans le dossier interfaceManagement dans votre ProjetExplorer, ouvrir pom.xml, dans l'overview sous Parent, s'assurer que la version de spring correspond à la votre
* clic droit sur le dossier interfaceManagement dans votre ProjetExplorer, Maven > Update Project


Enfin, il est nécessaire de run les 3 services depuis eclipse:

* clic droit sur le dossier classroomManagement>src/main/java dans votre ProjetExplorer, Run as > Java Application
* clic droit sur le dossier scenarioManagement>src/main/java dans votre ProjetExplorer, Run as > Java Application
* clic droit sur le dossier interfaceManagement>src/main/java dans votre ProjetExplorer, Run as > Java Application


Ensuite, il vous suffit d'ouvrir le fichier SOADashboardRoom1.html sous le dossier test_dashboard, vous pourrez ensuite observer les différentes informations par room, apprécier les scenarios automatiques sous certaines conditions et manipuler les lampes et les fenêtres.

## Contributeurs

### Etudiants

* Lorine POSE
* Baptiste MENNESSON
* Brice MOUTANIN

### Tuteurs

* Nawal Guermouche
* Ghada Gharbi