package triviaGame;

/*
 * Copyright 2004 Adaption Software Inc. Patrick Wilson-Welsh
 */
public class TurnResult {
	public int roll;

	public boolean rightAnswer;

	public Player player;

	public Category lastCategory;

	public TurnResult(int roll, boolean rightAnswer, Player currentPlayer) {
		this.roll = roll;
		this.rightAnswer = rightAnswer;
		this.player = currentPlayer;
	}
}