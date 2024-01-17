
//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Labyrinthe contient le plateau de jeu de labyrinthe (grille de cases)
// la case de depart et d'arrivée
// Elle genere un labyrinthe et teste s'il est jouable
//=================================================================================//

public class Labyrinthe{
    private Case[][] grille;
    private Joueur joueur;
    private Case caseDepart;
    private Case caseArrivee;

//========================================================================
//Constructeur 1: crée une insatnce labyrinthe 
//@param dimx: dimension en lignes
//@param dimy: dimension en colonnes
//========================================================================
    public Labyrinthe(int dimx,int dimy){

        this.grille=new Case[dimx][dimy];

        //Toutes les cases sont initialement vides (munies de tous leurs murs)
        for(int i=0;i<dimx;i++){
            for(int j=0;j<dimy;j++){
                grille[i][j]=new Case(i,j);
            }
        }

        //CONSTRUCTION DES CASES DEPART ET ARRIVEE:
        //On s'impose comme colonne initiale :0
        int ligne_entree=(int) (Math.random()*dimx);
        this.caseDepart=new Case(ligne_entree,0);
        this.caseDepart.removeMur(8);
        this.joueur=new Joueur(new Position(ligne_entree,0)); //Joueur sur la case de depart
        
        //On s'impose comme colonne d'arrivée :dimy
        int ligne_sortie=(int) (Math.random()*dimy);
        this.caseArrivee=new Case(ligne_sortie,dimy-1);
        this.caseArrivee.removeMur(4);   
    }
//========================================================================
//Constructeur 2: crée une instance de labyrinthe
//@param dimx: dimension en lignes
//@param dimy: dimension en colonnes
//@param j: joueur préalablement construi
//@param caseD: case de départ préalablement construite
//@param caseA: case d'arrivée préalablement construite
//========================================================================
    public Labyrinthe(int dimX, int dimY,Joueur j, Case caseD, Case caseA){

        this.grille=new Case[dimX][dimY];
        this.joueur=j;
        this.caseDepart=caseD;
        this.caseArrivee=caseA;
    }
//========================================================================
//Constructeur 3: crée une instance de labyrinthe
//@param grille: tableau 2D de cases déjà construit
//@param caseD: case de départ préalablement construite
//@param caseA: case d'arrivée préalablement construite
//========================================================================
    public Labyrinthe(Case[][] grille,Case caseD, Case caseA){
        this.grille=grille;
        this.caseDepart=caseD;
        this.caseArrivee=caseA;
        this.joueur.getPos().setPosition(this.caseDepart.getX(),this.caseDepart.getY());
    }
//========================================================================
//@return la case de depart
//========================================================================
    public Case getDepart(){
        return this.caseDepart;
    }
//========================================================================
//@return la case d'arrivée
//========================================================================
    public Case getArrivee(){
        return this.caseArrivee;
    }
//========================================================================
//@return la grille des cases de jeu
//========================================================================
    public Case[][] getGrille(){
        return this.grille;
    }
//========================================================================
//@return le joueur en cours
//========================================================================
  public Joueur getJoueur(){
      return this.joueur;
  }
//========================================================================
//@return la dimension de la grille selon les lignes
//========================================================================
    public int getDimX(){
    
        return grille.length;
    }
//========================================================================
//@return la dimension de la grille selon les colonnes
//========================================================================
    public int getDimY(){
        return grille[0].length;
    }
//========================================================================
//@return le type de case a la position p
//========================================================================
    public int getIdCase(Position p){
    
        Case c=grille [p.getX()][p.getY()];
        return c.getTypeCase();
    }

//========================================================================
//Melange de facon aleatoire les données du tableau tab
//@param tab: tableau 1D d'entiers 
//========================================================================
public void melangerDir(int[] tab){
    int temp,ind;
    int taille=tab.length;
    for (int i=0;i<taille;i++){
        ind=(int)(Math.random()*(taille));
        temp=tab[i];
        tab[i]=tab[ind];
        tab[ind]=temp;
    }
}

//========================================================================
//Genere un labyrinthe selon la méthode de "retour recursif/Back-tracking"
//@param cx: ligne de la case de départ d'où commence la récurisivité
//@param cy: colonne de la case de départ d'où commence la récursivité
//========================================================================

public void genererLabyrinthe(int cx,int cy){
    //Coordonnées de la nouvelle case
	int newX, newY;
	
    Direction d=new Direction();
    int[] directions= {d.Nord(), d.Sud(), d.Est(), d.West()};
    //On melange les directions pour avoir des directions aleatoires pour chaque case
    this.melangerDir(directions);

    //On construit des chemins pour les quatres directions de chaque case (si possible)
    for(int i=0;i<4;i++){
    	//Constructions des coordonnées de la prochaine case selon la direction choisie
        newX=cx+d.DX()[directions[i]];
        newY=cy+d.DY()[directions[i]];
        
        //On vérifie que les nouvelles coordonnées respectent bien les dimension
        if (  (newX>=0) &&  (newX<getDimX()) &&  (newY>=0)  &&  (newY<getDimY())  ){
        	//On ne peut aller vers une case que si elle n'a pas déja été visitée
            if(grille[newX][newY].getTypeCase()==Case.IDVIDE){
                //On modifie le type de la case courante 
                grille[cx][cy].setType(  (  (int)(grille[cx][cy].getTypeCase() | directions[i])  ) );
                //On supprime le mur entre la case courante et la nouvelle case (on se crée notre chemin)
                grille[cx][cy].removeMur(directions[i]);
                //On modifie le type de la nouvelle case
                grille[newX][newY].setType(  (   (int)(grille[newX][newY].getTypeCase() | d.OPPOSITE()[directions[i]])  ) ) ;
                //On supprime le mur opposé (car c'est un mur commun) pour la nouvelle case
                grille[newX][newY].removeMur(d.OPPOSITE()[directions[i]]);
                //On réitère l'algorithme pour la nouvelle case
                genererLabyrinthe(newX, newY);           
            }
        }

    }
}
//========================================================================
//@return vrai si le labyrinthe généré est jouable
//@param cx : coordonnée en ligne de la case de départ
//@param cy: coordonnée en colonne de la case de départ
//@param opt:
//========================================================================
public boolean estJouable(int cx,int cy,int opt){
    int newX, newY;
    Direction d=new Direction();
    int[] directions= {d.Nord(), d.Sud(), d.Est(), d.West()};

    for(int i=0;i<4;i++){

        if(directions[i]!=opt){
        	//Vérifie si dans la direction choisie on tombe sur un mur 
            if(!grille[cx][cy].aUnMur(directions[i])){
            	//On va vers la prochaine case selon la direction choisie
                newX=cx+d.DX()[directions[i]];
                newY=cy+d.DY()[directions[i]];
                
                if (  (newX>=0) &&  (newX<getDimX()) &&  (newY>=0)  &&  (newY<getDimY())  ){
                    if (grille[newX][newY].equals(caseArrivee)){
                        //Si on est sur la case d'arrivée donc il existe un chemin jouable
                        return true;
                    }else{
                        
                        return estJouable(newX, newY,d.OPPOSITE()[directions[i]]); 
                    }
                }   
                
            }
        }
        
    }
    return false;
}


//========================================================================
//Permet de construire un labyrinthe jouable
//========================================================================
public void construction(){
    
		
    this.genererLabyrinthe(this.getDepart().getX(),this.getDepart().getY());
    
    //Tant que notre labyrinthe n'est pas jouable on en construit un nouveau
    while(!this.estJouable(this.getDepart().getX(),this.getDepart().getY(),-1)){
        this.grille=new Case[getDimX()][getDimY()];
        for(int i=0;i<getDimX();i++){
            for(int j=0;j<getDimY();j++){
                grille[i][j]=new Case(i,j);
            }
        }
       
        this.genererLabyrinthe(this.getDepart().getX(),this.getDepart().getY());
    }
}

//========================================================================
//@return une chaine de caracteres dessinant la grille de jeu
//========================================================================
    public String toString(){
         String s="";
         for(int i=0;i<this.getDimX();i++){
            for(int j=0;j<this.getDimY();j++){
                s+=grille[i][j].toString();
            }
            s+="\n";
        }
        return s;
    }

    
    public String toString2(){
        String s="";
        for(int i=0;i<this.getDimX();i++){
           for(int j=0;j<this.getDimY();j++){
               s+=grille[i][j].toString2();
           }
           s+="\n";
       }
       return s;
   }



//========================================================================
//@return vrai si le joueur bouge selon la direction d
//@param dir: entier correspondant à la direction vers laquelle on bouge
//========================================================================

