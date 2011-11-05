package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.ColumnFixture;

public class BoardPlaceFixture extends ColumnFixture {
	public int placeNumber;

	private Place place;

	private Game theGame;

	private Board theBoard;

	public String players() {
		loadGameState();
		return theGame.getPlayersForPlace(placeNumber);
	}

	public Place nextPlace() {
		loadGameState();
		place = theBoard.getPlaceById(placeNumber);
		return theBoard.getNextPlaceForPlace(place);
	}

	public Category category() {
		loadGameState();
		place = theBoard.getPlaceById(placeNumber);
		return place.getCategory();
	}

	private void loadGameState() {
		theGame = StaticGame.getInstance();
		theBoard = theGame.getBoard();
	}
}
