DOMAINS
estado=estado(puerta,piso).
comoMoverme= estado*
puerta=symbol.
piso,pisofinal=integer
listapisos= integer*

PREDICATES
nondeterm viajar(piso,listapisos,comoMoverme,comoMoverme).
nondeterm mover(integer,integer,comoMoverme,comoMoverme).
piso(integer).
mostrarEnOrden(comoMoverme).
nondeterm avanzo(piso,listapisos,comoMoverme,comoMoverme).

CLAUSES
/*piso*/
piso(X):-X>-3,X<11.
/*Viajar*/
viajar(_,[],A,B):- B=A.
viajar(X,[Ca|Co],A,B):- piso(X), piso(Ca),mover(X,Ca,A,C), viajar(Ca,Co,C,B).
/*Mover*/
mover(E,E,C,B):- B=[estado("cerrar puerta en",E),estado("abrir puerta en",E)|C].
mover(E,G,C,B):- G>E, H=E+1, D=[estado("subir al piso",H)|C], mover(H,G,D,B).
mover(E,G,C,B):- G<E, H=E-1, D=[estado("bajar al piso",H)|C], mover(H,G,D,B).
/*mostrarEnOrden*/
mostrarEnOrden([]):-!.
mostrarEnOrden([estado(A,B)|Co]):- mostrarEnOrden(Co),write(A),write(" : "),write(B),write("\n").
/*avanzo*/
avanzo(A,B,C,D):- viajar(A,B,C,D),mostrarEnOrden(D).

GOAL
%Caso 1
avanzo(0,[1,-2,2,4,8,10,-1],[],R).
%Caso 2
%avanzo(0,[0],[],S).
%Caso 3
%avanzo(-3,[0],[],T).
%Caso 4
%avanzo(6,[11],[],U).