package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestOnePlayerInPenaltyBox extends TestCase {
	private Game aGame;

	private Board theBoard;

	@SuppressWarnings("unused")
	private Player al, bertha;

	public void setUp() {
		aGame = new Game();
		theBoard = aGame.getBoard();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
		putPlayerInPenaltyBox(); // Al
	}

	private void putPlayerInPenaltyBox() {
		aGame.takeTurn(2, false);
	}

	private void oddRollTurn() {
		aGame.takeTurn(3, true);
	}

	private void evenRollTurn() {
		aGame.takeTurn(2, true);
	}

	public void testIncorrectAnswerPutsPlayerInPenaltyBox() throws Throwable {
		assertTrue(al.getPlace().isPenaltyBox());
	}

	public void OneEvenRollPlayerIsStillInPenaltyBox() throws Throwable {
		oddRollTurn();
		evenRollTurn();
		assertTrue(al.getPlace().isPenaltyBox());
	}

	public void testTwoEvenRollsPlayerIsStillInPenaltyBox() throws Throwable {
		for (int i = 0; i < 2; i++) {
			oddRollTurn(); // Bertha
			evenRollTurn(); // Al
		}
		assertTrue(al.getPlace().isPenaltyBox());
	}

	public void testThreeEvenRollsGetsPlayerOutOfPenaltyBox() throws Throwable {

		aGame.takeTurn(3, true);

		aGame.takeTurn(2, true); // Al
		aGame.takeTurn(3, true);

		aGame.takeTurn(2, true); // Al
		aGame.takeTurn(3, true);

		TurnResult turnResult = aGame.takeTurn(2, true); // Al

		Place firstPlace = theBoard.getPlaceById(0);
		assertEquals(firstPlace, turnResult.player.getPlace());
		assertEquals(firstPlace, al.getPlace());
	}

	public void testOddRollGetsPlayerOutOfPenaltyBox() throws Throwable {
		oddRollTurn(); // Bertha
		oddRollTurn(); // Al
		Place fourthPlace = theBoard.getPlaceById(3);
		assertEquals(fourthPlace, al.getPlace());
	}
}