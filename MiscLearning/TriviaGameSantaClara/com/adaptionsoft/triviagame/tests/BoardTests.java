package com.adaptionsoft.triviagame.tests;

import junit.framework.TestCase;

import com.adaptionsoft.triviagame.game.Board;
import com.adaptionsoft.triviagame.game.Game;
import com.adaptionsoft.triviagame.game.Player;

public class BoardTests extends TestCase {
    private Board board;

    private Game game;

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	game = new Game();
	board = game.getBoard();
    }

    public void testGameHasBoard() throws Exception {
	assertNotNull(board);
	assertEquals("com.adaptionsoft.triviagame.game.Board", board.getClass()
		.getName());
    }

    public void testGetFirstPlace() throws Exception {
	assertEquals(0, board.getFirstPlace().getNumber());
    }

    public void testPlayerStartsOnFirstPlace() throws Exception {
	Player al = game.addPlayer("Al");
	assertEquals(0, al.getPlaceNumber());
    }

}
