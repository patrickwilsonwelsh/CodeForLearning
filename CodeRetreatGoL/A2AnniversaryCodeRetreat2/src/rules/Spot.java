package rules;

public abstract class Spot {
  public int numberOfLiveNeighbors;

  public Spot withLiveNeighbors(int numberOfLiveNeighbors) {
    this.numberOfLiveNeighbors = numberOfLiveNeighbors;
    return this;
  }
  
}
