package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Five extends Card {

	public Five(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getFiveCardAction();
	}

	public CardRank rank() {
		return CardRank.Five;
	}

}
