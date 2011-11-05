package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.RowFixture;

public class PlayerRowFixture extends RowFixture {
	private Game theGame;

	public Object[] query() throws Exception {
		theGame = StaticGame.getInstance();
		return theGame.getAllPlayers();
	}

	public Class getTargetClass() {
		return Player.class;
	}
}