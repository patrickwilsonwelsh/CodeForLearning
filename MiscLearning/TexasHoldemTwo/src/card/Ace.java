package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Ace extends Card {
	public Ace(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getAceAction();
	}

	public CardRank rank() {
		return CardRank.Ace;
	}

}
