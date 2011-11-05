package gale.gameOfLife;

import gale.gameOfLife.state.StateIncrementor;

import java.util.List;

import org.junit.Assert;

public class LifeBoard {
private CellState[][] board;


	public LifeBoard(int width, int height){
		board = new CellState[width][height];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				board[i][j]=CellState.Dead;
			}
		}
	}
	
	public CellState[][] getCellState() {
		return board;
	}
	
	public void setCellState(CellState[][] cellState){
		this.board = cellState;
	}
	
	
	public void setState(int x, int y, CellState cellState) {
		board[x][y] = cellState;
		
	}

	public CellState getState(int x, int y) {
		
		return board[x][y];
		
	}
	
	private int checkNeighborAliveRow(int x, int y, int count, int j) {
		for (int k = -1; k<=1; k++) {
			if ((k != 0 || j !=0) && cellAliveExists(x+j, y+k)) {
				count++;
			}
		}
		
//		if (j == 0) return count;
		
//		if (j !=0 && cellAliveExists(x+j, y-1)) count++;
//		if (j !=0 && cellAliveExists(x+j, y+1)) count++;
		
		
		
		return count;
	}

	private boolean cellAliveExists(int x, int y) {
		return board[getRealXPosition(x)][getRealYPosition(y)] == CellState.Alive;
	}
	
	public int getNumberOfAliveNeighbors(int x, int y) {
		int count = 0;

		if(cellAliveExists(x-1, y-1)) count++;
		if(cellAliveExists(x+0, y-1)) count++;
		if(cellAliveExists(x+1, y-1)) count++;
		if(cellAliveExists(x-1, y+0)) count++;
		if(cellAliveExists(x+1, y+0)) count++;
		if(cellAliveExists(x-1, y+1)) count++;
		if(cellAliveExists(x+0, y+1)) count++;
		if(cellAliveExists(x+1, y+1)) count++;
		
		return count;
	}

	public void tick() {
		StateIncrementor.nextState(this);
	}

	public int getRealXPosition(int i) {
		if (i<0)
			return board.length - Math.abs(i);
			
		return i%board.length;
	}

	public int getRealYPosition(int i) {
		if (i<0)
			return board[0].length - Math.abs(i);
			
		return i%board[0].length;
	}
}
