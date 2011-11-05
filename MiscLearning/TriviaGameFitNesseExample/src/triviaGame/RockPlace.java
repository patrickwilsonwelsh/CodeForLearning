package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class RockPlace extends Place {
	public RockPlace(int id, CircularQuestionAnswerMap questions) {
		super(id);
		this.questionsAndAnswers = questions;
	}

	public Category getCategory() {
		return Category.ROCK;
	}

	public boolean isRock() {
		return true;
	}

}