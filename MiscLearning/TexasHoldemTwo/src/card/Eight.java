package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Eight extends Card {
	public Eight(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getEightCardAction();
	}

	@Override
	public CardRank rank() {
		return CardRank.Eight;
	}
}
