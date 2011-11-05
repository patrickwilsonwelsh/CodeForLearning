package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Jack extends Card {

	public Jack(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getJackAction();
	}

	public CardRank rank() {
		return CardRank.Jack;
	}

}
