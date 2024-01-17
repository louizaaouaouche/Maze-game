//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe MazeMain permet de tester notre jeu de labyrinthe
//=================================================================================//

import java.awt.image.*;
import javax.swing.*;

public class MazeMain{

	private static Menu menu=new Menu();
	private static SimpleInterface ui = new SimpleInterface("Labyrinthe",4000,4000);
	private static BufferedImage[] sprites = SimpleInterface.getSprites("labsprites.png", 64,64);
	private static BufferedImage[] particuliers = SimpleInterface.getSprites("labsprites2.png", 60,65);
	private static BufferedImage[] lignedepart = SimpleInterface.getSprites("lignerouge.png", 64,64);
	private static BufferedImage[] lignearrivee = SimpleInterface.getSprites("ligneverte.png", 64,64);
	
	
	private static final int sec1=10;
	private static final int sec2=20;
	private static final int sec3=30;
	private static Minuteur chrono=new Minuteur();
	
	public static void main(String[] args){
		int cols=9;
		int rows=9;
		
		Menu2 menuNiv=new Menu2();
		
		//Construction du labyrinthe
		Labyrinthe lab=new Labyrinthe(rows,cols);
	    lab.construction();
	    
    	menu.setVisible(true);
    	
    	//Pour permettre un retour au menu principal après une victoire ou une perte
 	   	while (menu.isActive()) {
 	   		chrono=new Minuteur();
 	   		//Tant qu'on ne choisit pas le bouton Play on attend
			while(!menu.getPlay()) {
			 			
			 	System.out.print("");
			 			
			}
			
			//Affichage du deuxieme menu: les niveaux
			menu.setPlay();
			menuNiv.setVisible(true);
			
			int res=menuNiv.getPlay();
			while(res==0) {
	 			res=menuNiv.getPlay();
			 	System.out.print("");
			 			
			}
			
			menuNiv.setPlay();
			menuNiv.dispose();
			//Niveau facile
			if(res==1) {
				lab=new Labyrinthe(rows,cols);
				lab.construction();
				chrono.start();
				playUI(lab,70,70,sec1);
				
				
			}
			//Niveau normal
			if (res==2) {
				lab=new Labyrinthe(2*rows,2*cols);
				lab.construction();
				chrono.start();
				playUI(lab,35,35,sec2);
			}
			//Niveau difficile
			if (res==3) {
				lab=new Labyrinthe(3*rows,3*cols);
				lab.construction();
				chrono.start();
				playUI(lab,25,25,sec3);
			}
			
			
 	   	}
 		
	    
		
	}
//========================================================================
//Permet de jouer sur notre fenetre de jeu et affiche le resultat
//pour chaque mouvement
//@param lab :labyrinthe sur lequel on joue
//@param pixx: pixels pour chaque case en lignes
//@param pixy: pixels pour chaque case en colonnes
//========================================================================

	public static void playUI(Labyrinthe lab,int pixx,int pixy,int sec){
		//On initialise notre fenetre ui
		ui.setVisible(true);
		ui.setGrid(lab.getDimX(),lab.getDimY());
		ui.setSize(pixx*lab.getDimX(), pixy*lab.getDimY());
		ui.setLocationRelativeTo(null);
		show(lab,sec);
		ui.refresh();
		
		Direction d=new Direction();
		//On attend la saisie d'une direction
		int c=	ui.waitKey();
		int cpt=chrono.getSec();
		while(!lab.victoire() && (cpt<sec)){
			cpt=chrono.getSec();
			if (c!=-1)
				switch (c) {
				//Code ascii : "k"
				case 107:
					lab.play(d.West());
					break;
				//Code ascii : "o"
				case 111:
					lab.play(d.Nord());
					break;
				//Code ascii : "m"
				case 109:
					lab.play(d.Est());
					break;
				//Code ascii : "l"
				case 108:
					lab.play(d.Sud());
					break;
				default:
					break;
				}
			
			c = ui.popKey();
			show(lab,sec);
			SimpleInterface.pause(10);
		}
		//Message de victoire
		ui.dispose();
		if (cpt>=sec) {
			echecMessage();
		}else {
			vicMessage();
		}
		
		
		
		
		
	}
//========================================================================
//Crée une fenetre pour afficher un message de victoire au joueur
//========================================================================
	
