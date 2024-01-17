//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Menu permet de générer le menu princpal
//=================================================================================//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame implements ActionListener{
	private int hauteurMenu=600;//en px
	private int largeurMenu=600;//en px
	
	private JButton playButton= new JButton(); //bouton pour jouer
	private JButton exitButton= new JButton ();//bouton pour quitter le jeu
	private JButton helpButton= new JButton(); //bouton pour afficher la page d'aide

	private boolean play=false;
//========================================================================
//Constructeur: crée un menu
//========================================================================

	public Menu(){
		super();
		setTitle("MAZE WORLD"); //On donne un titre à l'application
		setSize(largeurMenu,hauteurMenu); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On ne permet pas le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix	
		add(buildContentPane());//ajouter le menu au panel principal
		
		
	}
	
//========================================================================
//@return la valeur booleenne de play
//========================================================================

	public boolean getPlay() {
		return play;
	}
	
//========================================================================
//Met play a faux
//========================================================================
	public void setPlay() {
		play=false;
	}
//========================================================================
//Construit le panel qui contient le menu
//@return un panel contenant le menu principal 
//========================================================================

	private JPanel buildContentPane(){
		
		JPanel panel = new JPanel();
        
        //Rendre les boutons cliquables
		playButton.addActionListener(this);
		exitButton.addActionListener(this);
		helpButton.addActionListener(this);
			
		//Initialisation des labels à mettre dans les boutons
		JLabel l1=new JLabel("     P L A Y",JLabel.CENTER);
		JLabel l2=new JLabel("     E X I T",JLabel.CENTER);
		JLabel l3=new JLabel("     H E L P",JLabel.CENTER);
		
		l1.setFont(new Font("Verdana", Font.BOLD, 15));
		l1.setForeground(Color.DARK_GRAY);
		
		l2.setFont(new Font("Verdana", Font.BOLD, 15));
		l2.setForeground(Color.DARK_GRAY);
		
		l3.setFont(new Font("Verdana", Font.BOLD, 15));
		l3.setForeground(Color.DARK_GRAY);
		
		//Positions des boutons
		playButton.add(BorderLayout.CENTER,l1);
		exitButton.add(BorderLayout.CENTER,l2);
		helpButton.add(BorderLayout.CENTER,l3);
		
		//Couleur de fond des boutons
		playButton.setBackground(Color.LIGHT_GRAY);
		exitButton.setBackground(Color.LIGHT_GRAY); 
		helpButton.setBackground(Color.LIGHT_GRAY);
		
		//Taille des boutons
		playButton.setPreferredSize(new Dimension(150,50));
		exitButton.setPreferredSize(new Dimension(150,50));
		helpButton.setPreferredSize(new Dimension(150,50));
		
		//Couleur de fond
		panel.setBackground(Color.DARK_GRAY);
		
		//Mode d'affichage
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
       
        JLabel titre=new JLabel("MAZE WORLD");
        titre.setFont(new Font("Courier", Font.BOLD, 50));
		titre.setForeground(Color.WHITE);
		
		
        JPanel buttons = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        buttons.add(playButton, gbc);
        buttons.add(exitButton, gbc);
        buttons.add(helpButton, gbc);
        
        panel.add(titre, gbc);
        panel.add(buttons, gbc);
        return panel;
	}
//========================================================================
//Crée une fenetre qui explique comment jouer
//========================================================================

	public static void Help() {
		SimpleInterface uhelp=new SimpleInterface("MAZE WORLD - HELP",820,580);
		uhelp.setLocationRelativeTo(null);
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\louiz\\eclipse-workspace\\Maze\\src\\help.jpg"));	
		uhelp.setContentPane(label);
		uhelp.setFocusable(true);
		uhelp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		uhelp.setVisible(true);
	}
//========================================================================
//Permet de gérer les choix des bouttons sur le menu 
//========================================================================

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == playButton){
			play= true;
		} else {
			if(source == exitButton){
				System.exit(0);	
			}else {
				this.setVisible(true);
				Help();
				
			}
			
		}
	}
}

