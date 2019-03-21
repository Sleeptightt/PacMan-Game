package threads;

import javafx.application.Platform;
import javafx.scene.shape.Arc;
import ui.PacManController;

/**
 * <b> Laboratorio unidad 3 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class MouthAnimationThread extends Thread{

	/**
	 * The state of the thread.
	 */
	private boolean active;
	
	/**
	 * Describes whether the mouth is opening or the mouth is closing.
	 */
	private boolean order;
	
	
	/**
	 * Determines the stages of the opening or closing of the mouth.
	 */
	private int open;
	
	/**
	 * The thread's association with the controller.
	 */
	private PacManController pacmanControl;
	
	/**
	 * The GUI component that represents the PacMan.
	 */
	private Arc pacman;

	
	/**
	 * This function initializes a new MouthAnimationThread.
	 * @param pacmanControl The thread's association with the controller.
	 * @param pacman The GUI component that represents the PacMan.
	 */
	public MouthAnimationThread(PacManController pacmanControl, Arc pacman) {
		order = true;
		open = 0;
		this.pacmanControl = pacmanControl;
		this.pacman = pacman;
		active = true;
	}

	
	/* 
	 * This function makes the thread start it's process.
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while(active) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					pacmanControl.animateMouth(pacman, open);
					if(order) {
						open++;
						if(open == 3) {
							open = 2;
							order = false;
						}
					}else {
						open--;
						if(open == -1) {
							open = 0;
							order = true;
						}
					}
				}
			});
			
			try {
				sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * This function deactivates this thread.
	 */
	public void deactivate() {
		active = false;
	}
}
