package triviaGame;

public abstract class Place {
	public int id;

	public int getId() {
		return id;
	}

	public CircularQuestionAnswerMap questionsAndAnswers;

	protected Place(int id) {
		this.id = id;
		questionsAndAnswers = new CircularQuestionAnswerMap();
	}

	public static Place parse(String placeString) {
		if (placeString == "PenaltyBox") {
			return new PenaltyBox();
		} else if (placeString == "WinningPlace") {
			return new WinningPlace();
		} else {
			return makeCategoryAwarePlace(placeString);
		}
	}

	public static Place makeCategoryAwarePlace(String placeString) {
		int placeId = Integer.parseInt(placeString);

		return new PlaceFactory().makePlace(Board.getCategoryForPlace(placeId),
				placeId);
	}

	public String getQuestionAnswerPair() {
		return questionsAndAnswers.getQuestionAnswerPair();
	}

	public boolean isPop() {
		return false;
	}

	public boolean isRock() {
		return false;
	}

	public boolean isScience() {
		return false;
	}

	public boolean isSports() {
		return false;
	}

	public boolean isPenaltyBox() {
		return false;
	}

	public boolean isWinningPlace() {
		return false;
	}

	public String toString() {
		return String.valueOf(id);
	}

	public abstract Category getCategory();

	public boolean equals(Object obj) {
		Place otherPlace = (Place) obj;
		return (this.id == otherPlace.id);
	}

	public int hashCode() {
		return id;
	}
}