package bettingAction;

public class BettingAction {
	public static final BettingAction Check = new Check();
	public static final BettingAction Fold = new Fold();
	public static final BettingAction BetAHundredBucks = new Bet("$100");
	public static final BettingAction BetFiftyBucks = new Bet("$50");;
	public static final BettingAction BetThirtyBucks = new Bet("$30");;
	public static final BettingAction BetTwentyBucks = new Bet("$20");;
	public static final BettingAction BetTenBucks = new Bet("$10");
	public static final BettingAction BetFiveBucks = new Bet("$5");;

	public String name;

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
