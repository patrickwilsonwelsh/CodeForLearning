
package game;

import util.GridWrapper;

public class Board {
	Cell[][] grid;
	public int width;
	public int height;
	private GridWrapper gridWrapper;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		initGrid(width, height);
		gridWrapper = new GridWrapper(width, height);
	}

	public int getNumberOfAliveNeighbors(CellLocation location) {
		int liveNeighborCount = 0;
		for (NeighborDirection vector : NeighborDirection.values()) {
			if (getNeighbor(location, vector).isAlive()) liveNeighborCount++;
		}
		return liveNeighborCount;
	}
	
	private Cell getNeighbor(CellLocation location, NeighborDirection vector) {
		CellLocation neighborLoc = location.plus(vector);
		neighborLoc = gridWrapper.wrapGrid(location, neighborLoc);
		
		return cellAt(neighborLoc);
	}

	public void setCellStateAt(CellLocation location, CellState state) {
		grid[location.x][location.y].setState(state);
	}

	private void initGrid(int width, int height) {
		grid = new Cell[width][height];

		for (int x = 0; x < width; x++) {
			initEachRow(height, x);
		}
	}

	private void initEachRow(int height, int x) {
		for (int y = 0; y < height; y++) {
			grid[x][y] = new Cell(new CellLocation(x, y));
		}
	}
	
	public Cell cellAt(CellLocation location) {
		return grid[location.x][location.y];
	}

	public void setGridState(Cell[][] gridState) {
		grid = gridState;
	}

	public Cell[][] getGridState() {
		return grid;
	}
	
    @Override 
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Board) {
        	Board that = (Board) other;
            result = (that.canEqual(this) 
            		&& that.allPointsOnGridEqualsGridFor(this) 
            		&& this.allPointsOnGridEqualsGridFor(that));
        }
        return result;
    }

    private boolean allPointsOnGridEqualsGridFor(Board otherBoard) {
		for (int y = 0; y < otherBoard.height-1; y++) {
			for (int x = 0; x < otherBoard.width-1; x++) {
				CellState thisCellState = this.grid[x][y].state;
				CellState thatCellState = otherBoard.grid[x][y].state;
				if (thisCellState != thatCellState) return false;
			}
		}
    	
		return true;
	}

	@Override 
	public int hashCode() {
        return (41 * (41 + width) + height);
    }

    private boolean canEqual(Object other) {
        return (other instanceof Board);
    }
}
