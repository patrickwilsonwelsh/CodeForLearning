package gale.gameOfLife;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LifeBoardTest {

	LifeBoard board;
	
	
	@Before
	public void setUp() {
		board = new LifeBoard(8, 9);
	}
	
	@Test
	public void testAllStateAreDeadOnInitialization() {
		for(int i=0;i<board.getCellState().length;i++)
			for(int j=0;j<board.getCellState()[0].length;j++)
				Assert.assertEquals(CellState.Dead, board.getState(i, j));
	}
	
	@Test
	public void testAliveBoardStateRemainsSame() throws Exception {
		board.setState(1, 1, CellState.Alive);
		Assert.assertEquals(CellState.Alive, board.getState(1, 1));	
	}
	
	@Test
	public void testDeadBoardStateRemainsSame() throws Exception {
		board.setState(2, 2, CellState.Dead);
		Assert.assertEquals(CellState.Dead, board.getState(2, 2));
	}
	

	@Test
	public void getNumberOfAliveNeighborsReturnsAppropriateInteger() throws Exception {
		
	
		board.setState(0, 0, CellState.Alive);
		board.setState(1, 0, CellState.Alive);
		board.setState(2, 2, CellState.Alive);
		assertEquals(3, board.getNumberOfAliveNeighbors(1, 1));
		assertEquals(2, board.getNumberOfAliveNeighbors(2, 1));

	}
	
	
	@Test
	public void boardWrapsFromLeftToRight() throws Exception {
		
		board.setState(7, 1, CellState.Alive);
		board.setState(7, 2, CellState.Alive);
		board.setState(0, 1, CellState.Alive);
		
		board.tick();
		
		assertEquals(CellState.Alive, board.getState(0, 2));
	}
	
	@Test
	public void getRealXPositionWrapsTo0AfterBoardLength() {
		Assert.assertEquals(0, board.getRealXPosition(8));
	}
	
	@Test
	public void getRealXPositionWrapsToBoardMaxBefore0() {
		Assert.assertEquals(7, board.getRealXPosition(-1));
	}
	
	@Test
	public void getRealYPositionWrapsTo0AfterBoardLength() {
		Assert.assertEquals(0, board.getRealYPosition(9));
	}
	
	
	
}
