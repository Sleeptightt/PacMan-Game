package model;

import java.io.Serializable;

/**
 * <b> Laboratorio unidad 3 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
@SuppressWarnings("serial")
public class Score implements Comparable<Score>, Serializable{
	
	/**
	 * The name of the player that achieved this score.
	 */
	private String name;
	
	/**
	 * The score number.
	 */
	private int score;

	/**
	 * This function initializes a new Score.
	 * @param name The name of the player that achieved this score.
	 * @param score The score number.
	 */
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}

	/* 
	 * This function compares another score with this one.
	 * @param a The other score to be compared.
	 * @return An integer representing whether this score is smaller than(negative integer), bigger than(positive integer) or equal(zero).
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Score a) {
		return (this.score - a.getScore());
	}
	
	/**
	 * This function obtains the name of the player.
	 * @return The name of the player associated with this score.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This function obtains the score number.
	 * @return The score number.
	 */
	public int getScore() {
		return this.score;
	}
}
