package player;

import bettingAction.BettingAction;

public class Fish extends Player {
	public Fish() throws Exception {
		super();
	}

	public BettingAction getAceAction() {
		return BettingAction.BetFiveBucks;
	}

	public BettingAction getKingAction() {
		return BettingAction.Check;
	}

	public BettingAction getQueenAction() {
		return BettingAction.Check;
	}

	public BettingAction getThreeCardAction() {
		return null;
	}

	public BettingAction getGroupFiveAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupFourAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupThreeAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupTwoAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupOneAction() {
		return BettingAction.BetTenBucks;
	}

	public BettingAction getJackAction() {
		return BettingAction.Check;
	}

	public BettingAction getTenCardAction() {
		return BettingAction.Check;
	}

	public BettingAction getNineCardAction() {
		return BettingAction.Check;
	}

	@Override
	public BettingAction getGroupSixAction() {
		return BettingAction.Check;
	}

	@Override
	public BettingAction getGroupSevenAction() {
		return BettingAction.Check;
	}

	public BettingAction getGroupNoneAction() {
		return BettingAction.Check;
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
