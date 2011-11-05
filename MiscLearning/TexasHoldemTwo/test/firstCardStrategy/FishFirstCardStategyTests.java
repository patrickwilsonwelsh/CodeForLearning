package firstCardStrategy;

import junit.framework.TestCase;
import player.Fish;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public class FishFirstCardStategyTests extends TestCase {
	private Player fish;

	protected void setUp() throws Exception {
		super.setUp();
		fish = new Fish();
	}

	public void testFishStrategyForAceOfDiamonds() throws Exception {
		fish.dealtCard(Card.AceOfClubs);
		assertEquals(BettingAction.BetFiveBucks, fish.getAction());
	}

	public void testFishStrategyForTwoOfHearts() throws Exception {
		fish.dealtCard(Card.DeuceOfHearts);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testFishStrategyForKingOfSpades() throws Exception {
		fish.dealtCard(Card.KingOfClubs);
		assertEquals(BettingAction.Check, fish.getAction());
	}
}
