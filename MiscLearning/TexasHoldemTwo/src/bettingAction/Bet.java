package bettingAction;

public class Bet extends BettingAction {
	@SuppressWarnings("unused")
	private String amount;

	public Bet(String amount) {
		this.amount = amount;
		name = "Bet " + amount;
	}
}
