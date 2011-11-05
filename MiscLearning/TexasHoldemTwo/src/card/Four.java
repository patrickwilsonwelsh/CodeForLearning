package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Four extends Card {
	public Four(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getFourCardAction();
	}

	public CardRank rank() {
		return CardRank.Four;
	}

}
