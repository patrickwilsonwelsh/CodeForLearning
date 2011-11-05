package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupNonePocket extends Pocket {
	public GroupNonePocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.None;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupNoneAction();
	}
}
