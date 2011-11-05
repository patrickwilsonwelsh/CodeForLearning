package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.ColumnFixture;

public class GoldCoinFixture extends ColumnFixture {
	public String playerName;

	private Game theGame;

	public int goldCoins() {
		theGame = StaticGame.getInstance();
		Player player = theGame.getPlayerNamed(playerName);
		return player.getGoldCoins();
	}
}