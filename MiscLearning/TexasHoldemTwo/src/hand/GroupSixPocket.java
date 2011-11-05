package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupSixPocket extends Pocket {
	public GroupSixPocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.Six;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupSixAction();
	}

}
