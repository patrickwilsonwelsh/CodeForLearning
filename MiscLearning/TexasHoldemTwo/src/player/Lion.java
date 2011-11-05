package player;

import bettingAction.BettingAction;

public class Lion extends Player {
	public Lion() throws Exception {
		super();
	}

	public BettingAction getKingAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getAceAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getGroupThreeAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupTwoAction() {
		return BettingAction.BetThirtyBucks;
	}

	public BettingAction getGroupOneAction() {
		return BettingAction.BetThirtyBucks;
	}

	public BettingAction getQueenAction() {
		return BettingAction.Check;
	}

	public BettingAction getJackAction() {
		return BettingAction.Check;
	}

	public BettingAction getTenCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getThreeCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupFourAction() {
		return BettingAction.Check;
	}

	@Override
	public BettingAction getGroupFiveAction() {
		return BettingAction.Check;
	}

	public BettingAction getNineCardAction() {
		return BettingAction.Check;
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
