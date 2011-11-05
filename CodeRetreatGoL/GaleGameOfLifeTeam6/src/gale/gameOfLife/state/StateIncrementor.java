package gale.gameOfLife.state;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;

public class StateIncrementor {

	public static CellState getNextCellState(CellState currentCellState, int numCurrentAliveNeighbors) {
		if (currentCellState == CellState.Dead)
			return getNextCellStateForDeadCell(numCurrentAliveNeighbors);

		return getNextCellStateForAliveCell(numCurrentAliveNeighbors);
	}

	private static CellState getNextCellStateForAliveCell(
			int numCurrentAliveNeighbors) {
		if (numCurrentAliveNeighbors < 2 || numCurrentAliveNeighbors > 3)
			return CellState.Dead;
			
		return CellState.Alive;
	}

	private static CellState getNextCellStateForDeadCell(
			int numCurrentAliveNeighbors) {
		if (numCurrentAliveNeighbors == 3)
			return CellState.Alive;

		return CellState.Dead;
	}

	public static LifeBoard nextState(LifeBoard board) {
		CellState cellState[][] = board.getCellState();
		CellState newCellState[][] = new CellState[cellState.length][cellState[0].length];
		
		for(int i=0;i<newCellState.length;i++)
			for(int j=0;j<newCellState[0].length;j++)
				newCellState[i][j] = getNextCellState(board.getState(i, j), board.getNumberOfAliveNeighbors(i, j));
		
		board.setCellState(newCellState);	
		return board;
	}


	
	
	
	
}
