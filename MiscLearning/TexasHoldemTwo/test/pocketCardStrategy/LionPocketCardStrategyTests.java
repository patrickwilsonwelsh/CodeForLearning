package pocketCardStrategy;

import junit.framework.TestCase;
import player.Lion;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public class LionPocketCardStrategyTests extends TestCase {
	private Player lion;

	protected void setUp() throws Exception {
		super.setUp();
		lion = new Lion();
	}

	public void testStrategyForGroupOnePockets() throws Exception {
		lion.dealtCard(Card.AceOfClubs);
		lion.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetThirtyBucks, lion.getAction());
	}

	public void testStrategyForGroupTwoPockets() throws Exception {
		lion.dealtCard(Card.AceOfClubs);
		lion.dealtCard(Card.KingOfDiamonds);
		assertEquals(BettingAction.BetThirtyBucks, lion.getAction());
	}

	public void testStrategyForGroupThreePockets() throws Exception {
		lion.dealtCard(Card.AceOfDiamonds);
		lion.dealtCard(Card.QueenOfSpades);
		assertEquals(BettingAction.Check, lion.getAction());
	}

	public void testStrategyForGroupFourPockets() throws Exception {
		lion.dealtCard(Card.AceOfDiamonds);
		lion.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Check, lion.getAction());
	}

	public void testStrategyForGroupFivePockets() throws Exception {
		lion.dealtCard(Card.TenOfSpades);
		lion.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Check, lion.getAction());
	}

	public void testStrategyForGroupSixPockets() throws Exception {
		lion.dealtCard(Card.NineOfDiamonds);
		lion.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Fold, lion.getAction());
	}

	public void testStrategyForGroupSevenPockets() throws Exception {
		lion.dealtCard(Card.NineOfDiamonds);
		lion.dealtCard(Card.AceOfSpades);
		assertEquals(BettingAction.Fold, lion.getAction());
	}

	public void testStrategyForGroupNonePockets() throws Exception {
		lion.dealtCard(Card.DeuceOfHearts);
		lion.dealtCard(Card.ThreeOfSpades);
		assertEquals(BettingAction.Fold, lion.getAction());
	}

}
