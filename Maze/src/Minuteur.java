//=================================================================================//
// PROJET LU2IN002
// @author: Louiza AOUAOUCHE /Arthur FREY
//
// La classe Minuteur permet de chronometrer la partie
//=================================================================================//

import java.util.Timer;
import java.util.TimerTask;

public class Minuteur {
	int seconds=0;	
	Timer timer=new Timer();
	
	TimerTask tache=new TimerTask() {
		public void run() {
			//Incrementation des secondes
			seconds++;
		}
	};
//=================================================================================//
//Lanceur du minuteur
//=================================================================================//
	public void start() {
		timer.scheduleAtFixedRate(tache, 1000, 1000);
	}
//=================================================================================//
// Getter des secondes
//@return la valeur des secondes
//=================================================================================//
	public int getSec() {
		return seconds;
	}
}
