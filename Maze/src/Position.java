//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Position permet d'implémenter une position sur le plateau de jeu
//=================================================================================//

public class Position{
	//Coordonnées de la case où se trouve l'element
	private int x;
	private int y;
	
//========================================================================
//Constructeur 1
//========================================================================
	public Position(int x,int y){
		this.x=x;
		this.y=y;
	}
//========================================================================
//Constructeur 2: initilise la position avec les coordonnées du parametre
//@param Position déjà crée
//========================================================================
	public Position(Position p){
		this(p.x,p.y);
	}
//========================================================================
//Modifie les coordonées de la position courante
//@param x nouvelle coordonnée en ligne
//@param y nouvelle coordonnée en colonne
//========================================================================
	public void setPosition(int x,int y){
		this.x=x;
		this.y=y;
	}

//========================================================================
//@return la valeur de la ligne x
//========================================================================
	public int getX(){
		return this.x;
	}
//========================================================================
//@return la valeur de la ligne y
//========================================================================
	public int getY(){
		return this.y;
	}

//========================================================================
//somme de deux positions (somme des coordonnées)
//@param nx: valeur à ajouter à la coordonnée en ligne 
//@param ny: valeur à ajouter à la coordonnée en ligne 
//@return la position dont les coordonnées sont la somme des coordonées
//de la position courante et ceux en parametre
//========================================================================
	public Position add(int nx,int ny){
		return new Position(this.x+nx,this.y+ny);
	}
//========================================================================
//difference de deux positions (soustraction des coordonnées)
//@param nx: valeur à soustraire à la coordonnée en ligne 
//@param ny: valeur à soustraire à la coordonnée en ligne 
//@return la position dont les coordonnées sont la difference des coordonées
//de la position courante et ceux en parametre
//========================================================================	
	public Position sub(int nx,int ny){

		return new Position(this.x-nx,this.y-ny);
	}
//========================================================================
//@return la chaine associée aux coordonnées de la position courante 
// sous forme [x,y]
//========================================================================
	@Override
	public String toString(){
		return "["+this.x+","+this.y+"]";
	}
//========================================================================
//@return vrai si  l'objet o est égal à la position courante 
//========================================================================
	@Override
	public boolean equals (Object o){
		if (this==o)return true;
		if (o==null)return false;
		if (getClass()!=o.getClass())return false;
		Position other=(Position)o;
		if (this.x!=other.x)return false;
		if (this.y!=other.y)return false;
		return true;
	}
}