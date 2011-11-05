package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Ten extends Card {
	public Ten(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getTenCardAction();
	}

	public CardRank rank() {
		return CardRank.Ten;
	}
}
