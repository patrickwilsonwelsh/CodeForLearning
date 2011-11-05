package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fitnesse.fixtures.TableFixture;

public class BoardPlayerFixture extends TableFixture {
	public Place place;

	private Game theGame;

	protected void doStaticTable(int rows) {
		theGame = StaticGame.getInstance();

		checkFirstRowPlayers();
		checkSecondRowPlayers();
		checkPenaltyBoxOccupant();
		checkWinningPlaceOccupant();
	}

	private void checkFirstRowPlayers() {
		for (int i = 0; i < 6; i++) {
			String expected = getText(1, i);
			String actual = theGame.getPlayersForPlace(i);

			if (blank(1, i))
				ignore(1, i);
			else if (expected.equals(actual))
				right(1, i);
			else
				wrong(1, i, "" + actual);
		}
	}

	private void checkSecondRowPlayers() {
		for (int i = 0; i < 6; i++) {
			String expected = getText(3, i);
			String actual = theGame.getPlayersForPlace(11 - i);

			if (blank(3, i))
				ignore(3, i);
			else if (expected.equals(actual))
				right(3, i);
			else
				wrong(3, i, "" + actual);
		}
	}

	private void checkPenaltyBoxOccupant() {
		if (blank(5, 0)) {
			ignore(5, 0);
			return;
		}
		String expectedPenaltyBoxOccupantName = getText(5, 0);
		Player expectedPenaltyBoxOccupant = theGame
				.getPlayerNamed(expectedPenaltyBoxOccupantName);
		boolean personIsInPenaltyBox = (theGame.getBoard()
				.inPenaltyBox(expectedPenaltyBoxOccupant));
		if (personIsInPenaltyBox)
			right(5, 0);
		else
			wrong(5, 0);
	}

	private void checkWinningPlaceOccupant() {
		if (blank(5, 1)) {
			ignore(5, 1);
			return;
		}

		String expectedWinningPlaceOccupantName = getText(5, 1);
		Player expectedWinningPlaceOccupant = theGame
				.getPlayerNamed(expectedWinningPlaceOccupantName);
		boolean personIsInWinningPlace = (theGame.getBoard()
				.playerInWinningPlace(expectedWinningPlaceOccupant));
		if (personIsInWinningPlace)
			right(5, 1);
		else
			wrong(5, 1);
	}
}
