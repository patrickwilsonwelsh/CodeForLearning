package game;

public class Life {
	private Board grid = new Board(12, 12);
	private Rules rules = new Rules();

	public Board nextState(Board currentGridState) {
		grid = currentGridState;
		return applyRulesToEntireBoard();
	}

	private Board applyRulesToEntireBoard() {
		for (int x = 0; x < grid.width; x++) {
			applyRulesToThisColumn(x);
		}
		return grid;
	}

	private void applyRulesToThisColumn(int x) {
		for (int y = 0; y < grid.height; y++) {
			applyRulesToThisCell(new CellLocation(x,y));
		}
	}

	private void applyRulesToThisCell(CellLocation location) {
		Cell cell = grid.cellAt(location);
		int livingNeighbors = grid.getNumberOfAliveNeighbors(location);
		cell.setState(convertBooleanToCellState(cell, rules.determineIfCellLivesFromNumberOf(livingNeighbors)));
	}

	private CellState convertBooleanToCellState(Cell cell, boolean alive) {
		if (alive) return CellState.Alive;
		return CellState.Dead;
	}
}
