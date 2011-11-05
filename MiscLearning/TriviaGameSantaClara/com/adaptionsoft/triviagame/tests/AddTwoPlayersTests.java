package com.adaptionsoft.triviagame.tests;

import com.adaptionsoft.triviagame.game.Game;
import com.adaptionsoft.triviagame.game.Player;

import junit.framework.TestCase;

public class AddTwoPlayersTests extends TestCase {
    private Game game;

    private Player al;

    private Player chet;

    @Override
    protected void setUp() throws Exception {
	game = new Game();
	al = game.addPlayer("Al");
    }

    public void testAddOnePlayer() throws Exception {
	assertEquals(1, game.numberOfPlayers());
	assertTrue(game.isPlaying(al));
	assertFalse(game.isPlayable());
    }

    public void testAddTwoPlayers() throws Exception {
	chet = game.addPlayer("Chet");

	assertEquals(2, game.numberOfPlayers());
	assertTrue(game.isPlayable());
	assertTrue(game.isPlaying(al));
	assertTrue(game.isPlaying(chet));

	Player Sam = new Player("Sam", game);
	assertFalse(game.isPlaying(Sam));
    }

    public void testCannotAddSamePlayerTwice() throws Exception {
	al = game.addPlayer("Al");

	assertEquals(1, game.numberOfPlayers());
    }
}
