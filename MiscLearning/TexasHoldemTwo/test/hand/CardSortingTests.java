package hand;

import hand.Hand;
import hand.Pocket.PocketType;
import junit.framework.TestCase;
import card.Card;

public class CardSortingTests extends TestCase {
	public void testAceSortsFirst() throws Exception {
		Hand hand = new Hand();
		hand.add(Card.DeuceOfHearts);
		hand = hand.add(Card.AceOfDiamonds);

		assertEquals(PocketType.AceDeuce, hand.getPocketName());
	}

	public void testJackSortsBehindQueen() throws Exception {
		Hand hand = new Hand();
		hand = hand.add(Card.JackOfHearts);
		hand = hand.add(Card.QueenOfDiamonds);

		assertEquals(PocketType.QueenJack, hand.getPocketName());
	}

	public void testKingSortsAheadOfJack() throws Exception {
		Hand hand = new Hand();
		hand = hand.add(Card.JackOfHearts);
		hand = hand.add(Card.KingOfDiamonds);

		assertEquals(PocketType.KingJack, hand.getPocketName());
	}
}
