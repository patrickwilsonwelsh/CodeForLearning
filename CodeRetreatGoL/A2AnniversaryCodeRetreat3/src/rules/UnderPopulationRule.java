package rules;

public class UnderPopulationRule implements LifeRule {
  @Override
  public boolean appliesWith(int numberOfNeighbors) {
    return numberOfNeighbors < 2;
  }
}