	public static void vicMessage() {
		SimpleInterface vic=new SimpleInterface("MAZE WORLD - VICTOIRE",600,450);
		vic.setLocationRelativeTo(null);
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\louiz\\eclipse-workspace\\Maze\\src\\victoire.jpg"));	
		vic.setContentPane(label);
		vic.setFocusable(true);
		vic.setVisible(true);
		vic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
//========================================================================
//Crée une fenetre pour afficher un message d'echec au joueur
//========================================================================

	public static void echecMessage() {
		SimpleInterface echec=new SimpleInterface("MAZE WORLD - ECHEC",600,450);
		echec.setLocationRelativeTo(null);
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\louiz\\eclipse-workspace\\Maze\\src\\echec.jpg"));	
		echec.setContentPane(label);
		echec.setFocusable(true);
		echec.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		echec.setVisible(true);
	}

	
//========================================================================
//Affiche le labyrinthe sur la fenetre de jeu
//@param lab :labyrinthe sur lequel on joue
//========================================================================
	public static void show(Labyrinthe lab,int sec){
		
		ui.setTitle("LABYRINTHE       "+"                                       "
				+ "   Temps restant:  "+ (sec- chrono.getSec())+" s");
		for (int i=0;i<lab.getDimX();i++) {
			for (int j=0;j<lab.getDimY();j++){
				ui.drawCell(j, i, sprites[0]);
							
				Case cas=lab.getGrille()[i][j];

				if (cas.murNord) { //N
					ui.drawCell(j,i,sprites[2]);
					if (cas.murSud ) {// N S
						ui.drawCell(j,i,sprites[5]);
						if (cas.murEst){ // N S E
							ui.drawCell(j,i,sprites[14]);
							if(cas.murOuest) {// N S E W
								ui.drawCell(j,i,sprites[15]);
							}
						}else {
							if(cas.murOuest) {// N S W
								ui.drawCell(j,i,sprites[13]);
							}
						}
					}else {
						if (cas.murEst) {// N E
							ui.drawCell(j,i,sprites[6]);
							if(cas.murOuest) {// N E W
								ui.drawCell(j,i,sprites[9]);
							}
						}else {
							if(cas.murOuest) {// N W
								ui.drawCell(j,i,sprites[7]);
							}
						}
					}
				}else {
					if (cas.murSud) { //S
						
						ui.drawCell(j,i,sprites[1]);
							if (cas.murEst){ //S E
								ui.drawCell(j,i,sprites[10]);
								if(cas.murOuest) {//S E W
									ui.drawCell(j,i,sprites[8]);
								}
							}else {
								if(cas.murOuest) {//S W
									ui.drawCell(j,i,sprites[11]);
								}
							}
						
					}else {
						if (cas.murEst) { //E
							ui.drawCell(j,i,sprites[3]);
							if(cas.murOuest) {//E W
								ui.drawCell(j,i,sprites[12]);
							}
						}else {
							if(cas.murOuest) {//W
								ui.drawCell(j,i,sprites[4]);
							}
						}
					}
				}
				
			}
		}
		ui.drawCell(lab.getDepart().getY(),lab.getDepart().getX(),lignedepart[0]);
		ui.drawCell(lab.getArrivee().getY(),lab.getArrivee().getX(),lignearrivee[0]);
		ui.drawCell(lab.getJoueur().getJJoueur(),lab.getJoueur().getIJoueur(),particuliers[0]);
		ui.refresh();
	}
	


	
}