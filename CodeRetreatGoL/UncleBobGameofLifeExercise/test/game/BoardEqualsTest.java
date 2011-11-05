package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BoardEqualsTest {
	
	private Board board1;
	private Board board2;

	@Before
	public void init() {
		board1 = new Board(10,10);
		board2 = new Board(10,10);
	}
	
	@Test
	public void emptyBoardsShouldBeEqual() {
		assertTrue(board1.equals(board2));
		assertTrue(board2.equals(board1));
	}
	
	@Test
	public void identicalBoardGridsShouldBeEqual() {
		board1.setCellStateAt(new CellLocation(4, 4), CellState.Alive);
		board2.setCellStateAt(new CellLocation(4, 4), CellState.Alive);
		
		assertTrue(board1.equals(board2));
		assertTrue(board2.equals(board1));
	}
	
	@Test
	public void differentBoardGridCoordinatesShouldNotBeEqual() {
		board1.setCellStateAt(new CellLocation(4, 4), CellState.Alive);
		board2.setCellStateAt(new CellLocation(8, 6), CellState.Alive);
		
		assertFalse(board1.equals(board2));
		assertFalse(board2.equals(board1));
	}
	
	@Test
	public void differentBoardsShouldNotBeEqual() {
		board1.setCellStateAt(new CellLocation(4, 4), CellState.Alive);
		
		assertFalse(board1.equals(board2));
		assertFalse(board2.equals(board1));
	}
}
