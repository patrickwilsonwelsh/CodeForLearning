package pocketCardStrategy;

import junit.framework.TestCase;
import player.Fish;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public class FishPocketCardStrategyTests extends TestCase {
	private Player fish;

	protected void setUp() throws Exception {
		super.setUp();
		fish = new Fish();
	}

	public void testStrategyForGroupOnePockets() throws Exception {
		fish.dealtCard(Card.AceOfClubs);
		fish.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetTenBucks, fish.getAction());
	}

	public void testStrategyForGroupTwoPockets() throws Exception {
		fish.dealtCard(Card.AceOfClubs);
		fish.dealtCard(Card.KingOfDiamonds);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testStrategyForGroupThreePockets() throws Exception {
		fish.dealtCard(Card.AceOfDiamonds);
		fish.dealtCard(Card.QueenOfSpades);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testStrategyForGroupFourPockets() throws Exception {
		fish.dealtCard(Card.AceOfDiamonds);
		fish.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testStrategyForGroupFivePockets() throws Exception {
		fish.dealtCard(Card.TenOfDiamonds);
		fish.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testStrategyForGroupSixPockets() throws Exception {
		fish.dealtCard(Card.NineOfDiamonds);
		fish.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testStrategyForGroupSevenPockets() throws Exception {
		fish.dealtCard(Card.NineOfDiamonds);
		fish.dealtCard(Card.AceOfSpades);
		assertEquals(BettingAction.Check, fish.getAction());
	}

	public void testStrategyForGroupNonePockets() throws Exception {
		fish.dealtCard(Card.DeuceOfHearts);
		fish.dealtCard(Card.ThreeOfSpades);
		assertEquals(BettingAction.Check, fish.getAction());
	}

}
