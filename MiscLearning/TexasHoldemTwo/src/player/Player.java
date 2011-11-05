package player;

import hand.Hand;
import hand.IHand;
import hand.Pocket.PocketType;
import bettingAction.BettingAction;
import card.Card;

public abstract class Player implements IPlayer {
	private IHand hand;

	public Player() throws Exception {
		hand = new Hand();
	}

	public abstract BettingAction getGroupOneAction();

	public abstract BettingAction getGroupTwoAction();

	public abstract BettingAction getGroupThreeAction();

	public abstract BettingAction getGroupFourAction();

	public abstract BettingAction getGroupFiveAction();

	public abstract BettingAction getGroupSixAction();

	public abstract BettingAction getGroupSevenAction();

	public void dealtCard(Card aCard) throws Exception {
		hand = hand.add(aCard);
	}

	// REFACTOR This starts a cyclic dependency from here to hand, to card,
	// back to pocketCardStrategy,
	// all to enable double-dispatch. Is this really better than explicit
	// conditional
	// logic that enables a hierarchical, acyclic dependency structure?
	public BettingAction getAction() throws Exception {
		return hand.getAction(this);
	}

	public BettingAction getPocketAction() {
		return BettingAction.Check;
	}

	public PocketType getHandName() {
		return hand.getPocketName();
	}

	public IHand getHand() {
		return hand;
	}

}
