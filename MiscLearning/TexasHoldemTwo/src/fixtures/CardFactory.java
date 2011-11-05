package fixtures;

import suit.SuitFactory;
import suit.SuitFactory.Suit;
import card.Ace;
import card.Card;
import card.Jack;
import card.King;
import card.Nine;
import card.Queen;
import card.Ten;
import card.Three;
import card.Two;

public class CardFactory {
	public Card cardForString(String cardString) {
		String rank = cardString.substring(0, 1);
		String suitString = cardString.substring(1);
		Suit suit = SuitFactory.getSuitForString(suitString);

		if (rank.equals("A"))
			return new Ace(suit);
		if (rank.equals("K"))
			return new King(suit);
		if (rank.equals("Q"))
			return new Queen(suit);
		if (rank.equals("J"))
			return new Jack(suit);
		if (rank.equals("T"))
			return new Ten(suit);
		if (rank.equals("9"))
			return new Nine(suit);

		// TODO Need to firstCardStrategy-drive these remaining ranks
		// if (rank.equals("8")) return new Eight(suit);
		// if (rank.equals("6")) return new Six(suit);
		// if (rank.equals("5")) return new Five(suit);
		// if (rank.equals("4")) return new Four(suit);
		if (rank.equals("3"))
			return new Three(suit);
		if (rank.equals("2"))
			return new Two(suit);

		return new Ace(suit);
	}

}
