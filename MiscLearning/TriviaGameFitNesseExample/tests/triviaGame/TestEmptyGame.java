package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestEmptyGame extends TestCase {
	private Game aGame;

	public void setUp() {
		aGame = new Game();
	}

	public void testHasNoPlayers() {
		assertEquals(0, aGame.getNumberOfPlayers());
	}

	public void testIsNotPlayable() {
		assertFalse(aGame.isPlayable());
	}

	public void HasNotStarted() {
		assertFalse(aGame.gameHasStarted());
	}
}