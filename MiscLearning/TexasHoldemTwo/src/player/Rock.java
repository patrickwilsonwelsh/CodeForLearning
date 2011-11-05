package player;

import bettingAction.BettingAction;

public class Rock extends Player {
	public Rock() throws Exception {
		super();
	}

	public BettingAction getAceAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getKingAction() {
		return BettingAction.BetTwentyBucks;
	}

	public BettingAction getJackAction() {
		return BettingAction.Check;
	}

	public BettingAction getTenCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getQueenAction() {
		return BettingAction.Check;
	}

	public BettingAction getThreeCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getNineCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupOneAction() {
		return BettingAction.BetAHundredBucks;
	}

	public BettingAction getGroupTwoAction() {
		return BettingAction.BetAHundredBucks;
	}

	public BettingAction getGroupThreeAction() {
		return BettingAction.Fold;
	}

	public BettingAction getGroupFourAction() {
		return BettingAction.Fold;
	}

	@Override
	public BettingAction getGroupFiveAction() {
		return BettingAction.Fold;
	}

	@Override
	public BettingAction getGroupSixAction() {
		return BettingAction.Fold;
	}

	@Override
	public BettingAction getGroupSevenAction() {
		return BettingAction.Fold;
	}

	public BettingAction getGroupNoneAction() {
		return BettingAction.Fold;
	}

	public BettingAction getEightCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getFiveCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getFourCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getSevenCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getSixCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getTwoCardAction() {
		return BettingAction.Check;
	}
}
