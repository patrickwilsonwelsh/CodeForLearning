package hand;

import hand.Pocket.PocketType;
import hand.PocketClassifier.PocketGroup;
import player.Player;
import bettingAction.BettingAction;
import card.Card;

public interface IHand {

	public abstract BettingAction getAction(Player player) throws Exception;

	public abstract IHand add(Card card) throws Exception;

	public abstract PocketType getPocketName();

	public abstract PocketGroup getPocketGroup();

}