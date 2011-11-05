package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestTwoPlayerGame extends TestCase {
	private Game aGame;

	private Player al, bertha;

	public void setUp() {
		aGame = new Game();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
	}

	public void testAddPlayers() {
		assertTrue(aGame.playerIsPlaying(al));
		assertTrue(aGame.playerIsPlaying(bertha));
		assertEquals(2, aGame.getNumberOfPlayers());
	}

	public void testPlayerIsNotPlaying() {
		Player sam = Player.newPlacelessPlayer("Sam");
		assertFalse(aGame.playerIsPlaying(sam));
	}

	public void testIsPlayable() {
		assertTrue(aGame.isPlayable());
	}

}