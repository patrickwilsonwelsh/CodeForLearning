package triviaGame;

import junit.framework.TestCase;

public class TestQuestions extends TestCase {
	private Board theBoard;

	public void setUp() {
		theBoard = new Board();
	}

	public void testGetQuestionsFromPopPlaceFromGame() {
		Place popPlace = theBoard.getPlaceById(0);
		assertEquals("Pop1=Answer1", popPlace.getQuestionAnswerPair());
		assertEquals("Pop2=Answer2", popPlace.getQuestionAnswerPair());
		assertEquals("Pop3=Answer3", popPlace.getQuestionAnswerPair());
		assertEquals("Pop1=Answer1", popPlace.getQuestionAnswerPair());
	}

	public void testGetQuestionsFromSciencePlaceFromGame() {
		Place sciencePlace = theBoard.getPlaceById(1);
		assertEquals("Science1=Answer1", sciencePlace.getQuestionAnswerPair());
		assertEquals("Science2=Answer2", sciencePlace.getQuestionAnswerPair());
		assertEquals("Science3=Answer3", sciencePlace.getQuestionAnswerPair());
		assertEquals("Science1=Answer1", sciencePlace.getQuestionAnswerPair());
	}

	public void testGetQuestionsFromSportsPlaceFromGame() {
		Place sportsPlace = theBoard.getPlaceById(2);
		assertEquals("Sports1=Answer1", sportsPlace.getQuestionAnswerPair());
		assertEquals("Sports2=Answer2", sportsPlace.getQuestionAnswerPair());
		assertEquals("Sports3=Answer3", sportsPlace.getQuestionAnswerPair());
		assertEquals("Sports1=Answer1", sportsPlace.getQuestionAnswerPair());
	}

	public void testGetQuestionsFromRockPlaceFromGame() throws Throwable {
		Place rockPlace = theBoard.getPlaceById(3);
		assertEquals("Rock1=Answer1", rockPlace.getQuestionAnswerPair());
		assertEquals("Rock2=Answer2", rockPlace.getQuestionAnswerPair());
		assertEquals("Rock3=Answer3", rockPlace.getQuestionAnswerPair());
	}

	public void testGetQuestionsFromWinningPlaceFromGame() throws Throwable {
		Place winningPlace = theBoard.getPlaceById(13);
		assertEquals("Winning1=Answer1", winningPlace.getQuestionAnswerPair());
		assertEquals("Winning2=Answer2", winningPlace.getQuestionAnswerPair());
		assertEquals("Winning3=Answer3", winningPlace.getQuestionAnswerPair());
	}

	public void testGetQuestionFromFirstPlace() {
		Place firstPlace = theBoard.getFirstPlace();
		assertEquals("Pop1=Answer1", firstPlace.getQuestionAnswerPair());
	}

	public void testGetCategoryFromLastPlace() {
		Place lastPlace = theBoard.getLastPlace();
		assertEquals("Rock1=Answer1", lastPlace.getQuestionAnswerPair());
	}

	public void testAllPopPlacesOfCategoryHaveSameSetOfQuestions()
			throws Throwable {
		Place popPlace1 = theBoard.getPlaceById(0);
		Place popPlace2 = theBoard.getPlaceById(4);
		// useful in their own right? :
		assertTrue(popPlace1.isPop());
		assertTrue(popPlace2.isPop());
		assertEquals("Pop1=Answer1", popPlace1.getQuestionAnswerPair());
		assertEquals("Pop2=Answer2", popPlace2.getQuestionAnswerPair());
	}

	public void testAllSportsPlacesOfCategoryHaveSameSetOfQuestions()
			throws Throwable {
		Place sportsPlace1 = theBoard.getPlaceById(2);
		Place sportsPlace2 = theBoard.getPlaceById(6);
		// useful in their own right? :
		assertTrue(sportsPlace1.isSports());
		assertTrue(sportsPlace2.isSports());

		assertEquals("Sports1=Answer1", sportsPlace1.getQuestionAnswerPair());
		assertEquals("Sports2=Answer2", sportsPlace2.getQuestionAnswerPair());
	}
}