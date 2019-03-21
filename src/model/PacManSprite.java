package model;

/**
 * <b> Laboratorio unidad 3 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class PacManSprite {

	/**
	 * One of the possible directions. 
	 */
	public static final String LEFT = "LEFT";
	
	/**
	 * One of the possible directions. 
	 */
	public static final String RIGHT = "RIGHT";
	
	/**
	 * One of the possible directions. 
	 */
	public static final String DOWN = "DOWN";
	
	/**
	 * One of the possible directions. 
	 */
	public static final String UP = "UP";
	
	/**
	 * The radius of the PacMan sprite.
	 */
	private double radius;
	
	/**
	 * The position on the x axis of the PacMan sprite.
	 */
	private double posX;
	
	/**
	 * The position on the y axis of the PacMan sprite.
	 */
	private double posY;
	
	/**
	 * The time that the PacMan sprite has to wait with each movement.
	 */
	private int waitT;
	
	/**
	 * The direction that the PacMan sprite is currently moving in.
	 */
	private String direction;
	
	/**
	 * The bounces that this sprite has achieved.
	 */
	private int bounces;
	
	/**
	 * Tells whether or not this PacMan is stopped.
	 */
	private boolean stopped;
	
	/**
	 * This function initializes a new PacManSprite.
	 * @param radius The radius of the PacMan sprite.
	 * @param posX The position on the x axis of the PacMan sprite.
	 * @param posY The position on the y axis of the PacMan sprite.
	 * @param waitT The time that the PacMan sprite has to wait with each movement.
	 * @param direction The direction that the PacMan sprite is currently moving in.
	 * @param bounces The bounces that this sprite has achieved.
	 * @param stopped Tells whether or not this PacMan is stopped.
	 */
	public PacManSprite(double radius, double posX, double posY, int waitT, String direction, int bounces, boolean stopped) {
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waitT = waitT;
		this.direction = direction;
		this.bounces = bounces;
		this.stopped = stopped;
	}

	/**
	 * This function obtains the radius of the sprite.
	 * @return the radius of the PacMan sprite.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * This function obtains the X position of the sprite.
	 * @return the position on the x axis of the PacMan sprite.
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * This function obtains the Y position of the sprite.
	 * @return the position on the y axis of the PacMan sprite.
	 */
	public double getPosY() {
		return posY;
	}

	/**
	 * This function obtains the wait time of the sprite.
	 * @return the wait time of the PacMan sprite.
	 */
	public int getWaitT() {
		return waitT;
	}

	/**
	 * This function obtains the direction of the sprite.
	 * @return the direction of the PacMan sprite.
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * This function obtains the number of bounces of the sprite.
	 * @return the number of bounces of the PacMan sprite.
	 */
	public int getBounces() {
		return bounces;
	}

	/**
	 * This function obtains the state of the sprite.
	 * @return the state of the PacMan sprite.
	 */
	public boolean isStopped() {
		return stopped;
	}

	/**
	 * This function modifies the direction of the PacMan sprite.
	 * @param direction the direction to set to the sprite.
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * This function modifies the X position of the PacMan sprite.
	 * @param posX the posX to set to the sprite.
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * This function modifies the Y position of the PacMan sprite.
	 * @param posY the posY to set to the sprite.
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}

	/**
	 * This function modifies the number of bounces of the PacMan sprite.
	 * @param bounces the number of bounces to set to the sprite.
	 */
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	/**
	 * This function modifies the state of the PacMan sprite.
	 * @param stopped the state to set to the sprite.
	 */
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}	
}