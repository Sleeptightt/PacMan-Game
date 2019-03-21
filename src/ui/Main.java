package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * <b> Laboratorio unidad 3 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class Main extends Application{

	/**
	* The main function responsible of initiating the program.
	* @param args Used to launch the program.
	*/
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	/**
	* The start function which initializes the stage and displays it. Also deactivates the threads when the window is close by using an anonymous nested class.
	* @param stage The main stage of the graphical interface.
	*/
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PacMan.fxml"));
		
		Parent root = loader.load();
		
		PacManController pacController = loader.getController();
		pacController.setStage(stage);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
					pacController.onCloseRequest();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
		
		Scene scene = new Scene(root);
    	stage.setTitle("Pac Man Game!");
    	stage.setScene(scene);
    	stage.setFullScreen(true);
    	stage.show();
	}

}
