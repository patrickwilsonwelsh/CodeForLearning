package com.adaptionsoft.triviagame.tests;

import junit.framework.TestCase;

import com.adaptionsoft.triviagame.game.Category;
import com.adaptionsoft.triviagame.game.Game;
import com.adaptionsoft.triviagame.game.Place;

public class PlacesTests extends TestCase {
    private Game game;

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	game = new Game();
    }

    public void testPlaceKnowsItsNumber() throws Exception {
	Place place = new Place(1, Category.CategoryNames.ROCK);
	assertEquals(1, place.getNumber());
    }

    public void testThirdPlaceKnowsItsCategory() throws Exception {
	Place thirdPlace = game.getPlaceNumber(2);
	assertEquals(Category.CategoryNames.SPORTS, thirdPlace
		.getCategoryName());
    }

    public void testFourthPlaceKnowsItsCategory() throws Exception {
	Place fourthPlace = game.getPlaceNumber(3);
	assertEquals(Category.CategoryNames.ROCK, fourthPlace.getCategoryName());
    }

}
