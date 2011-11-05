package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class PenaltyBox extends Place {
	public PenaltyBox() {
		super(Board.PENALTY_BOX_INDEX);
	}

	public boolean isPenaltyBox() {
		return true;
	}

	// REFACTOR Introduce Null Category
	public Category getCategory() {
		return null;
	}

	public String toString() {
		return "null";
	}
}