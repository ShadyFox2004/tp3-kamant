1.  *Moteur de calcul*
2.  Commencez par faire [[Simulation]]. Modifiez les [[State]], [[Variables]] et [[Equation]] au besoin. Validez vos résultats avec un jeu déquations simples. Allez-y progressivement:
3.  Une seule équation $f(x)=x+1$
4.  Une équation à 2 variables pour $x: f(x,y)=x+y$, et pour $y: f(x,y) = x-y$
5.  Une équation impliquant le pas de temps $f(t,x) = x+t$.
6.  Assurez-vous que lhistorique possède tous les états et quils sont bien indépendants les uns des autres. Vous pouvez vérifier en utilisant le débogueur.
7.  Programmez ensuite [[SimulationService]] et assurez-vous que le temps et le pas de temps sont bien mis à jour. Au début, faites simplement l'affichage en console. Gérer dabord le démarrage et larrêt complet de la simulation.
8.  Créez un [[Motor|moteur]] pour enregistrer et charger la simulation dans un fichier. Saisir une simulation est assez long, vous allez rapidement apprécier de pouvoir les conserver.
9.  Gérer la pause et la variable stop qui permet dinterrompre la simulation en fonction de ce qui sy déroule.