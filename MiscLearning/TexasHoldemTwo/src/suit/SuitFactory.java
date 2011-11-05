package suit;

public class SuitFactory {
	public enum Suit {
		Hearts, Spades, Clubs, Diamonds
	}

	public static Suit getSuitForString(String suitString) {
		if (suitString.equals("s"))
			return Suit.Spades;
		if (suitString.equals("c"))
			return Suit.Clubs;
		if (suitString.equals("d"))
			return Suit.Diamonds;
		if (suitString.equals("h"))
			return Suit.Hearts;

		// REFACTOR Blow up loudly here with exception?
		return null;
	}
}
