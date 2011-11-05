package firstCardStrategy;

import junit.framework.TestCase;
import player.Player;
import player.Rock;
import bettingAction.BettingAction;
import card.Card;

//TODO Backfill a few more single-card action tests for different players
public class RockFirstCardStrategyTests extends TestCase {

	private Player rock;

	protected void setUp() throws Exception {
		super.setUp();
		rock = new Rock();
	}

	public void testRockStrategyForKingOfSpades() throws Exception {
		rock.dealtCard(Card.KingOfHearts);
		assertEquals(BettingAction.BetTwentyBucks, rock.getAction());
	}

	public void testRockStrategyForAceOfDiamonds() throws Exception {
		rock.dealtCard(Card.AceOfDiamonds);
		assertEquals(BettingAction.BetTwentyBucks, rock.getAction());
	}

	public void testRockStrategyForQueenOfDiamonds() throws Exception {
		rock.dealtCard(Card.QueenOfDiamonds);
		assertEquals(BettingAction.Check, rock.getAction());
	}

}
