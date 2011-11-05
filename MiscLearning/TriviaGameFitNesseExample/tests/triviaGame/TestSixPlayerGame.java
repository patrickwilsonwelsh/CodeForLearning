package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestSixPlayerGame extends TestCase {
	private Game aGame;

	private Player al, bertha, joe, chet, ron, pat;

	public void setUp() {
		aGame = new Game();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
		joe = aGame.addPlayer("Joe");
		chet = aGame.addPlayer("Chet");
		ron = aGame.addPlayer("Ron");
		pat = aGame.addPlayer("Pat");
	}

	public void testPlayersArePlaying() {
		assertTrue(aGame.playerIsPlaying(al));
		assertTrue(aGame.playerIsPlaying(bertha));
		assertTrue(aGame.playerIsPlaying(joe));
		assertTrue(aGame.playerIsPlaying(chet));
		assertTrue(aGame.playerIsPlaying(ron));
		assertTrue(aGame.playerIsPlaying(pat));
		assertEquals(Game.MAX_PLAYERS, aGame.getNumberOfPlayers());
	}

	public void testIsPlayable() {
		assertTrue(aGame.isPlayable());
	}

	public void testAddingSeventhPlayerShouldFail() {
		assertNull(aGame.addPlayer("Dave"));
		assertEquals(Game.MAX_PLAYERS, aGame.getNumberOfPlayers());
	}

	public void testRemoveAllPlayers() {
		assertTrue(aGame.removeAllPlayers());

	}

}