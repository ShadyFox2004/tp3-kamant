# tp3-kamant

Un projet qui ne vas pas se terminer a la derniere minute.

 ***Développement de programmes dans un*** & Simulateur linéaire
interactif\
***environnement graphique*** & JavaFX\
Automne 2022 & Service JavaFX\
Cégep Limoilou & Conception dapplication\
Département dinformatique & (20 %)\
()


[[Context]]

[[Objectifs]]

[[Remise]]

[[Éléments requis]]

# Architecture, démarche et code fourni

1.  Pour aider à vaincre le syndrome de la page blanche, on vous fournit
    quelques classes de départs et on vous propose une démarche
    davancement. Cela devrait vous permettre davancer sans faire
    derreurs trop importantes .

    1.  Tout le code qui vous est fourni peut être modifié librement. On
        vous le donne pour vous simplifier la tâche et pour vous sauver
        du temps. Attention, si vous vous en écartez trop, ce sera à
        vous den assumer les conséquences. Il sera plus difficile de
        vous aider à avancer.

        1.  ### simulation
            1.  Cette classe est responsable de produire un nouvel Etat
                à partir de lancien état lorsquon appelle sa méthode
                *simulateStep*. Attention, la boucle de simulation
                principale devrait plutôt être dans
                *SimulationService* parce que ce dernier gère
                naturellement le temps, le lancement et larrêt du
                simulateur.

            2.  La classe accumule également tous les résultats de
                simulation dans un attribut nommé *historique.* Le
                premier état de lhistorique contient les valeurs
                initiales qui proviennent de léditeur de simulation
                (liste de variables).

            3.  Pour calculer un nouvel état, il faut:

                1.  À chaque pas de temps, il faut mettre à jour les
                    valeurs de ***t*** et ***dt*** avant de calculer
                    les valeurs des autres variables avec les
                    équations.

                2.  À chaque pas de temps il faut recalculer chaque
                    variable qui possède des équations. **IMPORTANT**
                    pour chacune, il faut additionner la contribution
                    de chaque équation à la valeur actuelle (au début
                    du pas de temps) de la variable.

                3.  Les résultats sont placés dans un nouvel état qui
                    sera retourné par la méthode

        2.   

             {#simulationservice}
            ### *SimulationService*:


            1.  Cette classe est un service JavaFX et elle est
                responsable de gérer le temps de la simulation. Elle
                doit appeler la méthode *simuleStep* de la simulation
                sélectionnée en lui envoyant le temps actuel et
                lintervalle de temps. Elle doit également envoyer
                létat actuel :

                1.  Lorsquon redémarre la simulation on doit repartir de
                    létat 0, si on redémarre après une pause il faut
                    repartir du dernier état simulé.

            2.  Le simulateur devrait pouvoir sarrêter par lui-même en
                fonction des états présents (par exemple lorsquun
                objet sort du cadre). Pour cela, il faut surveiller

        3.   

             {#etat}
            ### *Etat*


la variable nommée *stop*. La simulation sarrête lorsque stop est vrai
ou lorsquelle est interrompue par lutilisateur.

1.  Cest principalement une classe de données. Elle contient toutes les
    variables de la simulation pour un pas de temps.

2.  Cette classe possède un constructeur par recopie qui déclenche une
    copie profonde (deep copy) de toutes les variables et équations
    contenues. Le nouvel objet et tous ces constituants sont donc
    complètement distincts de lobjet original.

---
1.  ### *Variables*

    1.  Possède un nom: qui peut être utilisé dans les équations
        MathXParser (pas despace, de caractères spéciaux ou daccents).

    2.  Possède une valeur numérique de type double

    3.  Elle possède aussi un constructeur par recopie

    4.  Elle contient la liste de toutes les équations responsables de
        la modifier.

    5.  *MathXParser* ne reconnaît pas les variables du simulateur cest
        pourquoi on a laissé des méthodes de conversion dans la classe
        *Simulation*.

2.  ### *Equations*

    1.  Possède un nom: qui nest pas utilisé par *MathParser*. Cest pour
        vous aider à lidentifier.

        1.  Possède une expression mathématique de la forme:

            1.  *f(var1,var2,var3,var4)= ...*

            2.  Exemple *f(dt,ay)=ay\*dt*

        2.  Il est essentiel que:

            1.  toutes les variables utilisées soient définies dans la
                simulation;

            2.  toutes les variables utilisées soient dans la
                déclaration de la fonction.

Contre exemple: *f(x,t)=x+t+y* ne fonctionnera pas parce que *y* nest
pas dans *f(x,t)*. Il faudrait écrire ***f(x,y,t)=x+y+t***

1.   ## Démarche proposée

    1.  *Moteur de calcul*

        1.  Commencez par faire ***Simulation***. Modifiez les *Etat*,
            *Variable* et *Equation* au besoin. Validez vos résultats
            avec un jeu déquations simples. Allez-y progressivement:

            1.  Une seule équation *f(x)=x+1*

            2.  Une équation à 2 variables pour x: *f(x,y)=x+y*, et pour
                y: *f(x,y) = x-y*

            3.  Une équation impliquant le pas de temps *f(t,x) = x+t*.

            4.  Assurez-vous que lhistorique possède tous les états et
                quils sont bien indépendants les uns des autres. Vous
                pouvez vérifier en utilisant le débogueur.

        2.  Programmez ensuite *SimulationService* et assurez-vous que
            le temps et le pas de temps sont bien mis à jour. Au début,
            faites simplement laffichage en console. Gérer dabord le
            démarrage et larrêt complet de la simulation.

        3.  Créez un moteur pour enregistrer et charger la simulation
            dans un fichier. Saisir une simulation est assez long, vous
            allez rapidement apprécier de pouvoir les conserver.

        4.  Gérer la pause et la variable stop qui permet dinterrompre
            la simulation en fonction de ce qui sy déroule.

    2.  *UI*

---

1.  Décidez de lorganisation de votre application ( faites les fichiers
    FXML). Restez simple, cest plus facile daugmenter la complexité que
    de la simplifier !

2.  Commencez par faire léditeur déquation, il est essentiel pour
    pouvoir tester le simulateur.

3.  Faites ensuite le simulateur. Vous pourrez alors tester des
    simulations plus complexes ( gravité ou autre)

4.  Faites laffichage dans le *LineChart et du TableView*

5.  *(pour les équipes de 2).* Créez les affichages spécialisés

6.  *(pour les équipes de 2)* Créez lanimation du positionnement des
    fenêtres.

7.  Améliorez lapparence en utilisant des cellules maison dans les
    *ListView*

# Informations utiles

-   ListView

    -   Pour consulter les éléments d'un *ListView*

        -   *List.getItems()*

    -   Pour connaître l'élément sélectionné

        -   *Liste.getSelectionModel().getSelectedItem();*

    -   Pour réagir à un changement de sélection

        -   *Liste.getSelectionModel().getSelectedItem().addListener(callback);*

# Barème D'évaluation :

-   Linterface est propre, bien documentée et **intuitive** (utilisez
    les tooltips au besoin).

-   La simulation personnalisée est originale et suffisamment complexe
    (pour les équipes de 2).

-   La classe *DialoguesUtils* est la seule qui peut utiliser les
    *dialogues JavaFX.*

-   Le code est propre et bien formaté.

-   Toutes les fonctionnalités ont été réalisées.

-   Toutes les exigences ont été rencontrées.

-   Aucune méthode ne dépasse 50 lignes (incluant javadoc et
    commentaires)

-   Il ny a pas de \@ID ou méthode inutile dans le code.

-   Toutes les consignes ont bien été suivies et tous les comportements
    fonctionnent sans problème.

-   Les méthodes que vous avez programmées ou modifiées ont une javadoc
    conforme et des commentaires pertinents.

-   Les fonctions sont bien découpées. Les noms des méthodes sont clairs
    et significatifs. Il n'y a pas de code dupliqué ou redondant.

-   Il n'y a pas de *StackTrace* dans la console *IntelliJ.*

-   Des solutions efficaces ont été utilisées pour résoudre les
    problèmes (ex : classe *Function* sans méthode

*toString*)

>

**À REMETTRE :**

-   Il est à remettre [à la date indiquée sur Léa.]{.underline}

-   Remettez votre projet complet dans une archive .**zip** sur Léa.

-   Votre projet doit comprendre les fichiers des simulations que vous
    avez développées.
