package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupThreePocket extends Pocket {
	public GroupThreePocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.Three;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupThreeAction();
	}

}
