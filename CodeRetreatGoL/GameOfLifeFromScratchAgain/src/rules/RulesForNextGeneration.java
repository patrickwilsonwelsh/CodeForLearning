package rules;

public class RulesForNextGeneration {
  private Cell cell;

  public RulesForNextGeneration indicateThat(Cell cell) {
    this.cell = cell;
    return this;
  }

  public boolean lives() {
    return shouldLiveOnToNextGeneration() || shouldBeCreatedByReproduction();
  }

  private boolean shouldBeCreatedByReproduction() {
    return cell.isDead() && cell.hasThreeNeighbors();
  }
  
  private boolean shouldLiveOnToNextGeneration() {
    return cell.isAlive && cell.hasEnoughNeighbors();
  }
  
}
