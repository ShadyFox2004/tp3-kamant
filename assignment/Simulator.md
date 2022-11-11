Un simulateur
1.  Le simulateur doit pouvoir connaître léquation qui est sélectionnée dans léditeur déquation. Cest cette dernière qui sera simulée.
2.  Le simulateur doit permettre de:
	1.  **modifier** la longueur des pas de temps (période)
	2.  **démarrer** la simulation;
	3.  **arrêter** la simulation;
	4. mettre la simulation en **pause**. Cette fonctionnalité est plus complexe, car il faut retirer le temps de pause du temps de la simulation. Vous devriez la faire à la fin.
3.  Le simulateur affiche un label qui indique le temps où est rendue la simulation.
4.  Le simulateur présente également une liste contenant toutes les variables de la simulation sélectionnée. Vous pouvez la mettre à jour avec un bouton, mais il est préférable quelle se mette à jour automatiquement avec un écouteur sur la liste de léditeur de simulations.
5.  Lutilisateur doit pouvoir choisir une variable et pendant la simulation cette variable sera mise à jour en continu dans un *LineChart*.
6.  Comme toutes les simulations sont temporelles, le *LineChart* présente toujours le temps en abscisse et la valeur de la donnée choisie en ordonnée.
7.  Pour un extra vous pouvez permettre dafficher plusieurs variables sélectionnées en même temps, mais cest significativement plus difficile. Nessayez de la faire quà la toute fin.

