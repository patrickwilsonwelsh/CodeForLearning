package com.adaptionsoft.triviagame.tests;

import junit.framework.TestCase;

import com.adaptionsoft.triviagame.game.Game;
import com.adaptionsoft.triviagame.game.Player;

public class PlayerTests extends TestCase {

    public void testPlayerKnowsName() throws Exception {
	Player chet = new Player("chet", new Game());
	assertEquals("chet", chet.getName());
    }

}
