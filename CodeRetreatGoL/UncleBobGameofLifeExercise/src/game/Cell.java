package game;

public class Cell {
    CellLocation location;
    CellState state;
    
    public Cell(CellLocation location) {
    	this.location = location;
    	this.state = CellState.Dead;
    }
    
    public Cell(CellState state, CellLocation location) {
    	this.location = location;
    	this.state = state;
    }


	public void setState(CellState state) {
    	this.state = state;
    }

    public boolean isAlive() {
      return (state == CellState.Alive);
    }
}
