package com.adaptionsoft.triviagame.tests;

import com.adaptionsoft.triviagame.game.Game;

import junit.framework.TestCase;

public class NewGameTests extends TestCase {
    public void testNewGameHasNoPlayers() throws Exception {
	Game game = new Game();
	assertEquals(0, game.numberOfPlayers());
    }

    public void testNewGameIsNotPlayable() throws Exception {
	Game game = new Game();
	assertFalse(game.isPlayable());
    }
}
