package pocketCardStrategy;

import junit.framework.TestCase;
import player.Player;
import player.Rock;
import bettingAction.BettingAction;
import card.Card;

public class RockPocketCardStrategyTests extends TestCase {
	private Player rock;

	protected void setUp() throws Exception {
		super.setUp();
		rock = new Rock();
	}

	public void testStrategyForGroupOnePockets() throws Exception {
		rock.dealtCard(Card.AceOfClubs);
		rock.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetAHundredBucks, rock.getAction());
	}

	public void testStrategyForGroupTwoPockets() throws Exception {
		rock.dealtCard(Card.AceOfClubs);
		rock.dealtCard(Card.KingOfDiamonds);
		assertEquals(BettingAction.BetAHundredBucks, rock.getAction());
	}

	public void testStrategyForGroupThreePockets() throws Exception {
		rock.dealtCard(Card.AceOfDiamonds);
		rock.dealtCard(Card.QueenOfSpades);
		assertEquals(BettingAction.Fold, rock.getAction());
	}

	public void testStrategyForGroupFourPockets() throws Exception {
		rock.dealtCard(Card.AceOfDiamonds);
		rock.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Fold, rock.getAction());
	}

	public void testStrategyForGroupFivePockets() throws Exception {
		rock.dealtCard(Card.TenOfDiamonds);
		rock.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Fold, rock.getAction());
	}

	public void testStrategyForGroupSixPockets() throws Exception {
		rock.dealtCard(Card.NineOfDiamonds);
		rock.dealtCard(Card.JackOfSpades);
		assertEquals(BettingAction.Fold, rock.getAction());
	}

	public void testStrategyForGroupSevenPockets() throws Exception {
		rock.dealtCard(Card.NineOfDiamonds);
		rock.dealtCard(Card.AceOfSpades);
		assertEquals(BettingAction.Fold, rock.getAction());
	}

	public void testStrategyForGroupNonePockets() throws Exception {
		rock.dealtCard(Card.DeuceOfHearts);
		rock.dealtCard(Card.ThreeOfSpades);
		assertEquals(BettingAction.Fold, rock.getAction());
	}

}
