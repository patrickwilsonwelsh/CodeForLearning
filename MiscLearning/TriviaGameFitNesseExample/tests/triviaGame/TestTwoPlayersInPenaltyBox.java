package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestTwoPlayersInPenaltyBox extends TestCase {
	private Game aGame;

	private Board theBoard;

	private Player al, bertha;

	public void setUp() {
		aGame = new Game();
		theBoard = aGame.getBoard();
		al = aGame.addPlayer("Al");
		bertha = aGame.addPlayer("Bertha");
		aGame.takeTurn(3, true); // Al
		aGame.takeTurn(5, true); // Bertha
		assertEquals(3, al.getPlace().getId());
		assertEquals(5, bertha.getPlace().getId());
		putPlayerInPenaltyBox(); // Al
		putPlayerInPenaltyBox(); // Bertha
	}

	private void putPlayerInPenaltyBox() {
		aGame.takeTurn(2, false);
	}

	private void rollaThree() {
		aGame.takeTurn(3, true);
	}

	private void rollATwo() {
		aGame.takeTurn(2, true);
	}

	public void testTwoPlayersAreInPenaltyBox() {
		assertTrue(al.getPlace().isPenaltyBox());
		assertTrue(bertha.getPlace().isPenaltyBox());
	}

	public void testPlayerPlacesBeforePenaltyBox() {
		Place alsFormerPlace = al.getPlaceBeforePenaltyBox();
		Place berthasFormerPlace = bertha.getPlaceBeforePenaltyBox();
		assertEquals(3, alsFormerPlace.getId());
		assertEquals(5, berthasFormerPlace.getId());
	}

	public void testOddRollForBerthaOnly_ReturnsBerthaToStartingPlacePlusRoll() {
		rollATwo(); // Al
		rollaThree(); // Bertha
		Place ninthPlace = theBoard.getPlaceById(8);
		assertEquals(ninthPlace, bertha.getPlace());
		assertTrue(al.getPlace().isPenaltyBox());
	}

	public void testOneOddRollApiece_ReturnsBothPlayersToStartingPlacePlusRoll() {
		rollaThree(); // Al
		rollaThree(); // Bertha
		theBoard.getPlaceById(8);
		assertEquals(theBoard.getPlaceById(8), bertha.getPlace());
		assertEquals(theBoard.getPlaceById(6), al.getPlace());
	}

	public void testOneEvenRollApiece_BothPlayersAreStillInPenaltyBox() {
		rollATwo();
		rollATwo();
		assertTrue(al.getPlace().isPenaltyBox());
		assertTrue(bertha.getPlace().isPenaltyBox());
	}

	public void testTwoEvenRollsApiece_BothPlayersAreStillInPenaltyBox() {
		assertTrue(al.getPlace().isPenaltyBox());
		assertTrue(bertha.getPlace().isPenaltyBox());
		for (int i = 0; i < 2; i++) {
			rollATwo();
			rollATwo();
		}
		assertTrue(bertha.getPlace().isPenaltyBox());
		assertTrue(al.getPlace().isPenaltyBox());
	}

	public void testThreeEvenRollsApiece_GetsBothPlayersBackToStartingPlaces() {
		for (int i = 0; i < 3; i++) {
			rollATwo();
			rollATwo();
		}

		theBoard.getPlaceById(0);
		assertEquals(theBoard.getPlaceById(5), bertha.getPlace());
		assertEquals(theBoard.getPlaceById(3), al.getPlace());
	}

	public void testThreeEvenRollsForBerthaOnly_GetsBerthaBackToStartingPlace() {
		assertTrue(al.getPlace().isPenaltyBox());
		assertTrue(bertha.getPlace().isPenaltyBox());
		for (int i = 0; i < 3; i++) {
			rollaThree(); // Al
			rollATwo(); // Bertha
		}

		theBoard.getPlaceById(0);
		assertEquals(theBoard.getPlaceById(5), bertha.getPlace());
		assertEquals(theBoard.getPlaceById(0), al.getPlace());
	}
}