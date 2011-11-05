package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupSevenPocket extends Pocket {
	public GroupSevenPocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.Seven;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupSevenAction();
	}

}
