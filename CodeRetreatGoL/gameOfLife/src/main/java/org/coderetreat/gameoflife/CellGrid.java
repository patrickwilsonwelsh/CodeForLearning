/* This page is part of the Game of Life source code */

/**
 * Interface to Game of Life Grid.
 * The CellGridCanvas can deal with any grid, not only the Game of Life.
 * Copyright 1996-2004 Edwin Martin <edwin@bitstorm.nl>
 * @author Edwin Martin
 */

package org.coderetreat.gameoflife;

import java.util.Iterator;

/**
 * Interface between GameOfLifeCanvas and GameOfLifeApplet. This way GameOfLifeCanvas
 * is generic, independent of GameOfLifeApplet. It contains generic methods to operate
 * on a cell grid.
 * 
 * @author Edwin Martin
 */
public interface CellGrid {

	/**
	 * Get status of cell (alive or dead).
	 * 
	 * @param col
	 *            x-position
	 * @param row
	 *            y-position
	 * @return living or not
	 */
	boolean getCell(int col, int row);


	/**
	 * Set status of cell (alive or dead).
	 * 
	 * @param col
	 *            x-position
	 * @param row
	 *            y-position
	 * @param cell
	 *            living or not
	 */
	void setCell(int col, int row, boolean cell);

	int getRows();
	int getColumns();

	/**
	 * Resize the cell grid.
	 * 
	 * @param col
	 *            new number of columns.
	 * @param row
	 *            new number of rows.
	 */
	void resize(int col, int row);


	/**
	 * Get cell-enumerator. Enumerates over all living cells (type Cell).
	 * 
	 * @return Enumerator over Cell.
	 * @see Cell
	 */
	Iterator<Cell> getCellIterator();


	/**
	 * Clears grid.
	 */
	void clear();

	int getGenerations();

	void nextGeneration();
}