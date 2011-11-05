package org.coderetreat.newmodel;

import java.util.ArrayList;
import java.util.List;

public class Cells {
	
	List<NewCell> board;

	public Cells(int[] incomingBoard) {
		board = new ArrayList<NewCell>();
		
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 2; col++) {
				int isAlive = incomingBoard[script(row, col)];
				NewCell cell = new NewCell(row, col, isAlive);
				board.add(cell);
			}
		}
	}

	public boolean aliveNextTime(int row, int col) {
		NewCell cell = getCell(row,col);
		return cell.aliveNextTime();
	}

	protected NewCell getCell(int row, int col) {
		return board.get(script(row,col));
	}

	protected int script(int row, int col) {
		return col + row * 2;
	}

}
