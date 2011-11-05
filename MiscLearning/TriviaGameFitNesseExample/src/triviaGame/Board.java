package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {
	public static final int REGULAR_CATEGORIES = 4;
	public static final int MAX_REGULAR_PLACES = 12;
	public static final int MAX_PLACES = 14;
	public static final int FIRST_PLACE_INDEX = 0;
	public static final int LAST_PLACE_INDEX = 11;
	public static final int PENALTY_BOX_INDEX = 12;
	public static final int WINNING_PLACE_INDEX = 13;
	private Category lastCategory;
	private PlaceFactory placeMaker;
	private Place[] places;

	public Board() {
		initializePlaces();
	}

	public void initializePlaces() {
		placeMaker = new PlaceFactory();
		initializeRegularPlaces();
		initializePenaltyBox();
		initializeWinningPlace();
	}

	private void initializeWinningPlace() {
		places[WINNING_PLACE_INDEX] = initializePlace(Category.WINNING,
				WINNING_PLACE_INDEX);
	}

	private void initializePenaltyBox() {
		Place penaltyBox = new PenaltyBox();
		places[PENALTY_BOX_INDEX] = penaltyBox;
	}

	private void initializeRegularPlaces() {
		places = new Place[MAX_PLACES];

		for (int i = FIRST_PLACE_INDEX; i < MAX_PLACES; i++) {
			places[i] = initializePlace(getCategoryForPlace(i), i);
		}
	}

	public static Category getCategoryForPlace(int placeId) {
		Category[] lookupTable = new Category[] { Category.POP,
				Category.SCIENCE, Category.SPORTS, Category.ROCK };

		return lookupTable[placeId % REGULAR_CATEGORIES];
	}

	private Place initializePlace(Category category, int placeId) {
		return placeMaker.makePlace(category, placeId);
	}

	// --

	public Place getPlaceById(int placeId) {
		if ((placeId < FIRST_PLACE_INDEX) || (placeId > MAX_PLACES))
			throw new RuntimeException(MessageFormat.format(
					"Place {0} is not on this board",
					new Object[] { new Integer(placeId) }));

		return places[placeId];
	}

	public int getMaxNumberOfPlaces() {
		return MAX_PLACES;
	}

	public int getTotalNumberofPlaces() {
		return places.length;
	}

	public Place getFirstPlace() {
		return (Place) places[FIRST_PLACE_INDEX];
	}

	public Place getLastPlace() {
		return (Place) places[LAST_PLACE_INDEX];
	}

	public Place getPenaltyBox() {
		return (Place) places[PENALTY_BOX_INDEX];
	}

	public Place getWinningPlace() {
		return (Place) places[WINNING_PLACE_INDEX];
	}

	public Place getNextPlaceForPlace(Place currentPlace) {
		return getPlaceById(computeNextPlaceIdFor(currentPlace));
	}

	private int computeNextPlaceIdFor(Place currentPlace) {
		return (currentPlace.getId() + 1) % MAX_REGULAR_PLACES;
	}

	public Place getPlaceForPlayer(Player aPlayer) {
		return aPlayer.getPlace();
	}

	public String getPlayersForPlace(ArrayList allPlayers, int aPlaceId) {
		if (noPlayerFound(allPlayers, aPlaceId))
			return "";
		return buildPlayerList(allPlayers, aPlaceId);
	}

	private boolean noPlayerFound(ArrayList allPlayers, int aPlaceId) {
		return buildPlayerList(allPlayers, aPlaceId).length() == 0;
	}

	private String buildPlayerList(ArrayList allPlayers, int aPlaceId) {
		String playerList = "";
		Iterator allPlayerIt = allPlayers.iterator();
		while (allPlayerIt.hasNext()) {
			playerList = buildUpPlayerList((Player) allPlayerIt.next(),
					aPlaceId, playerList);
		}
		return playerList;
	}

	private String buildUpPlayerList(Player player, int aPlaceId,
			String playerList) {
		if (playerIsOn(player, aPlaceId)) {
			if (playerList.length() > 0)
				playerList += ", ";
			playerList += player.getName();
		}

		return playerList;
	}

	private boolean playerIsOn(Player player, int aPlaceId) {
		return player.getPlace().getId() == aPlaceId;
	}

	public void movePlayer(TurnResult turnResult) {
		for (int i = 0; i < turnResult.roll; i++) {
			advancePlayer(turnResult.player);
		}
		lastCategory = turnResult.player.getPlace().getCategory();
	}

	public void advancePlayer(Player aPlayer) {
		aPlayer.setPlace(getNextPlaceForPlayer(aPlayer));
	}

	private Place getNextPlaceForPlayer(Player aPlayer) {
		return getNextPlaceForPlace(aPlayer.getPlace());
	}

	public Category getCategoryNameOfLastQuestion() {
		return lastCategory;
	}

	public void returnToPlaceBeforePenaltyBox(Player luckyPlayer) {
		Place newPlace = luckyPlayer.getPlaceBeforePenaltyBox();
		luckyPlayer.setPlace(newPlace);
		luckyPlayer.numberOfEvenPenaltyBoxRolls = 0;
	}

	public void signalPenaltyBoxPlayerRolled(Player player) {
		player.numberOfEvenPenaltyBoxRolls++;
		if (playerHasRolledEvenThrice(player)) {
			returnToPlaceBeforePenaltyBox(player);
		}
	}

	public boolean playerHasRolledEvenThrice(Player player) {
		return player.numberOfEvenPenaltyBoxRolls >= 3;
	}

	public boolean inPenaltyBox(Player currentPlayer) {
		return currentPlayer.getPlace() == places[PENALTY_BOX_INDEX];
	}

	public void putPlayerInPenaltyBox(Player currentPlayer) {
		currentPlayer.numberOfEvenPenaltyBoxRolls = 0;
		currentPlayer.setPlace(places[PENALTY_BOX_INDEX]);
	}

	public boolean playerInWinningPlace(Player currentPlayer) {
		return (currentPlayer.getPlace() == (Place) places[WINNING_PLACE_INDEX]);
	}

	public void putPlayerInWinningPlace(Player currentPlayer) {
		currentPlayer.setPlace((WinningPlace) places[WINNING_PLACE_INDEX]);
	}
}
