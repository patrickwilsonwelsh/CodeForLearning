package player;

import bettingAction.BettingAction;
import card.Card;

public interface IPlayer {
	void dealtCard(Card card) throws Exception;

	BettingAction getAction() throws Exception;

	BettingAction getAceAction();

	BettingAction getKingAction();

	BettingAction getQueenAction();

	BettingAction getJackAction();

	BettingAction getTenCardAction();

	BettingAction getNineCardAction();

	BettingAction getEightCardAction();

	BettingAction getSevenCardAction();

	BettingAction getSixCardAction();

	BettingAction getFiveCardAction();

	BettingAction getFourCardAction();

	BettingAction getThreeCardAction();

	BettingAction getTwoCardAction();

	BettingAction getPocketAction();

	BettingAction getGroupOneAction();

	BettingAction getGroupTwoAction();

	BettingAction getGroupThreeAction();

	BettingAction getGroupFourAction();

	BettingAction getGroupFiveAction();

	BettingAction getGroupSixAction();

	BettingAction getGroupSevenAction();

	BettingAction getGroupNoneAction();
}
