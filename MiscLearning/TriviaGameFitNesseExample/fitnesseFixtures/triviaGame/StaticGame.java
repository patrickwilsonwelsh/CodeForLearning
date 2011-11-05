package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */

public abstract class StaticGame {
	private static Game theGame;

	private StaticGame() {
	}

	public static Game getInstance() {
		if (theGame == null)
			theGame = new Game();
		return theGame;
	}

	public static boolean newGame() {
		theGame = null;
		return (theGame == null);
	}
}