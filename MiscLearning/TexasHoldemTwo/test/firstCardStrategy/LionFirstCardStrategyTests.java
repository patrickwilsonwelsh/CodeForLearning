package firstCardStrategy;

import junit.framework.TestCase;
import player.Lion;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public class LionFirstCardStrategyTests extends TestCase {
	private Player lion;

	protected void setUp() throws Exception {
		super.setUp();
		lion = new Lion();
	}

	public void testLaserStrategyForAceOfDiamonds() throws Exception {
		lion.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetTenBucks, lion.getAction());
	}

	public void testLaserStrategyForTwoOfHearts() throws Exception {
		lion.dealtCard(Card.DeuceOfHearts);
		assertEquals(BettingAction.Check, lion.getAction());
	}

	public void testLaserStrategyForKingOfSpades() throws Exception {
		lion.dealtCard(Card.KingOfSpades);
		assertEquals(BettingAction.BetTenBucks, lion.getAction());
	}
}
