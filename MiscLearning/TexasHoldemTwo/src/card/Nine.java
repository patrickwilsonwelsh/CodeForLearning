package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Nine extends Card {
	public Nine(Suit suit) {
		super(suit);
	}

	@Override
	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getNineCardAction();
	}

	@Override
	public CardRank rank() {
		return CardRank.Nine;
	}

	public int rankNumber() {
		return 9;
	}

}
