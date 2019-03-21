package model;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class PacManSpriteTest {
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery1() {}
	
	/**
	 * This function tests whether or not the PacManSprite constructor functions correctly.
	 */
	@Test
	void pacManSpriteTest() {
		setupScenery1();
		double radius = 30.0;
		double posX = 90.0;
		double posY = 180.0;
		int waitT = 20;
		String direction = PacManSprite.LEFT;
		int bounces = 3;
		boolean stopped = false;
		PacManSprite spriteTest = new PacManSprite(radius, posX, posY, waitT, direction, bounces, stopped);
		
		assertTrue("The radius is not assigned correctly", radius == spriteTest.getRadius());
		assertTrue("The posX is not assigned correctly", posX == spriteTest.getPosX());
		assertTrue("The posY is not assigned correctly", posY == spriteTest.getPosY());
		assertTrue("The wait time is not assigned correctly", waitT == spriteTest.getWaitT());
		assertTrue("The direction is not assigned correctly", direction.equals(spriteTest.getDirection()));
		assertTrue("The bounces are not assigned correctly", bounces == spriteTest.getBounces());
		assertTrue("The state is not assigned correctly", stopped == spriteTest.isStopped());
	}

}
