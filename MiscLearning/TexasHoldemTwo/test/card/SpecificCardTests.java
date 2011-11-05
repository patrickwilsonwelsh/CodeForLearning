package card;

import junit.framework.TestCase;
import rank.CardRank;
import suit.SuitFactory.Suit;
import card.Card;

//TODO Add several more regression tests...
public class SpecificCardTests extends TestCase {
	public void testKingOfSpades() throws Exception {
		assertEquals(CardRank.King, Card.KingOfSpades.rank());
		assertEquals(Suit.Spades, Card.KingOfSpades.suit());
	}

	public void testTwoOfClubs() throws Exception {
		assertEquals(CardRank.Deuce, Card.DeuceOfClubs.rank());
		assertEquals(Suit.Clubs, Card.DeuceOfClubs.suit());
	}

}
