package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Six extends Card {

	public Six(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getSixCardAction();
	}

	public CardRank rank() {
		return CardRank.Six;
	}

}
