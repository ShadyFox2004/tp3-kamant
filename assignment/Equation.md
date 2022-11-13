#class
1.  Possède un nom: qui nest pas utilisé par *MathParser*. Cest pour vous aider à lidentifier. 
2.  Possède une expression mathématique de la forme:
	- $f(var1,var2,var3,var4)= ...$
	- Exemple $f(dt,ay)= ay/dt$
3.  Il est essentiel que:
	1.  toutes les variables utilisées soient définies dans la simulation;
	2.  toutes les variables utilisées soient dans la déclaration de la fonction.
	Contre exemple: $f(x,t)=x+t+y$ ne fonctionnera pas parce que *y* nestpas dans $f(x,t)$. Il faudrait écrire $f(x,y,t)=x+y+t$