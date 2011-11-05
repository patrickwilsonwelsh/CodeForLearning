package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestOnePlayerGame extends TestCase {
	private Game aGame;

	private Player al;

	public void setUp() {
		aGame = new Game();
		al = aGame.addPlayer("Al");
	}

	public void testAddPlayer() {
		assertTrue(aGame.playerIsPlaying(al));
		assertEquals(1, aGame.getNumberOfPlayers());
	}

	public void testGetPlayerName() {
		assertEquals("Al", al.getName());
	}

	public void testIsNotPlayable() {
		assertFalse(aGame.isPlayable());
	}

	public void testHasNotStarted() {
		assertFalse(aGame.gameHasStarted());
	}

	public void testCannotAddPlayerTwice() {
		assertTrue(aGame.playerIsPlaying(al));
		assertNull(aGame.addPlayer("Al"));
	}

}