	public boolean bougeVers(int dir){
        Direction d=new Direction();
        int newI=this.joueur.getIJoueur()+d.DX()[dir]; //Nouvelle ligne selon la direction dir
        int newJ=this.joueur.getJJoueur()+d.DY()[dir]; //Nouvelle colonne selon la direction dir

       
        Case caseJ=grille[this.joueur.getIJoueur()][this.joueur.getJJoueur()];// Case courante où se situe le joueur
        
        if(!caseJ.aUnMur(dir)){//La case courante n'a pas de mur selon la direction dir donc elle peut bouger

            if(newI>=0  && newI< getDimX()  && newJ>=0 && newJ<getDimY()){//on vérifie que les nouvelles coordonnées ne depassent pas la grille
                caseJ.aEteVisitee();
                this.joueur.set(newI,newJ);
                caseJ=grille[this.joueur.getIJoueur()][this.joueur.getJJoueur()];
                caseJ.setType(Case.IDJOUEUR);
                return true;
             }else{
                return false;
            }
         }else{
            return false;
        }
         
    }

	
//========================================================================
//Permet de jouer un coup vers la direction d 
//@return vrai si on peut jouer vers la direction dir
//@param dir: direction vers laquelle on veut jouer
//========================================================================

    public boolean play(int dir){
    
        return this.bougeVers(dir);
    }

//========================================================================
//@return vrai si le joueuer a gagné (s'il a atteint la case d'arrivée)
//========================================================================
    public boolean victoire(){
    
       return ((this.joueur.getPos().getX()==this.caseArrivee.getX())&&(this.joueur.getPos().getY()==this.caseArrivee.getY()));
    }


}