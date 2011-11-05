package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import junit.framework.TestCase;

public class TestBoard extends TestCase {
	private Board theBoard;

	private static int MAX_PLACES_FOR_TESTING = 14;

	private PlaceFactory placeMaker;

	public void setUp() {
		theBoard = new Board();
		placeMaker = new PlaceFactory();
	}

	public void testGetPlaceById() throws Throwable {
		Board theBoard = new Board();
		Place firstPlace = theBoard.getPlaceById(0);
		assertEquals(0, firstPlace.getId());
	}

	public void testGetFourthPlace() throws Throwable {
		Board theBoard = new Board();
		Place fourthPlace = theBoard.getPlaceById(3);
		assertEquals(3, fourthPlace.getId());
	}

	public void testGetFirstPlace() throws Throwable {
		Place firstPlace = theBoard.getFirstPlace();
		assertEquals(firstPlace, theBoard.getPlaceById(Board.FIRST_PLACE_INDEX));
	}

	public void testGetLastPlace() throws Throwable {
		Place lastPlace = theBoard.getLastPlace();
		assertEquals(lastPlace, theBoard.getPlaceById(Board.LAST_PLACE_INDEX));
		assertSame(lastPlace, theBoard.getPlaceById(Board.LAST_PLACE_INDEX));
	}

	public void testGetMaxNumberOfPlaces() {
		assertEquals(MAX_PLACES_FOR_TESTING, theBoard.getMaxNumberOfPlaces());
	}

	public void testCountActualNumberOfPlaces() {
		assertEquals(MAX_PLACES_FOR_TESTING, theBoard.getTotalNumberofPlaces());
	}

	public void testGetNextPlaceFromFirst() throws Throwable {
		Place firstPlace = theBoard.getFirstPlace();
		Place expectedPlace = theBoard
				.getPlaceById(Board.FIRST_PLACE_INDEX + 1);
		assertEquals(expectedPlace, theBoard.getNextPlaceForPlace(firstPlace));
	}

	public void testGetNextPlaceFromLast() throws Throwable {
		Place lastPlace = theBoard.getLastPlace();
		Place firstPlace = theBoard.getFirstPlace();
		assertEquals(firstPlace, theBoard.getNextPlaceForPlace(lastPlace));
	}

	public void testPlaceStringConversion() throws Throwable {
		String place = "12";
		int placeInt = Integer.parseInt(place);
		Place aPlace = theBoard.getPlaceById(placeInt);
		assertEquals(12, aPlace.getId());
	}

	public void testGetPopPlace() {
		Place popPlace = placeMaker.makePlace(Category.POP, 0);
		assertTrue(popPlace.isPop());
	}

	public void testGetRockPlace() {
		Place rockPlace = placeMaker.makePlace(Category.ROCK, 0);
		assertTrue(rockPlace.isRock());
		assertEquals("rock", rockPlace.getCategory().name);
	}

	public void testGetSportsPlace() {
		Place sportsPlace = placeMaker.makePlace(Category.SPORTS, 0);
		assertTrue(sportsPlace.isSports());
	}

	public void testGetSciencePlace() {
		Place sciencePlace = placeMaker.makePlace(Category.SCIENCE, 0);
		assertTrue(sciencePlace.isScience());
	}

	public void testPlaceIndexTooHigh() throws Exception {
		try {
			theBoard.getPlaceById(9999);
			fail("Should not allow too high a places index.");
		} catch (RuntimeException expected) {
			// successfully rejected too high index
		}
	}

	public void testPlaceIndexTooLow() throws Throwable {
		try {
			theBoard.getPlaceById(-9999);
			fail("Should not allow too low a places index.");
		} catch (RuntimeException expected) {
			// successfully rejected too low index
		}
	}

}
