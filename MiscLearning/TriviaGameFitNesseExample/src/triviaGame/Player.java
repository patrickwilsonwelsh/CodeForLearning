package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class Player {
	private Place place;
	private Place placeBeforePenaltyBox;
	private int goldCoins;
	public int numberOfEvenPenaltyBoxRolls;
	public String name;

	private Player(String name, Place initialPlace) {
		this.name = name;
		this.place = initialPlace;
		this.placeBeforePenaltyBox = initialPlace;
		numberOfEvenPenaltyBoxRolls = 0;
		goldCoins = 0;
	}

	public int numberOfEvenPenaltyBoxRolls() {
		return numberOfEvenPenaltyBoxRolls;
	}

	public static Player parse(String name) {
		return newPlacelessPlayer(name);
	}

	public static Player newPlacelessPlayer(String name) {
		return new Player(name, null);
	}

	public static Player newPlayerWithPlace(String name, Place aPlace) {
		return new Player(name, aPlace);
	}

	public String getName() {
		return name;
	}

	public void setPlace(Place aPlace) {
		placeBeforePenaltyBox = place;
		place = aPlace;
	}

	public Place getPlace() {
		return place;
	}

	public void addGoldCoin() {
		goldCoins++;
	}

	public int getGoldCoins() {
		return goldCoins;
	}

	public Place getPlaceBeforePenaltyBox() {
		return placeBeforePenaltyBox;
	}

	public boolean equals(Object obj) {
		Player otherPlayer = (Player) obj;
		return (this.name.equals(otherPlayer.name));
	}

	public int hashCode() {
		return name.hashCode();
	}

	public String toString() {
		return name;
	}
}