package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestGameStart extends TestCase {
	private Game aGame;

	@SuppressWarnings("unused")
	private Player al, bertha;

	public void setUp() {
		aGame = new Game();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
	}

	public void testBeforeGameStartsPlayersCanBeRemoved() {
		aGame.removePlayer(al);
		assertFalse(aGame.playerIsPlaying(al));
		assertEquals(1, aGame.getNumberOfPlayers());
	}

	public void testHasGameStarted() {
		assertFalse(aGame.gameHasStarted());
	}

	public void testCheckGameHasStartedAfterFirstTurn() throws Throwable {
		aGame.takeTurn();
		assertTrue(aGame.gameHasStarted());
	}

	public void testOnceGameStartsPlayersCannotBeAdded() throws Throwable {
		aGame.takeTurn();
		assertNull(aGame.addPlayer("Joe"));
		assertEquals(2, aGame.getNumberOfPlayers());
	}

	public void testOnceGameStartsPlayersCannotBeRemoved() throws Throwable {
		TurnResult turnResult = aGame.takeTurn(1, true);
		Player al = turnResult.player;
		aGame.removePlayer(al);
		assertEquals(2, aGame.getNumberOfPlayers());
		assertTrue(aGame.playerIsPlaying(al));
	}
}