package com.adaptionsoft.triviagame.game;

public class Player {
    private String name;

    private Place place;

    private Game game;

    public Player(String name, Game game) {
	this.name = name;
	this.game = game;
	this.place = game.getFirstPlace();
    }

    public String getName() {
	return name;
    }

    public boolean equals(Object otherPlayer) {
	if (otherPlayer instanceof Player) {
	    return this.getName().equals(((Player) otherPlayer).getName());
	}
	return false;
    }

    public int getPlaceNumber() {
	return place.getNumber();
    }

    public void advanceOnePlace() {
	place = game.getPlaceAfter(place);
    }

    public void moveToPlaceNumber(int placeNumber) {
	place = game.getPlaceNumber(placeNumber);

    }
}
