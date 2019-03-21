package model;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class ScoreTest {
	
	/**
	 * The association between the score and it's test.
	 */
	private Score myScore;
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery1() {}
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery2() {
		myScore = new Score("Yuji", 10);
	}
	
	/**
	 * This function tests whether or not the score constructor functions correctly.
	 */
	@Test
	void scoreTest() {
		setupScenery1();
		String name = "Cesarin";
		int score = 19;
		Score testScore = new Score(name, score);
		
		assertTrue("The name is not assigned correctly", name.equals(testScore.getName()));
		assertTrue("The size is not assigned correctly", score == testScore.getScore());
	}
	
	/**
	 * This function tests whether or not the scores are being sorted correctly.
	 */
	@Test
	void compareToTest() {
		setupScenery2();
		String name = "Cesarin";
		int score = 19;
		Score testScore = new Score(name, score);
		assertTrue("The sorting is not being done correctly", myScore.compareTo(testScore) < 0);
	}

}
