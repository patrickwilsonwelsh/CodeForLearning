package pocketCardStrategy;

import junit.framework.TestCase;
import player.Maniac;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public class ManiacPocketCardStrategyTests extends TestCase {
	private Player maniac;

	protected void setUp() throws Exception {
		super.setUp();
		maniac = new Maniac();
	}

	public void testStrategyForGroupOnePockets() throws Exception {
		maniac.dealtCard(Card.AceOfClubs);
		maniac.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetAHundredBucks, maniac.getAction());
	}

	public void testStrategyForGroupTwoPockets() throws Exception {
		maniac.dealtCard(Card.AceOfClubs);
		maniac.dealtCard(Card.KingOfDiamonds);
		assertEquals(BettingAction.BetAHundredBucks, maniac.getAction());
	}

	public void testStrategyForGroupThreePockets() throws Exception {
		maniac.dealtCard(Card.AceOfDiamonds);
		maniac.dealtCard(Card.QueenOfSpades);
		assertEquals(BettingAction.BetFiftyBucks, maniac.getAction());
	}

	public void testStrategyForGroupFourPockets() throws Exception {
		maniac.dealtCard(Card.AceOfDiamonds);
		maniac.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.BetFiftyBucks, maniac.getAction());
	}

	public void testStrategyForGroupFivePockets() throws Exception {
		maniac.dealtCard(Card.TenOfDiamonds);
		maniac.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.BetFiftyBucks, maniac.getAction());
	}

	public void testStrategyForGroupSixPockets() throws Exception {
		maniac.dealtCard(Card.NineOfDiamonds);
		maniac.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.BetTenBucks, maniac.getAction());
	}

	public void testStrategyForGroupSevenPockets() throws Exception {
		maniac.dealtCard(Card.NineOfDiamonds);
		maniac.dealtCard(Card.AceOfSpades);
		assertEquals(BettingAction.BetTenBucks, maniac.getAction());
	}

	public void testStrategyForGroupNonePockets() throws Exception {
		maniac.dealtCard(Card.DeuceOfClubs);
		maniac.dealtCard(Card.ThreeOfSpades);
		assertEquals(BettingAction.Check, maniac.getAction());
	}

}
