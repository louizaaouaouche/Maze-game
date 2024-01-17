//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Joueur permet d'implémenter notre objet qui va se deplacer sur le 
// plateau de jeu (labyrinthe)
//=================================================================================//

public class Joueur{
	private Position position;
	
//========================================================================
//Constructeur 1
//========================================================================
	public Joueur(Position position){
	
		this.position = position;
	}
//========================================================================
//Modifie la position du joueur
//========================================================================
	public void set(Position p){
	
		this.position=p;
	}
//========================================================================
//Modifie la position du joueur
//========================================================================
	public void set(int x,int y){
	
		this.position.setPosition(x,y);
	}

//========================================================================
//@return la position du joueur
//========================================================================
	public Position getPos(){
		return this.position;
	}
//========================================================================
//@return la ligne où se trouve le joueur
//========================================================================
	public int getIJoueur(){
		return this.position.getX();
	}
//========================================================================
//@return la colonne où se trouve le joueur
//========================================================================
	public int getJJoueur(){
		return this.position.getY();
	}

}