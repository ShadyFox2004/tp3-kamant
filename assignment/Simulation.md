#class
1.  Cette classe est responsable de produire un nouvel Etat à partir de lancien état lorsquon appelle sa méthode *simulateStep*. Attention, la boucle de simulation principale devrait plutôt être dans *SimulationService* parce que ce dernier gère naturellement le temps, le lancement et larrêt du simulateur.
2.  La classe accumule également tous les résultats de simulation dans un attribut nommé *historique.* Le premier état de lhistorique contient les valeurs initiales qui proviennent de léditeur de simulation (liste de variables).
3.  Pour calculer un nouvel état, il faut:
	1.  À chaque pas de temps, il faut mettre à jour les valeurs de ***t*** et ***dt*** avant de calculer les valeurs des autres variables avec les équations.
	2.  À chaque pas de temps il faut recalculer chaque variable qui possède des équations. **IMPORTANT** pour chacune, il faut additionner la contribution de chaque équation à la valeur actuelle (au début du pas de temps) de la variable.
	3. Les résultats sont placés dans un nouvel état qui sera retourné par la méthode