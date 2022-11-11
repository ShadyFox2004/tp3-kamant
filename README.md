# tp3-kamant

Un projet qui ne vas pas se terminer a la derniere minute.

 ***Développement de programmes dans un*** & Simulateur linéaire
interactif\
***environnement graphique*** & JavaFX\
Automne 2022 & Service JavaFX\
Cégep Limoilou & Conception dapplication\
Département dinformatique & (20 %)\
()


**CONTEXTE DE RÉALISATION DE L'ACTIVITÉ :**

-   Durée : 5 semaines (jusquà la fin de la semaine 14)

-   Ce travail peut être réalisé en équipe de 2 maximum.

-   Suivre les consignes additionnelles sur le canal *Questions
    générales* de l'équipe Teams du cours.

**OBJECTIFS**

-   Concevoir une application JavaFX interactive complète

-   Programmer les algorithmes requis en utilisant les structures de
    données appropriées

-   Gérer le projet et les ressources convenablement

**Remise**

-   **Changez lartifactId et le nom du projet pour remplace votre-nom
    par vos véritables noms (sans accents sans espace)**

-   **Remettre une archive en format zip contenant le projet complet.**

# Éléments requis:

1.  Pour ce TP on ne vous impose pas linterface GUI. Vous être libres de
    la produire comme vous le désirez, mais en respectant les exigences
    minimales demandées.

2.  Le UI doit comprendre

    1.  Un éditeur de simulation

        1.  Léditeur de simulation doit comprendre au moins 3
            *ListView*:

            1.  la liste de toutes les simulations connues. Cette liste
                affiche:

                1.  Le nom de la simulation;

                2.  Le nombre de pas simulés.

            2.  la liste de toutes les variables de la simulation qui
                est sélectionnée;

                1.  le nom de la variable (celui qui est utilisé dans
                    les équations);

                2.  la valeur de la variable;

                3.  Toutes les simulations doivent avoir au minimum les
                    variables suivantes:

                    1.  ***t*** : représentant le temps depuis le début
                        de la simulation.

                    2.  ***dt*** : linterval de temps en cours

                    3.  ***stop*** : une variable permettant de stopper
                        la simulation en fonction dune équation
                        logique;

            3.  la liste de toutes les équations qui modifient la
                variable sélectionnée de la simulation sélectionnée:

                1.  Le nom de léquation;

                2.  Lexpression mathématique de *MathXParser*. Voir
                    *Equation* plus loin.

        2.  Léditeur de simulation doit offrir un moyen (bouton, menu
            contextuel...) pour que lutilisateur puisse:

            1.  **Ajouter / modifier / effacer -\>** une simulation

            2.  Ajouter / modifier / effacer / changer la valeur / dupliquer -\une variable

            3.  **Ajouter / modifier / effacer** -\une équation

        3.  Lorsque lutilisateur sélectionne une simulation dans la
            première liste, la seconde liste doit afficher ses
            variables. Lorsque lutilisateur sélectionne une variable
            dans la seconde liste, la troisième liste devrait afficher
            les équations qui lui sont associées. Lutilisateur ne doit
            pas pouvoir ajouter une équation si une variable nest pas
            sélectionnée et pareillement pour les variables et les
            listes. Évidemment, en plus de mettre les différents objets
            correctement dans les listes, il faut les imbriquer
            correctement.

            1.  Simulation possède un historique qui est une liste de
                tous les états successifs qui ont été calculés. Un
                état est lensemble des variables dune simulation.
                Létat 0 est important, car il constitue les valeurs
                initiales desquelles repart toujours la simulation.

        4.  Léditeur de simulation doit également proposer une
            fonctionnalité pour **lire** et écrire les simulations
            complètes sur le disque (avec les variables et les
            équations).

            1.  Vous pouvez le faire avec des boutons ou des menus
                contextuels. Les fichiers peuvent être écrits en
                utilisant la sérialisation objet, mais attention les
                fichiers deviendront illisibles au fur et à mesure que
                vous modifierez le code.

            2.  Lutilisateur doit pouvoir choisir le nom et lemplacement
                du fichier lui-même. Utilisez un *FileChooser*
                (*showOpenDialog* et *showCloseDialog*). Les fichiers
                auront lextension \".***sim***\" ( exemple
                gravite**.sim**).

    2.  Un simulateur

        1.  Le simulateur doit pouvoir connaître léquation qui est
            sélectionnée dans léditeur déquation. Cest cette dernière
            qui sera simulée.

        2.  Le simulateur doit permettre de:

            1.  **modifier** la longueur des pas de temps (période)

            2.  **démarrer** la simulation;

            3.  **arrêter** la simulation;

            4.  mettre la simulation en **pause**. Cette fonctionnalité
                est plus complexe, car il faut retirer le temps de
                pause du temps de la simulation. Vous devriez la faire
                à la fin.

        3.  Le simulateur affiche un label qui indique le temps où est
            rendue la simulation.

        4.  Le simulateur présente également une liste contenant toutes
            les variables de la simulation sélectionnée. Vous pouvez la
            mettre à jour avec un bouton, mais il est préférable quelle
            se mette à jour automatiquement avec un écouteur sur la
            liste de léditeur de simulations.

            1.  Lutilisateur doit pouvoir choisir une variable et
                pendant la simulation cette variable sera mise à jour
                en continu dans un *LineChart*.

            2.  Comme toutes les simulations sont temporelles, le
                *LineChart* présente toujours le temps en abscisse et
                la valeur de la donnée choisie en ordonnée.

            3.  Pour un extra vous pouvez permettre dafficher plusieurs
                variables sélectionnées en même temps, mais cest
                significativement plus difficile. Nessayez de la faire
                quà la toute fin.

    3.  Un calculateur

        1.  Vous pouvez réutiliser le calculateur qui a été développé
            dans le TP2. On va le modifier légèrement:

            1.  Au lieu davoir une liste de fonction personnalisée, on
                utilisera les équations du simulateur. Les équations

            2.  Il faut ajouter un moyen (bouton, menu contextuel...)
                pour permettre à lutilisateur de transférer la
                dernière valeur calculée vers la variable qui est
                sélectionnée dans la liste de léditeur de simulation.

    4.  (pour les équipes de 2 seulement) Une animation qui
        redimensionne et déplace chaque fenêtre à une position
        prédéterminée sur lécran de sorte que lutilisateur puisse bien
        voir lensemble de

toute linterface. La fonctionnalité est déclenchée par un menu ou un
bouton qui doit être évident et bien placé.

1.  Exigences minimales

    1.  Personne seule:

        1.  Au moins 2 fenêtres (en plus du à propos et les dialogues).
            Les fenêtres doivent contenir:

            1.  Simulateur, calculateur et Éditeur de simulation

        2.  Lanimation de repositionnement des fenêtres

        3.  1 scénario de simulation

    2.  En plus pour les équipes de 2:

        1.  Au moins 3 fenêtres (en plus du à propos et des dialogues).

        2.  Lanimation de repositionnement des fenêtres

        3.  Un afficheur spécialisé

        4.  2 scénarios de simulations dans 2 fichiers

# Fonctionnalités requises {#fonctionnalituxe9s-requises}

1.  Sauvegarde des fichiers de simulation.

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
