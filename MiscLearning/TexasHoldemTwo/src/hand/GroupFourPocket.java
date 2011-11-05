package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupFourPocket extends Pocket {
	public GroupFourPocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.Four;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupFourAction();
	}

}
