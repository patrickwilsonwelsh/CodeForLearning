package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public abstract class Card implements ICard, Comparable {
	public static final Card AceOfDiamonds = new Ace(Suit.Diamonds);
	public static final Card AceOfHearts = new Ace(Suit.Hearts);
	public static final Card AceOfSpades = new Ace(Suit.Spades);
	public static final Card AceOfClubs = new Ace(Suit.Clubs);
	public static final Card KingOfDiamonds = new King(Suit.Diamonds);
	public static final Card KingOfHearts = new King(Suit.Hearts);
	public static final Card KingOfSpades = new King(Suit.Spades);
	public static final Card KingOfClubs = new King(Suit.Clubs);
	public static final Card QueenOfDiamonds = new Queen(Suit.Diamonds);
	public static final Card QueenOfHearts = new Queen(Suit.Hearts);
	public static final Card QueenOfSpades = new Queen(Suit.Spades);
	public static final Card QueenOfClubs = new Queen(Suit.Clubs);
	public static final Card JackOfDiamonds = new Jack(Suit.Diamonds);
	public static final Card JackOfHearts = new Jack(Suit.Hearts);
	public static final Card JackOfSpades = new Jack(Suit.Spades);
	public static final Card JackOfClubs = new Jack(Suit.Clubs);
	public static final Card TenOfDiamonds = new Ten(Suit.Diamonds);
	public static final Card TenOfHearts = new Ten(Suit.Hearts);
	public static final Card TenOfSpades = new Ten(Suit.Spades);
	public static final Card TenOfClubs = new Ten(Suit.Clubs);
	public static final Card NineOfDiamonds = new Nine(Suit.Diamonds);
	public static final Card NineOfHearts = new Nine(Suit.Hearts);
	public static final Card NineOfSpades = new Nine(Suit.Spades);
	public static final Card NineOfClubs = new Nine(Suit.Clubs);
	public static final Card EightOfDiamonds = new Eight(Suit.Diamonds);
	public static final Card EightOfHearts = new Eight(Suit.Hearts);
	public static final Card EightOfSpades = new Eight(Suit.Spades);
	public static final Card EightOfClubs = new Eight(Suit.Clubs);
	public static final Card SevenOfDiamonds = new Seven(Suit.Diamonds);
	public static final Card SevenOfHearts = new Seven(Suit.Hearts);
	public static final Card SevenOfSpades = new Seven(Suit.Spades);
	public static final Card SevenOfClubs = new Seven(Suit.Clubs);
	public static final Card SixOfDiamonds = new Six(Suit.Diamonds);
	public static final Card SixOfHearts = new Six(Suit.Hearts);
	public static final Card SixOfSpades = new Six(Suit.Spades);
	public static final Card SixOfClubs = new Six(Suit.Clubs);
	public static final Card FiveOfDiamonds = new Five(Suit.Diamonds);
	public static final Card FiveOfHearts = new Five(Suit.Hearts);
	public static final Card FiveOfSpades = new Five(Suit.Spades);
	public static final Card FiveOfClubs = new Five(Suit.Clubs);
	public static final Card FourOfDiamonds = new Four(Suit.Diamonds);
	public static final Card FourOfHearts = new Four(Suit.Hearts);
	public static final Card FourOfSpades = new Four(Suit.Spades);
	public static final Card FourOfClubs = new Four(Suit.Clubs);
	public static final Card ThreeOfDiamonds = new Three(Suit.Diamonds);
	public static final Card ThreeOfHearts = new Three(Suit.Hearts);
	public static final Card ThreeOfSpades = new Three(Suit.Spades);
	public static final Card ThreeOfClubs = new Three(Suit.Clubs);
	public static final Card DeuceOfDiamonds = new Two(Suit.Diamonds);
	public static final Card DeuceOfHearts = new Two(Suit.Hearts);
	public static final Card DeuceOfSpades = new Two(Suit.Spades);
	public static final Card DeuceOfClubs = new Two(Suit.Clubs);

	protected Suit suit;

	protected CardRank rankName;

	public Card(Suit suit) {
		this.suit = suit;
	}

	public abstract BettingAction getSingleCardAction(IPlayer player);

	public abstract CardRank rank();

	public Suit suit() {
		return suit;
	}

	public boolean equals(Object otherCard) {
		if (otherCard == null)
			return false;
		return compareTo(otherCard) == 0; // compareTo() implements the
		// full contract
	}

	public int compareTo(Object otherCard) {
		if (otherCard == null)
			throw new NullPointerException();
		if (otherCard == this)
			return 0;

		try {
			Card that = (Card) otherCard;
			int difference = this.rankNumber() - that.rankNumber();
			if (difference > 0)
				return 1;
			if (difference == 0)
				return 0;
			return -1;
		} catch (ClassCastException e) {
			return -1;
		}
	}

	public int rankNumber() {
		return rank().ordinal() + 2;
	}

}
