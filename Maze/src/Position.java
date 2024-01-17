//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Position permet d'impl�menter une position sur le plateau de jeu
//=================================================================================//

public class Position{
	//Coordonn�es de la case o� se trouve l'element
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
//Constructeur 2: initilise la position avec les coordonn�es du parametre
//@param Position d�j� cr�e
//========================================================================
	public Position(Position p){
		this(p.x,p.y);
	}
//========================================================================
//Modifie les coordon�es de la position courante
//@param x nouvelle coordonn�e en ligne
//@param y nouvelle coordonn�e en colonne
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
//somme de deux positions (somme des coordonn�es)
//@param nx: valeur � ajouter � la coordonn�e en ligne 
//@param ny: valeur � ajouter � la coordonn�e en ligne 
//@return la position dont les coordonn�es sont la somme des coordon�es
//de la position courante et ceux en parametre
//========================================================================
	public Position add(int nx,int ny){
		return new Position(this.x+nx,this.y+ny);
	}
//========================================================================
//difference de deux positions (soustraction des coordonn�es)
//@param nx: valeur � soustraire � la coordonn�e en ligne 
//@param ny: valeur � soustraire � la coordonn�e en ligne 
//@return la position dont les coordonn�es sont la difference des coordon�es
//de la position courante et ceux en parametre
//========================================================================	
	public Position sub(int nx,int ny){

		return new Position(this.x-nx,this.y-ny);
	}
//========================================================================
//@return la chaine associ�e aux coordonn�es de la position courante 
// sous forme [x,y]
//========================================================================
	@Override
	public String toString(){
		return "["+this.x+","+this.y+"]";
	}
//========================================================================
//@return vrai si  l'objet o est �gal � la position courante 
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