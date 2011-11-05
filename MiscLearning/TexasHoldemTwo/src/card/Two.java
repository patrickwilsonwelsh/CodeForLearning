package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Two extends Card {
	public Two(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getPocketAction();
	}

	public CardRank rank() {
		return CardRank.Deuce;
	}
}
