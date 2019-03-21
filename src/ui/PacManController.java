package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Game;
import model.PacManSprite;
import threads.MouthAnimationThread;
import threads.PacManThread;

/**
 * <b> Laboratorio unidad 3 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class PacManController {
	
	/**
    * The main stage handled by the controller.
    */
	private Stage stage;
	
	
	/**
	 * The gridPane that shows the players top scores.
	 */
	private GridPane scoreGrid;
	
	
	/**
	 * The association between the controller and the Game that is being displayed.
	 */
	private Game mainGame;
	
	/**
	 * The label which shows the current level that is being played.
	 */
	@FXML
	private Label levelLabel;
	
	
    /**
     * The main game background.
     */
    @FXML
    private Pane gameBackGround;

    
    /**
     * The menu of options for the user to choose from.
     */
    @FXML
    private MenuBar menuOptions;

    
    /**
     * The label which shows how many times the pacmans have bounced.
     */
    @FXML
    private Label bounceCounter;
    
    
    /**
     * A list of PacManThreads which is used to deactivate them all in the end.
     */
    private List<PacManThread> threads;
    
    /**
     * A list of MouthAnimationThreads which is used to deactivate them all in the end.
     */
    private List<MouthAnimationThread> mouthThreads;
    
    @FXML
    /**
     * This function initializes the Game and adds event handlers for the different menu options.
     */
    public void initialize() {
    	scoreGrid = null;
    	threads = new ArrayList<PacManThread>();
    	mouthThreads = new ArrayList<MouthAnimationThread>();
    	try {
			mainGame = new Game();
		} catch (ClassNotFoundException | IOException e2) {
			e2.printStackTrace();
		}
    	int counter = 1;
    	for(int i = 0; i < menuOptions.getMenus().size(); i++) {
    		for(int j = 0 + counter; j < menuOptions.getMenus().get(i).getItems().size();j++) {
    			String a = menuOptions.getMenus().get(i).getItems().get(j).getText();
    			menuOptions.getMenus().get(i).getItems().get(j).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						if(a.equals("Exit game")) {
							try {
								onCloseRequest();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							System.exit(0);
						}
						else if(a.equals("Save game")) {
							try {
								mainGame.saveGame("Data/savedGame.txt");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else if(a.equals("Hall of fame")) {
							showHallOfFame();
						}
						else if(a.equals("About the game")) {
							aboutTheGame();
						}
					}
    				
    			});
    		}
    		counter--;	
    	}
    }
    
    
    /**
     * This function is triggered when the user clicks the level one menu option. It loads the game's level one.
     * @param event The event that triggered this function to be called.
     * @throws IOException When the file containing the level is not found.
     */
    @FXML
    void levelOne(ActionEvent event) throws IOException {
    	bounceCounter.setText("Bounces: 0");
    	gameBackGround.getChildren().clear();
    	mainGame.getSprites().clear();
    	mainGame.loadGame("Data/Level1.txt");
    	levelLabel.setText("Level " + mainGame.getLevel());
    	startGame();
    }

    
    /**
     * This function is triggered when the user clicks the level two menu option. It loads the game's level two.
     * @param event The event that triggered this function to be called.
     * @throws IOException When the file containing the level is not found.
     */
    @FXML
    void levelTwo(ActionEvent event) throws IOException {
    	bounceCounter.setText("Bounces: 0");
    	gameBackGround.getChildren().clear();
    	mainGame.getSprites().clear();
    	mainGame.loadGame("Data/Level2.txt");
    	levelLabel.setText("Level " + mainGame.getLevel());
    	startGame();
    }

    /**
     * This function is triggered when the user clicks the level zero menu option. It loads the game's level zero.
     * @param event The event that triggered this function to be called.
     * @throws IOException When the file containing the level is not found.
     */
    @FXML
    void levelZero(ActionEvent event) throws IOException {
    	bounceCounter.setText("Bounces: 0");
    	gameBackGround.getChildren().clear();
    	mainGame.getSprites().clear();
    	mainGame.loadGame("Data/Level0.txt");
    	levelLabel.setText("Level " + mainGame.getLevel());
    	startGame();
    }
    
    /**
     * This function is triggered when the user clicks the saved game menu option. It loads the previously saved game or nothing if there is no saved game.
     * @param event The event that triggered this function to be called.
     * @throws IOException When the file containing the level is not found.
     */
    @FXML
    void savedGame(ActionEvent event) throws IOException {
    	bounceCounter.setText("Bounces: 0");
    	gameBackGround.getChildren().clear();
    	mainGame.getSprites().clear();
    	mainGame.loadGame("Data/savedGame.txt");
    	levelLabel.setText("Level " + mainGame.getLevel());
    	startGame();
    }
    
    
    /**
     * This function is called after the game has been loaded and it is responsible of creating the graphical user interface components to show the PacMans on screen.
     * It also creates a PacManThread and a MouthAnimationThread for each of the PacMans.
     */
    public void startGame(){
    	for(int i = 0; i < mainGame.getSprites().size(); i++) {
    		PacManSprite ps = mainGame.getSprites().get(i);
    		if(!ps.isStopped()) {
	    		Circle eye = new Circle(ps.getPosX()+5, ps.getPosY() - ps.getRadius()/2, 5, Color.BLACK);
	    		Arc pacman = new Arc(ps.getPosX(), ps.getPosY(), ps.getRadius(), ps.getRadius(), 45, 270);
	    		pacman.setType(ArcType.ROUND);
	    		pacman.setFill(Color.YELLOW);
	    		if(ps.getDirection().equals(PacManSprite.DOWN)) {
	    			pacman.setRotate(-270);
	    			eye.setCenterX(ps.getPosX() + ps.getRadius()/2);
	    			eye.setCenterY(ps.getPosY() + ps.getRadius()/2);
	    		}
	    		else if(ps.getDirection().equals(PacManSprite.UP)) {
	    			pacman.setRotate(-90);
	    			eye.setCenterX(ps.getPosX() + ps.getRadius()/2);
	    			eye.setCenterY(ps.getPosY() - ps.getRadius()/2);
	    		}
	    		else if(ps.getDirection().equals(PacManSprite.LEFT)) {
	    			pacman.setRotate(-180);
	    			eye.setCenterX(ps.getPosX() - 5);
	    		}
				gameBackGround.getChildren().add(pacman);
				gameBackGround.getChildren().add(eye);
				pacman.setOnMouseClicked(new EventHandler<MouseEvent>() {
	
					@Override
					public void handle(MouseEvent e) {
						gameBackGround.getChildren().remove(eye);
						gameBackGround.getChildren().remove(pacman);
					}
					
				});
				eye.setOnMouseClicked(pacman.getOnMouseClicked());
				MouthAnimationThread mAT = new MouthAnimationThread(this, pacman);
				mouthThreads.add(mAT);
				mAT.start();
				PacManThread pt = new PacManThread(this, eye, pacman, ps);
				threads.add(pt);
				pt.start();
				
    		}
    		int times = Integer.parseInt(bounceCounter.getText().split(" ")[1])+ps.getBounces();
			bounceCounter.setText("Bounces: " + times);
    	}
    }
   
    
    /**
     * This function is called when all the PacMans have been caught and it is responsible of interacting with the user in order to save scores and continue to the next level if there is one.
     */
    public void mainGame() {
    	gameBackGround.getChildren().clear();
    	TextField nameTF = new TextField();
		nameTF.setPromptText("Enter your name");
		nameTF.setLayoutX(300);
		nameTF.setLayoutY(300);
		Button submit = new Button("Submit score");
		submit.setLayoutX(300);
		submit.setLayoutY(350);
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				int bounces = Integer.parseInt(bounceCounter.getText().split(" ")[1]);
				try {
					mainGame.addScore(nameTF.getText(), bounces);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				gameBackGround.getChildren().remove(nameTF);
				gameBackGround.getChildren().remove(submit);
				showHallOfFame();
				Button continuee = new Button("Continue");
				continuee.setMaxWidth(500);
				continuee.setMaxHeight(500);
				continuee.setLayoutX(900);
				continuee.setLayoutY(450);
				continuee.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						if(mainGame.getLevel() == 0) {
							try {
								levelOne(null);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else if(mainGame.getLevel() == 1) {
							try {
								levelTwo(null);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else {
							
						}
					}
					
				});
				gameBackGround.getChildren().add(continuee);
			}
			
		});
		gameBackGround.getChildren().add(nameTF);
		gameBackGround.getChildren().add(submit);
		
    }
    
    
    /**
     * This function is responsible of animating the opening and closing animation of the PacMan's mouth.
     * @param pacman The arc representing the PacMan.
     * @param open An integer representing how much of the mouth has to be opened at the time.
     */
    public void animateMouth(Arc pacman, int open) {
    	if(open == 0) {
    		pacman.setLength(270);
    		pacman.setStartAngle(45);
    	}else if(open == 1){
    		pacman.setLength(315);
    		pacman.setStartAngle(22);
    	}else {
    		pacman.setLength(360);
    		pacman.setStartAngle(0);
    	}
    }
    
    
	/**
	 * This function is responsible of moving the PacMan depending on it's direction. It handles the moment when the PacMan bounces with the walls or with other PacMans.
	 * @param eye The circle representing the eye of the PacMan.
	 * @param pacman The arc representing the PacMan.
	 * @param ps The PacManSprite that is associated with this specific PacMan.
	 */
	public void movePacman(Circle eye, Arc pacman, PacManSprite ps) {
		
	    if(ps.getDirection().equals(PacManSprite.LEFT)) {
			eye.setCenterX(eye.getCenterX()-5);
			pacman.setCenterX(pacman.getCenterX()-5);
			ps.setPosX(pacman.getCenterX());
			if(pacman.getCenterX() - pacman.getRadiusX()<= 0 || isTouchingOtherSprite(pacman)) {
				pacman.setRotate(pacman.getRotate() + 180);
				eye.setCenterX(pacman.getCenterX() + 5);
				eye.setCenterY(pacman.getCenterY() - ps.getRadius()/2);
				int times = Integer.parseInt(bounceCounter.getText().split(" ")[1])+1;
				bounceCounter.setText("Bounces: " + times);
				ps.setBounces(ps.getBounces()+1);
				ps.setDirection(PacManSprite.RIGHT);
				pacman.setCenterX(pacman.getCenterX() + 5);
				eye.setCenterX(eye.getCenterX() + 5);
			}
		}
		else if(ps.getDirection().equals(PacManSprite.RIGHT)) {
			eye.setCenterX(eye.getCenterX()+5);
			pacman.setCenterX(pacman.getCenterX()+5);
			ps.setPosX(pacman.getCenterX());
			if(pacman.getCenterX() >= (stage.getScene().getWidth() - pacman.getRadiusX()) || isTouchingOtherSprite(pacman)) {
				pacman.setRotate(-180);
				eye.setCenterX(pacman.getCenterX() - 5);
				eye.setCenterY(pacman.getCenterY() - ps.getRadius()/2);
				int times = Integer.parseInt(bounceCounter.getText().split(" ")[1])+1;
				bounceCounter.setText("Bounces: " + times);
				ps.setBounces(ps.getBounces()+1);
				ps.setDirection(PacManSprite.LEFT);
				pacman.setCenterX(pacman.getCenterX() - 5);
				eye.setCenterX(eye.getCenterX() - 5);
			}
		}
		else if(ps.getDirection().equals(PacManSprite.DOWN)) {
			eye.setCenterY(eye.getCenterY()+5);
			pacman.setCenterY(pacman.getCenterY()+5);
			ps.setPosY(pacman.getCenterY());
			if(pacman.getCenterY() >= (stage.getScene().getHeight() - (pacman.getRadiusX()*2)) || isTouchingOtherSprite(pacman)) {
				pacman.setRotate(pacman.getRotate()+180);
				eye.setCenterX(pacman.getCenterX() + ps.getRadius()/2);
				eye.setCenterY(pacman.getCenterY() - ps.getRadius()/2);
				int times = Integer.parseInt(bounceCounter.getText().split(" ")[1])+1;
				bounceCounter.setText("Bounces: " + times);
				ps.setBounces(ps.getBounces()+1);
				ps.setDirection(PacManSprite.UP);
				pacman.setCenterY(pacman.getCenterY() - 5);
				eye.setCenterY(eye.getCenterY() - 5);
			}
		}
		else {
			eye.setCenterY(eye.getCenterY()-5);
			pacman.setCenterY(pacman.getCenterY()-5);
			ps.setPosY(pacman.getCenterY());
			if(pacman.getCenterY() - pacman.getRadiusY()<=0 || isTouchingOtherSprite(pacman)) {
				pacman.setRotate(pacman.getRotate()-180);
				eye.setCenterX(pacman.getCenterX() + ps.getRadius()/2);
				eye.setCenterY(pacman.getCenterY() + ps.getRadius()/2);
				int times = Integer.parseInt(bounceCounter.getText().split(" ")[1])+1;
				bounceCounter.setText("Bounces: " + times);
				ps.setBounces(ps.getBounces()+1);
				ps.setDirection(PacManSprite.DOWN);
				pacman.setCenterY(pacman.getCenterY() + 5);
				eye.setCenterY(eye.getCenterY() + 5);
			}
		}
    }
 
	
	/**
	 * This function tells whether or not this PacMan is touching another one anywhere on the screen. If it is, it changes the direction of the other PacMan that is touching and returns true.
	 * @param pacman The arc representing the PacMan.
	 * @return A boolean representing whether or not this PacMan is touching another PacMan.
	 */
	public boolean isTouchingOtherSprite(Arc pacman) {
		boolean exit = false;
		for(int i = 0; i < threads.size(); i++) {
			Arc otherPac = threads.get(i).getPacman();
			Circle eye = threads.get(i).getEye();
			double cx1 =otherPac.getCenterX();
			double cy1 =otherPac.getCenterY();
			double r1 = otherPac.getRadiusX();
			double cx2 = pacman.getCenterX();
			double cy2 = pacman.getCenterY();
			double r2 = pacman.getRadiusX();
			double distance = Math.sqrt( (cx1 - cx2)*(cx1 - cx2) + (cy1 - cy2)*(cy1 - cy2) );
			if(distance <= r1+r2 && threads.get(i).isActive() && !otherPac.equals(pacman)) {
				if(threads.get(i).getSprite().getDirection().equals(PacManSprite.LEFT)) {
					otherPac.setRotate(otherPac.getRotate() + 180);
					eye.setCenterX(otherPac.getCenterX() + 5);
					eye.setCenterY(otherPac.getCenterY() -  threads.get(i).getSprite().getRadius()/2);
					threads.get(i).getSprite().setDirection(PacManSprite.RIGHT);
					otherPac.setCenterX(otherPac.getCenterX() + 5);
					eye.setCenterX(eye.getCenterX() + 5);
				}
				else if(threads.get(i).getSprite().getDirection().equals(PacManSprite.RIGHT)) {
					otherPac.setRotate(-180);
					eye.setCenterX(otherPac.getCenterX() - 5);
					eye.setCenterY(otherPac.getCenterY() -  threads.get(i).getSprite().getRadius()/2);
					threads.get(i).getSprite().setDirection(PacManSprite.LEFT);
					otherPac.setCenterX(otherPac.getCenterX() - 5);
					eye.setCenterX(eye.getCenterX() - 5);
				}
				else if(threads.get(i).getSprite().getDirection().equals(PacManSprite.DOWN)) {
					otherPac.setRotate(otherPac.getRotate()+180);
					eye.setCenterX(otherPac.getCenterX() + threads.get(i).getSprite().getRadius()/2);
					eye.setCenterY(otherPac.getCenterY() - threads.get(i).getSprite().getRadius()/2);
					threads.get(i).getSprite().setDirection(PacManSprite.UP);
					otherPac.setCenterY(otherPac.getCenterY() - 5);
					eye.setCenterY(eye.getCenterY() - 5);
				}
				else if(threads.get(i).getSprite().getDirection().equals(PacManSprite.UP)) {
					otherPac.setRotate(otherPac.getRotate()-180);
					eye.setCenterX(otherPac.getCenterX() + threads.get(i).getSprite().getRadius()/2);
					eye.setCenterY(otherPac.getCenterY() + threads.get(i).getSprite().getRadius()/2);
					threads.get(i).getSprite().setDirection(PacManSprite.DOWN);
					otherPac.setCenterY(otherPac.getCenterY() + 5);
					eye.setCenterY(eye.getCenterY() + 5);
				}
				exit = true;
			}
		}
		return exit;
	}
	
	
    /**
     * This function fills the score grid and shows it, represents the top scores in the game.
     */
    public void showHallOfFame() {
    	GridPane grid = new GridPane();
    	String[][] hall = mainGame.getHallOfFame();
    	for(int i = 0; i < hall.length; i++) {
    		for(int j = 0; j < hall[i].length; j++) {
    			Label lb = new Label(hall[i][j]);
    			grid.add(lb, j+1, i);
    			GridPane.setHalignment(lb, HPos.CENTER);
    		}
    	}
    	for(int j = 0; j < 10; j++) {
    		Label lb = new Label(String.valueOf(j+1));
    		grid.add(lb, 0, j);
    		GridPane.setHalignment(lb, HPos.CENTER);
    	}
    	ColumnConstraints column = new ColumnConstraints();
    	column.setPercentWidth(30);
    	grid.getColumnConstraints().add(column);

    	column = new ColumnConstraints();
    	column.setPercentWidth(30);
    	grid.getColumnConstraints().add(column);
    	grid.getColumnConstraints().add(column);
    	
    	grid.setMaxHeight(500);
    	grid.setPrefWidth(500);
    	grid.setAlignment(Pos.CENTER);
    	grid.setGridLinesVisible(true);
    	grid.setLayoutX(700);
    	grid.setLayoutY(175);
    	grid.getStyleClass().add("mygridStyle");
    	Button hide = new Button("Hide");
    	hide.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gameBackGround.getChildren().remove(grid);
			}
    		
    	});
    	grid.add(hide, 1, 10);
    	GridPane.setHalignment(hide, HPos.CENTER);
    	if(scoreGrid!=null)
    		gameBackGround.getChildren().remove(scoreGrid);
    	
    	gameBackGround.getChildren().add(grid);
    	scoreGrid = grid;
    }
    
    
    /**
     * This function shows information about the game on screen when the menu option is clicked.
     */
    public void aboutTheGame() {
    	
    }
    
    /**
     * This function deactivates all the threads currently running. It's called when the user exits the game or clicks the "X" on the top right corner.
     * @throws InterruptedException if any thread has interrupted the current thread.
     */
    @FXML
    public void onCloseRequest() throws InterruptedException {
    	for (int i = 0; i < threads.size(); i++) {
			threads.get(i).deactivate();
			mouthThreads.get(i).deactivate();
			threads.get(i).join();
			mouthThreads.get(i).join();
		}
    	System.out.println("Threads finalized!");
    }
    
    
    /**
     * This function obtains the main game background.
	 * @return The main game background.
	 */
	public Pane getGameBackGround() {
		return gameBackGround;
	}
	
	
	/**
	 * This function obtains the main game that is currently being played.
	 * @return The main game that is currently being played.
	 */
	public Game getGame() {
		return mainGame;
	}
	
	
	/**
	 * This function modifies the controller's stage.
	 * @param stage The new stage.
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
