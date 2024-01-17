//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Direction permet d'implémenter une direction necessaire pour generer
// automatiquement le labyrinthe ainsi que pour le mouvement du joueur
//=================================================================================//
 class Direction{
	private final int N=1;
	private final int S=2;
	private final int E=4;
	private final int W=8;
	private final int[] dx=new int[9];
	private final int[] dy=new int[9];
	private final int[] opposite=new int[9];

//========================================================================
//Constructeur 1
//========================================================================
	public Direction(){
		opposite[N]=S;
		opposite[S]=N;
		opposite[W]=E;
		opposite[E]=W;

		dy[N] = 0;
		dy[E] = 1;
		dy[S] = 0;
		dy[W] = -1;

		dx[N] = -1;
		dx[E] = 0;
		dx[S] = 1;
		dx[W] = 0;
	}
//========================================================================
//Renvoie l'entier relatif au nord
//========================================================================
	public int Nord(){
		return N;
	}
//========================================================================
//Renvoie l'entier relatif au sud
//========================================================================
	public int Sud(){
		return S;
	}
//========================================================================
//Renvoie l'entier relatif à l'est
//========================================================================
	public int Est(){
		return E;
	}
//========================================================================
//Renvoie l'entier relatif à l'ouest
//========================================================================
	public int West(){
		return W;
	}
//========================================================================
//Renvoie le tableau des pas de deplacements pour chaque direction
//selon les lignes
//========================================================================
	public int[] DX(){
		return this.dx;
	}
//========================================================================
//Renvoie le tableau des pas de deplacements pour chaque direction
//selon les colonnes
//========================================================================
	public int[] DY(){
		return this.dy;
	}
//========================================================================
//Renvoie le tableau des directions opposées au directions basiques
// S=!=N, E=!=W
//========================================================================
	public int[] OPPOSITE(){
		return this.opposite;
	}
}