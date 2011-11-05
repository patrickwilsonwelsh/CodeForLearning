package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class King extends Card {
	public King(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getKingAction();
	}

	public CardRank rank() {
		return CardRank.King;
	}

}
