package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <b> Laboratorio unidad 3 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class Game{
	
	/**
	 * The path of serialization for the scores.
	 */
	private static final String PATH_SCORES = "Data/HallOfFame.pac";
		
	/**
	 * The list of sprites of the game.
	 */
	private List<PacManSprite> sprites;
	
	/**
	 * The list of scores that represents the hall of fame.
	 */
	private List<Score> hallOfFame;
	
	/**
	 * The level of the current game.
	 */
	private int level;
	
	/**
	 * This function initializes a new game and loads the serialized scores, if there are any.
	 * @throws IOException If the specified path doesn't exist.
	 * @throws ClassNotFoundException If the specified class of the object can't be found.
	 */
	public Game() throws IOException, ClassNotFoundException {
		sprites = new ArrayList<PacManSprite>();
		loadScores();
	}
	
	/**
	 * This function serializes the list of scores.
	 * @throws IOException If the specified path doesn't exist.
	 */
	private void saveScores() throws IOException {

		File fl = new File(PATH_SCORES);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fl));
		oos.writeObject(hallOfFame);
		oos.close();
	}
	
	/**
	 * This function loads the serialized list of scores if there is one.
	 * @throws IOException If the specified path doesn't exist.
	 * @throws ClassNotFoundException If the specified class of the object can't be found.
	 */
	@SuppressWarnings("unchecked")
	private void loadScores() throws IOException, ClassNotFoundException{
		
		File fl = new File(PATH_SCORES);
		if(fl.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fl));
			hallOfFame = ( ArrayList<Score> ) ois.readObject();
			ois.close();
		}else {
			hallOfFame = new ArrayList<Score>();
		}
		
	}
	
	
	/**
	 * This function loads all the attributes of the game.
	 * @param path The file path.
	 * @throws IOException If the specified path doesn't exist.
	 */
	public void loadGame(String path) throws IOException {
		File fl = new File(path);
		FileReader fr = new FileReader(fl);
		BufferedReader in = new BufferedReader(fr);
		in.readLine();
		level = Integer.parseInt(in.readLine());
		in.readLine();
		String s = in.readLine();
		
		while(s!=null) {
			String[] arr = s.split(" ");
			double radius = Double.parseDouble(arr[0]);
			double posX = Double.parseDouble(arr[1]);
			double posY = Double.parseDouble(arr[2]);
			int waitT = Integer.parseInt(arr[3]);
			String direction = arr[4];
			int bounces = Integer.parseInt(arr[5]);
			boolean stopped = Boolean.parseBoolean(arr[6]);
			PacManSprite ps = new PacManSprite(radius, posX, posY, waitT, direction, bounces, stopped);
			sprites.add(ps);
			s = in.readLine();
		}
		fr.close();
		in.close();
	}
	
	/**
	 * This function saves all the attributes of the game in the specified file path.
	 * @param path The file path.
	 * @throws IOException If the specified path doesn't exist.
	 */
	public void saveGame(String path) throws IOException {
		PrintWriter pw = new PrintWriter(path);
		pw.print("#Level\n" + level + "\n#radius posX posY wait direction bounces stopped\n");
		for(int i = 0; i < sprites.size(); i++) {
			PacManSprite ps = sprites.get(i);
			pw.println(ps.getRadius() + " " + ps.getPosX() + " " + ps.getPosY() + " " + ps.getWaitT() + " " + ps.getDirection() + " " + ps.getBounces() + " " + ps.isStopped());
		}
		pw.close();
	}
	
	/**
	 * This function adds a new score to the hall of fame if it's better than any of the current ones.
	 * @param name The name of the player that achieved the score.
	 * @param score The score number.
	 * @throws IOException If the specified path doesn't exist.
	 */
	public void addScore(String name, int score) throws IOException {
		Score sc = new Score(name, score);
		if(hallOfFame.size() < 10) {
			hallOfFame.add(sc);
			Collections.sort(hallOfFame);
		}
		else {
			hallOfFame.add(sc);
			Collections.sort(hallOfFame);
			hallOfFame.remove(10);
		}
		saveScores();
	}
	
	/**
	 * This function tells whether or not all the PacMans are stopped.
	 * @return A boolean that tells whether or not all the PacMans are stopped.
	 */
	public boolean allStopped() {
		boolean exit = true;
		for(int i = 0; i < sprites.size() && exit; i++) {
			if(!sprites.get(i).isStopped())
				exit = false;
		}
		return exit;
	}
	
	/**
	 * This function obtains the list of sprites.
	 * @return the list of sprites of the current game.
	 */
	public List<PacManSprite> getSprites() {
		return sprites;
	}

	/**
	 * This function obtains the matrix of scores that represent the hall of fame as a whole.
	 * @return the hallOfFame matrix.
	 */
	public String[][] getHallOfFame() {
		String[][] hall = new String[10][2];
		for(int i = 0; i < 10;i++) {
			hall[i][0] = "Empty";
			hall[i][1] = "Empty";
		}
		for(int i = 0; i < hallOfFame.size();i++) {
			hall[i][0] = hallOfFame.get(i).getName();
			hall[i][1] = hallOfFame.get(i).getScore() + "";
		}
		
		return hall;
	}
	
	/**
	 * This function obtains the level of the game that's being played.
	 * @return The level of the current game.
	 */
	public int getLevel() {
		return level;
	}
}
