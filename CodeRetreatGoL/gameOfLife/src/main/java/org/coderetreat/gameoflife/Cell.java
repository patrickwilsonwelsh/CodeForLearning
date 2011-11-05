/* This page is part of the Game of Life source code */

package org.coderetreat.gameoflife;

/**
 * Every cell in the grid is a Cell-object. So it must be as small as possible.
 * Because every cell is pre-generated, no cells have to be generated when the
 * Game Of Life playw. Whether a cell is alive or not, is not part of the
 * Cell-object.
 * 
 * @author Edwin Martin
 * 
 */
public class Cell {

	private final short col;
	private final short row;
	/**
	 * Number of neighbours of this cell.
	 * 
	 * Determines the next state.
	 */
	private byte neighbour;

	/**
	 * HASHFACTOR must be larger than the maximum number of columns (that is:
	 * the max width of a monitor in pixels). It should also be smaller than
	 * 65536. (sqrt(MAXINT)).
	 */
	private final int HASHFACTOR = 5000;


	/**
	 * Constructor
	 * 
	 * @param col
	 *            column of cell
	 * @param row
	 *            row or cell
	 */
	public Cell(int col, int row) {
		this.col = (short) col;
		this.row = (short) row;
		setNeighbour((byte)0);
	}


	/**
	 * Compare cell-objects for use in hashtables
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Cell))
			return false;
		return getColumn() == ((Cell) o).getColumn() && getRow() == ((Cell) o).getRow();
	}


	/**
	 * Calculate hash for use in hashtables
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HASHFACTOR * getRow() + getColumn();
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell at (" + getColumn() + ", " + getRow() + ") with " + getNeighbour() + " neighbour" + (getNeighbour() == 1 ? "" : "s");
	}


	public short getColumn() {
		return col;
	}


	public short getRow() {
		return row;
	}


	public void setNeighbour(byte neighbour) {
		this.neighbour = neighbour;
	}


	public byte getNeighbour() {
		return neighbour;
	}
}
