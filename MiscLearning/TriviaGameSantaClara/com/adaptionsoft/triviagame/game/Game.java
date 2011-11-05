package com.adaptionsoft.triviagame.game;

import java.util.ArrayList;

public class Game {
    private Board board;
    int numPlayers = 0;
    ArrayList<Player> players;

    public Game() {
	players = new ArrayList<Player>();
	board = new Board();
    }

    public int numberOfPlayers() {
	return players.size();
    }

    public boolean isPlayable() {
	return (numberOfPlayers() > 1);
    }

    public Player addPlayer(String name) {
	if (numberOfPlayers() == 6)
	    return null;
	if (isPlaying(new Player(name, this)))
	    return null;
	Player player = new Player(name, this);
	players.add(player);
	return player;
    }

    public boolean isPlaying(Player player) {
	return players.contains(player);
    }

    public Board getBoard() {
	return board;
    }

    public Place getFirstPlace() {
	return board.getFirstPlace();
    }

    public Place getPlaceAfter(Place place) {
	return board.getPlaceAfter(place);
    }

    public Place getPlaceNumber(int placeNumber) {
	return board.getPlaceForNumber(placeNumber);
    }
}
