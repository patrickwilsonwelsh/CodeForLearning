package rules;

public class Cell {
  protected int numberOfLiveNeighbors;
  protected boolean isAlive;

  public Cell() {
    this.isAlive = true;
  }

  public Cell withLiveNeighbors(int numberOfLiveNeighbors) {
    this.numberOfLiveNeighbors = numberOfLiveNeighbors;
    return this;
  }

  public Cell thatIsAlive() {
    this.isAlive = true;
    return this;
  }

  public Cell thatIsDead() {
    this.isAlive = false;
    return this;
  }

  public boolean isDead() {
    return (!isAlive);
  }

  public boolean hasThreeNeighbors() {
    return numberOfLiveNeighbors == 3;
  }

  public boolean hasEnoughNeighbors() {
    return hasTwoNeighbors() || hasThreeNeighbors();
  }
  
  private boolean hasTwoNeighbors() {
    return numberOfLiveNeighbors == 2;
  }


}
