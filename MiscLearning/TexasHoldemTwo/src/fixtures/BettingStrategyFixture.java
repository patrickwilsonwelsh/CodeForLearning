package fixtures;

import player.Fish;
import player.Lion;
import player.Maniac;
import player.Player;
import player.Rock;
import card.Card;
import fit.ColumnFixture;

public class BettingStrategyFixture extends ColumnFixture {
	public String firstCard;
	public String secondCard;
	public String playerType;
	public String flop1;
	public String flop2;
	public String flop3;

	private Player player;
	private CardFactory factory;

	public void execute() throws Exception {
		if (playerType.equals("Fish"))
			player = new Fish();
		else if (playerType.equals("Maniac"))
			player = new Maniac();
		else if (playerType.equals("Lion"))
			player = new Lion();
		else if (playerType.equals("Rock"))
			player = new Rock();

		factory = new CardFactory();
		player.dealtCard(getCardFor(firstCard));
		if (secondCard != null)
			player.dealtCard(getCardFor(secondCard));
	}

	private Card getCardFor(String card) {
		return factory.cardForString(card);
	}

	public String firstCardStrategy() throws Exception {
		return player.getAction().toString();
	}

	public String pocketCardsStrategy() throws Exception {
		return player.getAction().name;
	}

	public String pocketGroup() {
		return player.getHand().getPocketGroup().toString();
	}

	public String flopStrategy() {
		return "";
	}

}
