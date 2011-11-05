package card;

import junit.framework.TestCase;
import player.Fish;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;
import card.Card;

public class CardTests extends TestCase {
	private Card card;
	private Fish fish;

	protected void setUp() throws Exception {
		super.setUp();
		fish = new Fish();
	}

	public void testAceOfDiamonds() throws Exception {
		card = Card.AceOfDiamonds;
		assertEquals(CardRank.Ace, card.rank());
		assertEquals(14, card.rankNumber());
		assertEquals(Suit.Diamonds, card.suit());
		assertEquals(BettingAction.BetFiveBucks, card.getSingleCardAction(fish));
	}

	public void testKingOfSpades() throws Exception {
		card = Card.KingOfSpades;
		assertEquals(CardRank.King, card.rank());
		assertEquals(13, card.rankNumber());
		assertEquals(Suit.Spades, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testQueenOfHearts() throws Exception {
		card = Card.QueenOfHearts;
		assertEquals(CardRank.Queen, card.rank());
		assertEquals(12, card.rankNumber());
		assertEquals(Suit.Hearts, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testJackOfClubs() throws Exception {
		card = Card.JackOfClubs;
		assertEquals(CardRank.Jack, card.rank());
		assertEquals(11, card.rankNumber());
		assertEquals(Suit.Clubs, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testTenOfDiamonds() throws Exception {
		card = Card.TenOfDiamonds;
		assertEquals(CardRank.Ten, card.rank());
		assertEquals(10, card.rankNumber());
		assertEquals(Suit.Diamonds, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testNineOfSpades() throws Exception {
		card = Card.NineOfSpades;
		assertEquals(CardRank.Nine, card.rank());
		assertEquals(9, card.rankNumber());
		assertEquals(Suit.Spades, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testEightOfHearts() throws Exception {
		card = Card.EightOfHearts;
		assertEquals(CardRank.Eight, card.rank());
		assertEquals(8, card.rankNumber());
		assertEquals(Suit.Hearts, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testSevenOfClubs() throws Exception {
		card = Card.SevenOfClubs;
		assertEquals(CardRank.Seven, card.rank());
		assertEquals(7, card.rankNumber());
		assertEquals(Suit.Clubs, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testSixOfDiamonds() throws Exception {
		card = Card.SixOfDiamonds;
		assertEquals(CardRank.Six, card.rank());
		assertEquals(6, card.rankNumber());
		assertEquals(Suit.Diamonds, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testFiveOfSpades() throws Exception {
		card = Card.FiveOfSpades;
		assertEquals(CardRank.Five, card.rank());
		assertEquals(5, card.rankNumber());
		assertEquals(Suit.Spades, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testFourOfHearts() throws Exception {
		card = Card.FourOfHearts;
		assertEquals(CardRank.Four, card.rank());
		assertEquals(4, card.rankNumber());
		assertEquals(Suit.Hearts, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testThreeOfClubs() throws Exception {
		card = Card.ThreeOfClubs;
		assertEquals(CardRank.Three, card.rank());
		assertEquals(3, card.rankNumber());
		assertEquals(Suit.Clubs, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

	public void testTwoOfDiamonds() throws Exception {
		card = Card.DeuceOfDiamonds;
		assertEquals(CardRank.Deuce, card.rank());
		assertEquals(2, card.rankNumber());
		assertEquals(Suit.Diamonds, card.suit());
		assertEquals(BettingAction.Check, card.getSingleCardAction(fish));
	}

}
