package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestRealTurns extends TestCase {
	private Game aGame;

	private Player al, bertha;

	public void setUp() {
		aGame = new Game();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
	}

	public void testOneRealTurn() throws Throwable {
		assertEquals(al, aGame.whoseTurnIsIt());
		aGame.takeTurn(); // al's turn
		assertEquals(bertha, aGame.whoseTurnIsIt());
	}
}