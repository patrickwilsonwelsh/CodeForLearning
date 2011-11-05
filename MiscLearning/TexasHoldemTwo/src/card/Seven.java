package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Seven extends Card {
	public Seven(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getSevenCardAction();
	}

	public CardRank rank() {
		return CardRank.Seven;
	}

}
