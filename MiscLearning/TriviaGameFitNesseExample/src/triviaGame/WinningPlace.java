package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class WinningPlace extends Place {
	public WinningPlace(CircularQuestionAnswerMap questions) {
		this();
		this.questionsAndAnswers = questions;
	}

	public WinningPlace() {
		super(Board.WINNING_PLACE_INDEX);
	}

	public Category getCategory() {
		return Category.WINNING;
	}

	public boolean isWinningPlace() {
		return true;
	}

}