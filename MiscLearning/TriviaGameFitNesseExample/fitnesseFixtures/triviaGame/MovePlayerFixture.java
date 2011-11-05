package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.ColumnFixture;

public class MovePlayerFixture extends ColumnFixture {
	public String playerName;

	public int placeNumber;

	private Game theGame;

	public boolean moveToPlace() {
		theGame = StaticGame.getInstance();
		Player player = theGame.getPlayerNamed(playerName);
		Place place = theGame.getBoard().getPlaceById(placeNumber);
		player.setPlace(place);
		return true;
	}

	public Place CurrentPlayerPlace() {
		theGame = StaticGame.getInstance();
		Player player = theGame.getPlayerNamed(playerName);
		Place currentPlace = player.getPlace();
		return currentPlace;
	}

	public boolean moveToNextPlace() {
		theGame = StaticGame.getInstance();
		Player player = theGame.getPlayerNamed(playerName);
		Board theBoard = theGame.getBoard();
		Place currentPlace = player.getPlace();
		player.setPlace(theBoard.getNextPlaceForPlace(currentPlace));
		return true;
	}
}
