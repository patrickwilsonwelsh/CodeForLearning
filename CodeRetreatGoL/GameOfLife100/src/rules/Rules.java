package rules;

public class Rules {
  private Cell cell;

  public Rules() {
  }

  public Rules sayThatGiven(Cell cell) {
    this.cell = cell;
    return this;
  }

  public boolean cellWillLiveNextTime() {
    if (cell.isAlive() && cell.getLiveNeighbors() == 2) return true;
    if (cell.getLiveNeighbors() == 3) return true;
    
    return false;
  }

}
