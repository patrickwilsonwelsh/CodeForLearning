package position;

import rules.OverPopulationRule;
import rules.UnderPopulationRule;

public class Live extends Position {
  public Live() {
    rulesList.add(new OverPopulationRule());
    rulesList.add(new UnderPopulationRule());
  }

  @Override
  public Position oppositeState() {
    return new Dead();
  }

}
