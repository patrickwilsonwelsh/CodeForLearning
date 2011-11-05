package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public class Queen extends Card {
	public Queen(Suit suit) {
		super(suit);
	}

	public BettingAction getSingleCardAction(IPlayer player) {
		return player.getQueenAction();
	}

	public CardRank rank() {
		return CardRank.Queen;
	}

}
