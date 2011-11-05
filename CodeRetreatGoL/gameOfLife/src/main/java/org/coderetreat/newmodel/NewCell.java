package org.coderetreat.newmodel;

public class NewCell {

	private int row;
	private int col;
	private boolean isAlive;

	public NewCell(int row, int col, int isAlive) {
		this.row = row;
		this.col = col;
		this.isAlive = isAlive == 1;
	}

	public boolean aliveNextTime() {
		return true;
	}

	public boolean isAlive() {
		return isAlive;
	}

}
