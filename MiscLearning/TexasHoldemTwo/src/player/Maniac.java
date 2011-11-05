package player;

import bettingAction.BettingAction;

public class Maniac extends Player {
	public Maniac() throws Exception {
		super();
	}

	public BettingAction getAceAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getKingAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getQueenAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getThreeCardAction() {
		return BettingAction.Fold;
	}

	public BettingAction getJackAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getTenCardAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getGroupOneAction() {
		return BettingAction.BetAHundredBucks;
	}

	public BettingAction getGroupTwoAction() {
		return BettingAction.BetAHundredBucks;
	}

	public BettingAction getGroupThreeAction() {
		return BettingAction.BetFiftyBucks;
	}

	public BettingAction getGroupFourAction() {
		return BettingAction.BetFiftyBucks;
	}

	@Override
	public BettingAction getGroupFiveAction() {
		return BettingAction.BetFiftyBucks;
	}

	@Override
	public BettingAction getGroupSixAction() {
		return BettingAction.BetTenBucks;
	}

	@Override
	public BettingAction getGroupSevenAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getNineCardAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getGroupNoneAction() {
		return BettingAction.Check;
	}

	public BettingAction getEightCardAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getFiveCardAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getFourCardAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getSevenCardAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getSixCardAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getTwoCardAction() {
		return BettingAction.Check;
	}
}
