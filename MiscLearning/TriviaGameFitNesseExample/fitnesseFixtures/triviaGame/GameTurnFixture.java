package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.ColumnFixture;

public class GameTurnFixture extends ColumnFixture {
	public int roll;

	public boolean rightAnswer = true;

	private Game theGame;

	private TurnResult turnResult;

	private Player player;

	public boolean didThePlayerTakeATurn() throws Throwable {
		theGame = StaticGame.getInstance();
		turnResult = theGame.takeTurn(roll, rightAnswer);
		return (turnResult != null);
	}

	public Player whichPlayerWasIt() throws Throwable {
		player = turnResult.player;
		return player;
	}

	public boolean gameHasStarted() {
		theGame = StaticGame.getInstance();
		return theGame.gameHasStarted();
	}

	public Place whichPlaceDidTheyEndUpOn() {
		theGame = StaticGame.getInstance();
		player = turnResult.player;
		return theGame.getBoard().getPlaceForPlayer(player);
	}

	public Category lastQuestionCategory() {
		return turnResult.lastCategory;
	}
}