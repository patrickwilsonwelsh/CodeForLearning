package gol;

public class Cell {

  public int numberOfLiveNeighbors;
  private State isAlive;

  public Cell withLiveNeighbors(int numberOfLiveNeighbors) {
    this.numberOfLiveNeighbors = numberOfLiveNeighbors;
    return this;
  }

  public Cell thatIs(State state) {
    this.isAlive = state;
    return this;
  }
  
  public boolean isAlive() {
    return isAlive == State.ALIVE;
  }



}
