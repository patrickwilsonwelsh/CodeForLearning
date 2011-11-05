package triviaGame;

import junit.framework.TestCase;

public class TestFakeTurns extends TestCase {
	private Board theBoard;

	private Game aGame;

	private Player al, bertha;

	private int roll;

	boolean correctAnswer;

	public void setUp() {
		aGame = new Game();
		theBoard = aGame.getBoard();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
		roll = 6;
		correctAnswer = true;
	}

	public void testOneFakeTurn() throws Throwable {
		aGame.takeTurn(roll, correctAnswer); // al's turn
		assertEquals(theBoard.getPlaceById(6), theBoard.getPlaceForPlayer(al));
		assertEquals(theBoard.getPlaceById(0), theBoard
				.getPlaceForPlayer(bertha));
	}

	public void testTwoFakeTurns() throws Throwable {
		assertEquals(al, aGame.whoseTurnIsIt());
		aGame.takeTurn(roll, correctAnswer); // al's turn
		assertEquals(theBoard.getPlaceById(6), theBoard.getPlaceForPlayer(al));
		roll = 3;
		assertEquals(bertha, aGame.whoseTurnIsIt());
		aGame.takeTurn(roll, correctAnswer);
		aGame.takeTurn(roll, correctAnswer);
		assertEquals(theBoard.getPlaceById(3), theBoard
				.getPlaceForPlayer(bertha));
	}

	public void testThreeFakeTurns() throws Throwable {
		aGame.takeTurn(roll, correctAnswer); // al's turn
		roll = 3;
		aGame.takeTurn(roll, correctAnswer); // bertha's turn
		roll = 8;
		aGame.takeTurn(roll, correctAnswer); // al's turn again
		assertEquals(theBoard.getPlaceById(2), theBoard.getPlaceForPlayer(al));
	}

	public void testPlayerLandingOnSpace() {
		aGame.takeTurn(2, correctAnswer);
		Board theBoard = aGame.getBoard();
		Place alsPlace = theBoard.getPlaceForPlayer(al);
		assertTrue(alsPlace.isSports());
	}

	public void testCorrectAnswerGetsGoldCoin() {
		aGame.takeTurn(roll, correctAnswer);
		assertEquals(1, al.getGoldCoins());
	}

	private void takeTestTurn() {
		roll = 3;
		aGame.takeTurn(roll, correctAnswer);
	}

	public void testSixGoldCoinsPutsPlayerInWinningPlace() throws Throwable {
		aGame.takeTurn(roll, correctAnswer);

		for (int i = 0; i < 10; i++) {
			takeTestTurn();
		}
		assertEquals(6, al.getGoldCoins());
		assertTrue(theBoard.getPlaceForPlayer(al).isWinningPlace());
	}

	public void testCorrectAnswerInWinningPlaceWinsGame() throws Throwable {
		aGame.takeTurn(roll, correctAnswer);
		for (int i = 0; i < 12; i++) {
			takeTestTurn();
		}
		assertTrue(aGame.playerHasWonGame(al));
	}

	public void testIncorrectAnswerInWinningPlacePutsYouInPenaltyBox()
			throws Throwable {
		aGame.takeTurn(roll, correctAnswer);
		for (int i = 0; i < 11; i++) {
			takeTestTurn();
		}
		correctAnswer = false;
		takeTestTurn(); // al
		assertTrue(al.getPlace().isPenaltyBox());
	}
}