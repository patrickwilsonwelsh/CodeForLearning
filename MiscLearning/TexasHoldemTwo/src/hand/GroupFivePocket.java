package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupFivePocket extends Pocket {
	public GroupFivePocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.Five;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupFiveAction();
	}
}
