package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class PopPlace extends Place {
	public PopPlace(int id, CircularQuestionAnswerMap questions) {
		super(id);
		this.questionsAndAnswers = questions;
	}

	public Category getCategory() {
		return Category.POP;
	}

	public boolean isPop() {
		return true;
	}

}