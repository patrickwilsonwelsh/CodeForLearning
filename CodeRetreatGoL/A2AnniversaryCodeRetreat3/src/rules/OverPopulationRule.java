package rules;

public class OverPopulationRule implements LifeRule {

  @Override
  public boolean appliesWith(int numberOfNeighbors) {
    return (numberOfNeighbors > 3);
  }

}
