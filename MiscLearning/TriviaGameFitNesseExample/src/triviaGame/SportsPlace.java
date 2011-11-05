package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class SportsPlace extends Place {
	public SportsPlace(int id, CircularQuestionAnswerMap questions) {
		super(id);
		this.questionsAndAnswers = questions;
	}

	public Category getCategory() {
		return Category.SPORTS;
	}

	public boolean isSports() {
		return true;
	}

}