package hand;

import hand.Hand;
import hand.PocketClassifier;
import hand.Pocket.PocketType;
import junit.framework.TestCase;
import card.Card;

//TODO: Backfill all pocketGroup classification tests
public class PocketCardClassificationTests extends TestCase {
	private Hand hand;

	protected void setUp() throws Exception {
		hand = new Hand();
		assertEquals(PocketClassifier.PocketGroup.None, hand.getPocketGroup());
	}

	public void testCreateSameSuitGroupFivePockets() throws Exception {
		hand = hand.add(Card.AceOfClubs);
		hand = hand.add(Card.DeuceOfClubs);
		assertEquals(PocketType.AceDeuceSame, hand.getPocketName());
		assertEquals(PocketClassifier.PocketGroup.Five, hand.getPocketGroup());
	}

	public void testGroupOnePocketClassification() throws Exception {
		hand = hand.add(Card.AceOfDiamonds);
		hand = hand.add(Card.KingOfClubs);

		assertEquals(PocketType.AceKing, hand.getPocketName());
		assertEquals(PocketClassifier.PocketGroup.Two, hand.getPocketGroup());
	}

	public void testGroupSevenPocketClassification() throws Exception {
		hand = hand.add(Card.DeuceOfHearts);
		hand = hand.add(Card.ThreeOfHearts);

		assertEquals(PocketType.ThreeDeuceSame, hand.getPocketName());
		assertEquals(PocketClassifier.PocketGroup.Seven, hand.getPocketGroup());

	}

	public void testGroupNonePocketClassification() throws Exception {
		hand = hand.add(Card.DeuceOfHearts);
		hand = hand.add(Card.ThreeOfSpades);

		assertEquals(PocketType.ThreeDeuce, hand.getPocketName());
		assertEquals(PocketClassifier.PocketGroup.None, hand.getPocketGroup());
	}

}
