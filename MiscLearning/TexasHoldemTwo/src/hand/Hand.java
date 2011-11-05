package hand;

import hand.Pocket.PocketType;
import hand.PocketClassifier.PocketGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import player.Player;
import suit.SuitFactory.Suit;
import bettingAction.BettingAction;
import card.Card;

public class Hand implements IHand {

	protected List<Card> cards;
	protected PocketType pocketName;
	protected PocketGroup pocketGroup;
	protected PocketClassifier pocketClassifier;
	private PostFlopClassifier postFlopClassifier;

	public Hand() throws Exception {
		cards = new ArrayList<Card>();
		pocketClassifier = new PocketClassifier();
		postFlopClassifier = new PostFlopClassifier();
		pocketGroup = PocketClassifier.PocketGroup.None;
	}

	protected boolean isFlush() {
		Suit firstCardSuit = cards.get(0).suit();
		for (Card card : cards) {
			if (card.suit() != firstCardSuit)
				return false;
		}

		return true;
	}

	public BettingAction getAction(Player player) throws Exception {
		return getFirstCard().getSingleCardAction(player);
	}

	public Card getFirstCard() {
		return cards.get(0);
	}

	public boolean hasButOneCard() {
		return (cards.size() == 1);
	}

	public Hand add(Card card) throws Exception {
		cards.add(card);
		return getClassifiedHand();
	}

	@SuppressWarnings("unused")
	private void printCards() {
		System.out.println("Cards: ");
		for (Card card : cards) {
			System.out.println("   " + card.rank() + " of " + card.suit());
		}
	}

	@SuppressWarnings("unchecked")
	private Hand getClassifiedHand() throws Exception {
		Collections.sort(cards);
		Collections.reverse(cards);

		if (cards.size() > 1) {
			// printCards();
			setPocketName();
		}

		if (cards.size() == 2) {
			return pocketClassifier.classify(this);
		} else if (cards.size() == 5) {
			return postFlopClassifier.classify(this);
		}

		return this;
	}

	public PocketType getPocketName() {
		return pocketName;
	}

	private void setPocketName() throws Exception {
		String pocketString = "";
		for (int i = 0; i < 2; i++) {
			Card card = cards.get(i);
			pocketString += card.rank();
		}

		if (isFlush())
			pocketString += "Same";

		pocketName = PocketType.valueOf(pocketString);
	}

	public PocketGroup getPocketGroup() {
		return pocketGroup;
	}

}
