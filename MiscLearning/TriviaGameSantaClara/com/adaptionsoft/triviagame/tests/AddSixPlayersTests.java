package com.adaptionsoft.triviagame.tests;

import junit.framework.TestCase;
import com.adaptionsoft.triviagame.game.Game;

public class AddSixPlayersTests extends TestCase {
    private Game game;

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	game = new Game();
	game.addPlayer("Al");
	game.addPlayer("Chet");
	game.addPlayer("Don");
	game.addPlayer("Ed");
	game.addPlayer("Fred");
	game.addPlayer("Ron");
    }

    public void testCanAddSixPlayers() throws Exception {
	assertEquals(6, game.numberOfPlayers());
    }

    public void testCannotAddSeventhPlayer() throws Exception {
	game.addPlayer("Seventh");
	assertEquals(6, game.numberOfPlayers());
    }

}
