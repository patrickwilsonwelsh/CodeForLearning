package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupOnePocket extends Pocket {
	public GroupOnePocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.One;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupOneAction();
	}

}
