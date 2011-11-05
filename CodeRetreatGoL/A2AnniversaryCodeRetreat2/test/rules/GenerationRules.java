package rules;

public class GenerationRules {
  private Spot currentCell;

  public GenerationRules given(Spot cell) {
    this.currentCell = cell;
    return this;
  }

  public abstract boolean thatItLives() {
  }

  public boolean thatItDies() {
    return !thatItLives();
  }

}
