package rules;

public class SpawnLifeRule implements LifeRule {

  @Override
  public boolean appliesWith(int neighbors) {
    return neighbors == 3;
  }

}
