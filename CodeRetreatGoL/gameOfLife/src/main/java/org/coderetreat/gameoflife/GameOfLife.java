/* This page is part of the Game of Life source code */

/**
 * Copyright 1996-2004 Edwin Martin <edwin@bitstorm.nl>
 * @author Edwin Martin
 */

package org.coderetreat.gameoflife;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameOfLife implements CellGrid {
	
	List<Cell> tempList = new ArrayList<Cell>();

	public GameOfLife(int cellCols, int cellRows) {
		tempList = new ArrayList<Cell>();
		for ( int col  = 0; col < cellCols; col++ ) {
			for ( int row = 0; row < cellRows; row++) {
				System.out.println("row : " + row);
				System.out.println("column : " + col);				
				tempList.add(new Cell(row, col));
			}
		}
	}


	public void clear() {
	
	}


	public void nextGeneration() {
		
	}

	public Iterator<Cell> getCellIterator() {
		return tempList.iterator();
	}


	public boolean getCell(int col, int row) {
		throw new UnsupportedOperationException("Not implemented yet");
	}


	public void setCell(int col, int row, boolean c) {
	}


	public int getGenerations() {
		return 3;
	}

	public void resize(int cellColsNew, int cellRowsNew) {
		
	}


	public int getColumns() {
		return 5;
	}

	public int getRows() {
		return 5;
	}
}