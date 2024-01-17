//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Menu2 permet de générer le menu des niveaux
//=================================================================================//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Menu2 extends JFrame implements ActionListener{
	
	private int hauteurMenu=600;//en px
	private int largeurMenu=600;//en px
	
	private JButton facile= new JButton();
	private JButton normal= new JButton ();
	private JButton difficile= new JButton();

	private int play=0;
//========================================================================
//Constructeur: crée un menu de niveaux 
//========================================================================

	public Menu2(){
		super();
		setTitle("MAZE WORLD"); //On donne un titre à l'application
		setSize(largeurMenu,hauteurMenu); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On ne permet pas le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix	
		add(buildContentPane());
		
		
	}
//========================================================================
//@return la valeur booleenne de play
//========================================================================

	public int getPlay() {
		return play;
	}
//========================================================================
//Met play a faux
//========================================================================
	public void setPlay() {
		play=0;
	}
	
//========================================================================
//Construit le panel qui contient le menu des niveaux
//@return un panel contenant le menu des niveaux
//========================================================================

	private JPanel buildContentPane(){
		
		JPanel panel = new JPanel();
		
        //Rendre les boutons cliquables
		facile.addActionListener(this);
		normal.addActionListener(this);
		difficile.addActionListener(this);
		
		//Initialisation des labels à mettre dans les boutons
		JLabel l1=new JLabel("     FACILE",JLabel.CENTER);
		JLabel l2=new JLabel("    NORMAL",JLabel.CENTER);
		JLabel l3=new JLabel("   DIFFICILE",JLabel.CENTER);
		
		l1.setFont(new Font("Verdana", Font.BOLD, 15));
		l1.setForeground(Color.DARK_GRAY);
		
		l2.setFont(new Font("Verdana", Font.BOLD, 15));
		l2.setForeground(Color.DARK_GRAY);
		
		l3.setFont(new Font("Verdana", Font.BOLD, 15));
		l3.setForeground(Color.DARK_GRAY);
		
		//Positions des boutons
		facile.add(BorderLayout.CENTER,l1);
		normal.add(BorderLayout.CENTER,l2);
		difficile.add(BorderLayout.CENTER,l3);
		
		//Couleur de fond des boutons
		facile.setBackground(Color.WHITE);
		normal.setBackground(Color.WHITE); 
		difficile.setBackground(Color.WHITE);
		
		//Taille des boutons
		facile.setPreferredSize(new Dimension(150,50));
		normal.setPreferredSize(new Dimension(150,50));
		difficile.setPreferredSize(new Dimension(150,50));
		
		//Mode d'affichage
        panel.setLayout(new GridBagLayout());
      

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
       
       
        JLabel titre=new JLabel("NIVEAU");
        titre.setFont(new Font("Courier", Font.BOLD, 50));
		titre.setForeground(Color.DARK_GRAY);
    		
        JPanel buttons = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        buttons.add(facile, gbc);
        buttons.add(normal, gbc);
        buttons.add(difficile, gbc);
        
       
        panel.add(titre, gbc);
        panel.add(buttons, gbc);
        return panel;
	}
	
//========================================================================
//Permet de gérer les choix des bouttons sur le menu des niveaux
//========================================================================

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		play=0;
		if(source == facile){
			play= 1;
		} else {
			if(source == normal){
				play=2;	
			}else {
				play=3;
				
			}
			
		}
	}
}

