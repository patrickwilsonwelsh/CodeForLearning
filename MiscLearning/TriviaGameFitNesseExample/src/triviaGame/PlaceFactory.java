package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class PlaceFactory {
	public PlaceFactory() {
		initializeQuestions();
	}

	private CircularQuestionAnswerMap popQuestionsAndAnswers;
	private CircularQuestionAnswerMap sportsQuestionsAndAnswers;
	private CircularQuestionAnswerMap scienceQuestionsAndAnswers;
	private CircularQuestionAnswerMap rockQuestionsAndAnswers;
	private CircularQuestionAnswerMap winningQuestionsAndAnswers;

	private void initializeQuestions() {
		initializePopQuestions();
		initializeSportsQuestions();
		initializeScienceQuestions();
		initializeRockQuestions();
		initializeWinningQuestions();
	}

	@SuppressWarnings("unchecked")
	private void initializePopQuestions() {
		popQuestionsAndAnswers = new CircularQuestionAnswerMap();
		popQuestionsAndAnswers.put("Pop1", "Answer1");
		popQuestionsAndAnswers.put("Pop2", "Answer2");
		popQuestionsAndAnswers.put("Pop3", "Answer3");
	}

	@SuppressWarnings("unchecked")
	private void initializeSportsQuestions() {
		sportsQuestionsAndAnswers = new CircularQuestionAnswerMap();
		sportsQuestionsAndAnswers.put("Sports1", "Answer1");
		sportsQuestionsAndAnswers.put("Sports2", "Answer2");
		sportsQuestionsAndAnswers.put("Sports3", "Answer3");
	}

	@SuppressWarnings("unchecked")
	private void initializeScienceQuestions() {
		scienceQuestionsAndAnswers = new CircularQuestionAnswerMap();
		scienceQuestionsAndAnswers.put("Science1", "Answer1");
		scienceQuestionsAndAnswers.put("Science2", "Answer2");
		scienceQuestionsAndAnswers.put("Science3", "Answer3");
	}

	@SuppressWarnings("unchecked")
	private void initializeRockQuestions() {
		rockQuestionsAndAnswers = new CircularQuestionAnswerMap();
		rockQuestionsAndAnswers.put("Rock1", "Answer1");
		rockQuestionsAndAnswers.put("Rock2", "Answer2");
		rockQuestionsAndAnswers.put("Rock3", "Answer3");
	}

	@SuppressWarnings("unchecked")
	private void initializeWinningQuestions() {
		winningQuestionsAndAnswers = new CircularQuestionAnswerMap();
		winningQuestionsAndAnswers.put("Winning1", "Answer1");
		winningQuestionsAndAnswers.put("Winning2", "Answer2");
		winningQuestionsAndAnswers.put("Winning3", "Answer3");
	}

	public Place makePlace(Category category, int placeId) {
		if (category == Category.POP)
			return new PopPlace(placeId, popQuestionsAndAnswers);
		else if (category == Category.ROCK)
			return new RockPlace(placeId, rockQuestionsAndAnswers);
		else if (category == Category.SCIENCE)
			return new SciencePlace(placeId, scienceQuestionsAndAnswers);
		else if (category == Category.SPORTS)
			return new SportsPlace(placeId, sportsQuestionsAndAnswers);
		else if (category == Category.WINNING)
			return new WinningPlace(winningQuestionsAndAnswers);
		else
			return null;
	}
}
