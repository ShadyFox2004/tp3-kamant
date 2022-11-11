Un éditeur de simulation:
1. L'éditeur de simulation doit comprendre au moins 3 *ListView*:
	1. la liste de toutes les simulations connues. Cette liste affiche:
		1. Le nom de la simulation;
		2. Le nombre de pas simulés.
	2. la liste de toutes les variables de la simulation qui est sélectionnée;
	3. le nom de la variable (celui qui est utilisé dans les équations);
	4. la valeur de la variable;
2. Toutes les simulations doivent avoir au minimum les variables suivantes:
	- ***t*** : représentant le temps depuis le début  de la simulation.
	- ***dt*** : linterval de temps en cours
	- ***stop*** : une variable permettant de stopper la simulation en fonction d'une équation logique;
	- la liste de toutes les équations qui modifient la variable sélectionnée de la simulation sélectionnée:
		- Le nom de léquation;
		- Lexpression mathématique de *MathXParser*. Voir *Equation* plus loin.
3. Léditeur de simulation doit offrir un moyen (bouton, menu contextuel...) pour que lutilisateur puisse:
	1. **Ajouter / modifier / effacer -\>** une simulation
	2. Ajouter / modifier / effacer / changer la valeur / dupliquer -\une variable
	3. **Ajouter / modifier / effacer** -\une équation
4. Lorsque lutilisateur sélectionne une simulation dans la première liste, la seconde liste doit afficher ses variables. Lorsque lutilisateur sélectionne une variable dans la seconde liste, la troisième liste devrait afficher les équations qui lui sont associées. Lutilisateur ne doit pas pouvoir ajouter une équation si une variable nest pas sélectionnée et pareillement pour les variables et les listes. Évidemment, en plus de mettre les différents objets correctement dans les listes, il faut les imbriquer correctement.
5. Simulation possède un historique qui est une liste de tous les états successifs qui ont été calculés. Un état est lensemble des variables dune simulation. Létat 0 est important, car il constitue les valeurs initiales desquelles repart toujours la simulation.
6. Léditeur de simulation doit également proposer une fonctionnalité pour **lire** et écrire les simulations complètes sur le disque (avec les variables et les équations).
7. Vous pouvez le faire avec des boutons ou des menus contextuels. Les fichiers peuvent être écrits en utilisant la sérialisation objet, mais attention les fichiers deviendront illisibles au fur et à mesure que vous modifierez le code.
8. Lutilisateur doit pouvoir choisir le nom et lemplacement du fichier lui-même. Utilisez un *FileChooser* (*showOpenDialog* et *showCloseDialog*). Les fichiers auront lextension \".***sim***\" ( exemple gravite**.sim**).