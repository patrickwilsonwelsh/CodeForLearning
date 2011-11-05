package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Three extends Card {
	public Three(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getGroupThreeAction();
	}

	public CardRank rank() {
		return CardRank.Three;
	}

}
