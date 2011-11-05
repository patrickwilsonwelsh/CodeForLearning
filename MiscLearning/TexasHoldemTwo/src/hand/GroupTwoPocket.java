package hand;

import player.Player;
import bettingAction.BettingAction;

public class GroupTwoPocket extends Pocket {
	public GroupTwoPocket(Hand hand) throws Exception {
		super();
		pocketName = hand.pocketName;
		cards = hand.cards;
		pocketGroup = PocketClassifier.PocketGroup.Two;
	}

	public BettingAction getAction(Player player) {
		return player.getGroupTwoAction();
	}

}
