package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class NextBoardStateTest {
	private Board board;
	private Life life;

	@Before
	public void init() {
		int width = 10;
		int height = 10;
		board = new Board(width, height);
		life = new Life();
	}
	
	@Test
	public void emptyBoardShouldRemainEmpty() {
		assertTrue(board.equals(life.nextState(board)));
	}
}
