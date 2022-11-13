#class
Cette classe est un service JavaFX et elle est responsable de gérer le temps de la simulation. Elle doit appeler la méthode *simuleStep* de la simulation sélectionnée en lui envoyant le temps actuel et lintervalle de temps. Elle doit également envoyer létat actuel :
1.  Lorsquon redémarre la simulation on doit repartir de létat 0, si on redémarre après une pause il faut repartir du dernier état simulé.
2.  Le simulateur devrait pouvoir sarrêter par lui-même en fonction des états présents (par exemple lorsqu'un objet sort du cadre). Pour cela, il faut surveiller