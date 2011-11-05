package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
import fit.ColumnFixture;

public class AddRemovePlayerFixture extends ColumnFixture {
	public String playerName;

	private Game theGame;
	private Player thePlayer;

	public boolean wasThePlayerAddedOK() {
		theGame = StaticGame.getInstance();
		thePlayer = theGame.addPlayer(playerName);
		return theGame.playerIsPlaying(thePlayer);
	}

	public boolean removePlayer() {
		theGame = StaticGame.getInstance();
		thePlayer = theGame.getPlayerNamed(playerName);
		theGame.removePlayer(thePlayer);
		return (playerWasRemoved(thePlayer));
	}

	public boolean playable() {
		theGame = StaticGame.getInstance();
		return (theGame.isPlayable());
	}

	public int howManyPlayersArePlaying() {
		theGame = StaticGame.getInstance();
		return theGame.getNumberOfPlayers();
	}

	private boolean playerWasRemoved(Player aPlayer) {
		theGame = StaticGame.getInstance();
		return (!theGame.playerIsPlaying(aPlayer));
	}

	// TODO: Deprecate this fixture method in the test tables...this is where
	// ZiBreve and an Eclipse plugin would be ideal...
	public boolean addPlayer() {
		return wasThePlayerAddedOK();
	}

	// TODO: Deprecate this fixture method in the test tables...
	public int countPlayers() {
		return howManyPlayersArePlaying();
	}
}