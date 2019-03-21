package threads;

import javafx.application.Platform;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import model.PacManSprite;
import ui.PacManController;

public class PacManThread extends Thread{
	
	/**
	 * The association between the thread and the model package.
	 */
	private PacManSprite pacmanSprite;
	
	/**
	 * The thread's association with the controller.
	 */
	private PacManController pacmanControl;
		
	/**
	 * The GUI component that represents the eye of the PacMan.
	 */
	private Circle eye;
	
	/**
	 * The GUI component that represents the PacMan.
	 */
	private Arc pacman;
	
	/**
	 * The state of the thread.
	 */
	private boolean active;
		
	/**
	 * This function initializes a new PacManThread.
	 * @param pacmanControl The thread's association with the controller.
	 * @param eye The GUI component that represents the eye of the PacMan.
	 * @param pacman The GUI component that represents the PacMan.
	 * @param ps The association between the thread and the model package.
	 */
	public PacManThread(PacManController pacmanControl, Circle eye, Arc pacman, PacManSprite ps) {
		this.pacmanControl = pacmanControl;
		this.pacmanSprite = ps;
		this.eye = eye;
		this.pacman = pacman;
		active = !ps.isStopped();
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
					pacmanControl.movePacman(eye, pacman, pacmanSprite);
				}
			});
			
			try {
				sleep(pacmanSprite.getWaitT());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!pacmanControl.getGameBackGround().getChildren().contains(pacman)) {
				pacmanSprite.setStopped(true);
				deactivate();
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if(pacmanControl.getGame().allStopped())
						pacmanControl.mainGame();
				}
			});
			
		}
	}
	
	/**
	 * This function deactivates this thread.
	 */
	public void deactivate() {
		active = false;
	}

	/**
	 * This function obtains the GUI component for the PacMan of this thread.
	 * @return The PacMan of this specific thread.
	 */
	public Arc getPacman() {
		return pacman;
	}
	
	/**
	 * This function obtains the state of this thread.
	 * @return A boolean representing whether or not this thread is still running.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * This function obtains this thread's PacManSprite.
	 * @return The PacManSprite of the thread.
	 */
	public PacManSprite getSprite() {
		return pacmanSprite;
	}
	
	/**
	 * This function obtains the GUI component for the PacMan's eye of this thread.
	 * @return The eye of the PacMan.
	 */
	public Circle getEye() {
		return eye;
	}
}
