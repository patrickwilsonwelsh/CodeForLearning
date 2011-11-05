package firstCardStrategy;

import junit.framework.TestCase;
import player.Maniac;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public class ManiacFirstCardStrategyTests extends TestCase {
	private Player maniac;

	protected void setUp() throws Exception {
		super.setUp();
		maniac = new Maniac();
	}

	public void testManiacStrategyForAceOfDiamonds() throws Exception {
		maniac.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetTwentyBucks, maniac.getAction());
	}

	public void testManiacStrategyForKingOfSpades() throws Exception {
		maniac.dealtCard(Card.QueenOfDiamonds);
		assertEquals(BettingAction.BetTwentyBucks, maniac.getAction());
	}

}
