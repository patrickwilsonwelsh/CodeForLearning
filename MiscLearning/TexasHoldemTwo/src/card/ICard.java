package card;

import player.IPlayer;
import rank.CardRank;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;

public interface ICard {
	int rankNumber();

	BettingAction getSingleCardAction(IPlayer player);

	CardRank rank();

	Suit suit();

}
