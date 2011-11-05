package com.adaptionsoft.triviagame.tests;

import junit.framework.TestCase;

import com.adaptionsoft.triviagame.game.Game;
import com.adaptionsoft.triviagame.game.Player;

public class PlayerMovementTests extends TestCase {
    private Game game;

    private Player al;

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	game = new Game();
	al = game.addPlayer("Al");
    }

    public void testPlayerCanMoveOnePlace() throws Exception {
	al.advanceOnePlace();
	assertEquals(1, al.getPlaceNumber());
    }

    public void testPlayerCanMoveAllTheWayAroundTheBoard() throws Exception {
	for (int i = 0; i < 15; i++) {
	    al.advanceOnePlace();
	}

	assertEquals(3, al.getPlaceNumber());
    }

    public void testMovePlayerToArbitraryPlaceOnBoard() throws Exception {
	al.moveToPlaceNumber(5);
	assertEquals(5, al.getPlaceNumber());
    }

}
