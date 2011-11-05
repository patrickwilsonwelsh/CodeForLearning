package rules;

public class Cell {
  private int liveNeighbors;
  private boolean state;

  public Cell(boolean isAlive) {
    this.state = isAlive;
  }

  public Cell with(int liveNeighbors) {
    this.liveNeighbors = liveNeighbors;
    return this;
  }

  public Cell liveNeighbors() {
    return this;
  }

  public int getLiveNeighbors() {
    return liveNeighbors;
  }

  public boolean isAlive() {
   return state;
  }

}
