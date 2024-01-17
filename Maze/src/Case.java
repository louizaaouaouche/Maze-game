//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Case permet d'implémenter une case du plateau de jeu (labyrinthe)
// et ainsi gérer tous les traitements qui peuvent se faire dessus
//=================================================================================//

public class Case{
	//Constantes permettant d'initialiser le type de la case courante 
	public static final int IDVIDE=0;
	public static final int IDJOUEUR=2;//Occupée par le joueur
  	public boolean murOuest,murNord,murEst,murSud;
	private int type_case;
	private boolean visited;
	private int x,y;
//========================================================================
//Constructeur 1
//========================================================================
 	public Case(int x, int y, int type_case){
 		this.x = x;
 		this.y = y;
 		this.type_case = type_case;
        this.murSud = true;
        this.murEst = true;
        this.murNord = true;
        this.murOuest = true;
		this.visited=false;
 	}
//========================================================================
//Constructeur 2
//========================================================================
 	public Case(int x, int y){
	
 		this.x = x;
 		this.y = y;
 		this.type_case = this.IDVIDE;
        this.murSud = true;
        this.murEst = true;
        this.murNord = true;
        this.murOuest = true;
		this.visited=false;
 	}
//========================================================================
//Met a jour la case en cours (this) par la case c en parametre
//========================================================================
	public void setCase(Case c){
    //Met a jour la case de la grille en cours (this) par la case c en parametre
    	this.x = c.x;
 		this.y = c.y;
 		this.type_case = c.type_case;
		this.visited=c.visited;
     
	}
//========================================================================
//Retourne le type de la case courante
//========================================================================
	public int getTypeCase(){
	//Renvoie le type de la case
		return this.type_case;
	}
//=======================================================================
//Met a jour le type de la case courante
//=======================================================================
	public void setType(int type){
		this.type_case=type;
	}

//========================================================================
//Le joueur est sur la case courante
//========================================================================
 	public void visite(){
		this.visited=true;
		this.type_case=IDJOUEUR;
 	}
//========================================================================
//Renvoie vrai si le joueur est sur la case en cours, faux sinon
//========================================================================
	public boolean est_joueur(){
		return (this.type_case==IDJOUEUR);
	}
//========================================================================
//Renvoie vrai si la case a été visitée, faux sinon
//========================================================================
	public void aEteVisitee(){
		this.visited=true;
	}
//========================================================================
//Renvoie vrai si la case courante est vide, faux sinon 
//========================================================================
    public boolean estVide(){ 
      
	   return (this.type_case==IDVIDE);
    }

//========================================================================
//Accesseur vers la ligne de la case 
//========================================================================
	public int getX(){
		return this.x;
	}
//========================================================================
//Accesseur vers la colonne de la case 
//========================================================================
	public int getY(){
		return this.y;
	}
//========================================================================
//Renvoie vrai si la case courante est la meme que la Case c 
//========================================================================
public boolean equals(Case c){
	return (this.x==c.x && this.y==c.y);
}
//========================================================================
//Supprime le mur de la case courante selon la direction Dir
//========================================================================
     public void removeMur(int dir){
	//Met la case courante a vide (suppression)
		
		switch(dir){
			case 1:
				this.murNord=false;
				break;
			case 2:
				this.murSud=false;
				break;
			case 4:
				this.murEst=false;
				break;
			case 8:
				this.murOuest=false;
				break;
			
			default:
				break;
		}
     }
//========================================================================
//Renvoie vrai si la case courante a un mur selon la direction Dir 
//========================================================================
	public boolean aUnMur(int dir){
		switch(dir){
			case 1:
				return this.murNord;
			case 2:
				return this.murSud;

			case 8:
				return this.murOuest;
				
			case 4:
				return this.murEst;

			default:
				return true;
				
		}
     
	}
//========================================================================
//Affichage des coordonnées de la case en cours
//========================================================================
public void affiche(){
	System.out.println("Case : ["+this.x+","+this.y+"]\n");
}
//========================================================================
//Dessin de la case en cours sur le terminal
//========================================================================

 public String toString(){
	
		if (this.murSud && this.murEst ){
			return("_|");
		}
	
		if(this.murSud && !this.murEst){
			return("_ ");
		}

		if(this.murEst && !this.murSud){
			return(" |");
		}
		
		return ("  ");
		
	}
 
public String toString2(){
	String s="";
	if (this.murNord ){
		s+="N";
	}

	if(this.murSud){
		s+="S";
	}

	if(this.murEst){
		s+="E";
	}
	if(this.murOuest){
		s+="W";
	}
	
	return s+"-";
	
}
}