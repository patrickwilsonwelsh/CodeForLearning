package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class SciencePlace extends Place {
	public SciencePlace(int id, CircularQuestionAnswerMap questions) {
		super(id);
		this.questionsAndAnswers = questions;
	}

	public Category getCategory() {
		return Category.SCIENCE;
	}

	public boolean isScience() {
		return true;
	}

}