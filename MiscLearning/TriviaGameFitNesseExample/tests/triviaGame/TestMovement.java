package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestMovement extends TestCase {
	private Game aGame;

	private Board theBoard;

	private Player al;

	public void setUp() {
		aGame = new Game();
		theBoard = aGame.getBoard();
		al = aGame.addPlayer("Al");
	}

	public void testNewPlayerAssignedToFirstPlace() {
		Place firstPlace = theBoard.getFirstPlace();
		assertEquals(firstPlace, theBoard.getPlaceForPlayer(al));
	}

	public void testMovePlayerToNextPlace() throws Throwable {
		Place secondPlace = theBoard.getPlaceById(1);
		theBoard.advancePlayer(al);
		assertEquals(secondPlace, theBoard.getPlaceForPlayer(al));
	}

	public void testMovePlayerFromLastToFirstPlace() throws Throwable {
		Place lastPlace = theBoard.getLastPlace();
		Place firstPlace = theBoard.getFirstPlace();
		al.setPlace(lastPlace);
		theBoard.advancePlayer(al);
		assertEquals(firstPlace, theBoard.getPlaceForPlayer(al));
	}

	public void PutPlayerInWinningPlace() {
		Place winningPlace = theBoard.getWinningPlace();
		al.setPlace(winningPlace);
		assertTrue(al.getPlace().isWinningPlace());
	}

	public void MovePlayerAroundTheBoard() throws Throwable {
		Place firstPlace = theBoard.getFirstPlace();
		for (int i = 0; i < 12; i++) {
			theBoard.advancePlayer(al);
		}
		assertEquals(firstPlace.getId(), al.getPlace().getId());
	}
}