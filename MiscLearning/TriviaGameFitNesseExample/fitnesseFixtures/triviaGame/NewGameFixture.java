package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.ColumnFixture;

public class NewGameFixture extends ColumnFixture {
	public boolean newGame() {
		return StaticGame.newGame();
	}
}