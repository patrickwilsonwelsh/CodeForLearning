package gale.gameOfLife.state;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;

import org.junit.Assert;
import org.junit.Test;

public class StateIncrementorTest {

	
	@Test
	public void nextStateReturnsNextState() {
	
		LifeBoard board = new LifeBoard(5, 5);
		
		board.setState(0, 0, CellState.Alive);
		board.setState(0, 1, CellState.Alive);
		board.setState(1, 0, CellState.Alive);
		
		StateIncrementor.nextState(board);
		
		Assert.assertEquals(CellState.Alive, board.getState(0, 0));
		Assert.assertEquals(CellState.Alive, board.getState(0, 1));
		Assert.assertEquals(CellState.Alive, board.getState(1, 0));
		Assert.assertEquals(CellState.Alive, board.getState(1, 1));
		
	}

	
	/**
	 * 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
   	 * 2. Any live cell with more than three live neighbours dies, as if by overcrowding.
   	 * 3. Any live cell with two or three live neighbours lives on to the next generation.
   	 * 4. Any dead cell with exactly three live neighbours becomes a live cell.
	 */
	
	@Test
	public void getNextCellStateImplementsLifeRules() {
		CellState initialState = CellState.Dead;
		
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 1));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 0));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 1));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 0));
			
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 4));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 5));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 6));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 7));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Alive, 8));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 4));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 5));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 6));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 7));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 8));
		
		Assert.assertEquals(CellState.Alive, StateIncrementor.getNextCellState(CellState.Dead, 3));
		Assert.assertEquals(CellState.Alive, StateIncrementor.getNextCellState(CellState.Alive, 2));
		Assert.assertEquals(CellState.Alive, StateIncrementor.getNextCellState(CellState.Alive, 3));
		Assert.assertEquals(CellState.Dead, StateIncrementor.getNextCellState(CellState.Dead, 2));
		
	}
	
	
}
