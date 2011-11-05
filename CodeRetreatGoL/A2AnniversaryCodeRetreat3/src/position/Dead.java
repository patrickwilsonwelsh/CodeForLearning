package position;

import rules.SpawnLifeRule;

public class Dead extends Position {
  public Dead() {
    rulesList.add(new SpawnLifeRule());
  }
  
  @Override
  public Position oppositeState() {
    return new Live();
  }

}
