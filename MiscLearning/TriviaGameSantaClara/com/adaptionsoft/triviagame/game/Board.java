package com.adaptionsoft.triviagame.game;

import java.util.LinkedList;
import java.util.List;

public class Board {
    List<Place> places;

    public Board() {
	places = new LinkedList<Place>();
	places.add(new Place(0, Category.CategoryNames.POP));
	places.add(new Place(1, Category.CategoryNames.SCIENCE));
	places.add(new Place(2, Category.CategoryNames.SPORTS));
	places.add(new Place(3, Category.CategoryNames.ROCK));
	places.add(new Place(4, Category.CategoryNames.POP));
	places.add(new Place(5, Category.CategoryNames.SCIENCE));
	places.add(new Place(6, Category.CategoryNames.SPORTS));
	places.add(new Place(7, Category.CategoryNames.ROCK));
	places.add(new Place(8, Category.CategoryNames.POP));
	places.add(new Place(9, Category.CategoryNames.SCIENCE));
	places.add(new Place(10, Category.CategoryNames.SPORTS));
	places.add(new Place(11, Category.CategoryNames.ROCK));
    }

    public Place getFirstPlace() {
	return places.get(0);
    }

    public Place getPlaceAfter(Place place) {
	int number = place.getNumber();
	if (number == 11)
	    return places.get(0);
	return places.get((number + 1));
    }

    public Place getPlaceForNumber(int placeNumber) {
	return places.get(placeNumber);
    }
}
