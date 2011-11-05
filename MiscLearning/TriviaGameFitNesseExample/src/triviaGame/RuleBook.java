package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class RuleBook {
	private Board theBoard;

	private TurnResult turnResult;

	public Player interpret(TurnResult turnResult, Board board) {
		theBoard = board;
		this.turnResult = turnResult;
		if (turnResult.player.getPlace().isPenaltyBox()) {
			this.movePlayerOutOfBoxIfPossible();
			return null;
		} else {
			return this.checkAnswer();
		}
	}

	public Player checkAnswer() {
		if (turnResult.rightAnswer) {
			return processRightAnswer();
		} else {
			processWrongAnswer();
			return null;
		}
	}

	private Player processRightAnswer() {
		if (theBoard.playerInWinningPlace(turnResult.player))
			return turnResult.player;
		else {
			turnResult.player.addGoldCoin();
			advancePlayerAfterRightAnswer();
			turnResult.lastCategory = turnResult.player.getPlace()
					.getCategory();
			return null;
		}
	}

	private void processWrongAnswer() {
		theBoard.putPlayerInPenaltyBox(turnResult.player);
	}

	private void advancePlayerAfterRightAnswer() {
		if (couldWin())
			theBoard.putPlayerInWinningPlace(turnResult.player);
		else
			theBoard.movePlayer(turnResult);
	}

	public void movePlayerOutOfBoxIfPossible() {
		movePlayerOutOfBoxIfPossible(theBoard, turnResult.player);
	}

	public void movePlayerOutOfBoxIfPossible(Board board, Player player) {
		if (rollIsOdd(turnResult.roll)) {
			board.returnToPlaceBeforePenaltyBox(player);
			board.movePlayer(turnResult);
		} else {
			// roll is even
			board.signalPenaltyBoxPlayerRolled(player);
		}
	}

	private boolean rollIsOdd(int roll) {
		return (roll % 2 == 1);
	}

	private boolean couldWin() {
		return turnResult.player.getGoldCoins() == Game.MAX_GOLD_COINS;
	}
}