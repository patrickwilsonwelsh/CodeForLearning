package position;

import java.util.ArrayList;
import java.util.List;

import rules.LifeRule;


public abstract class Position {
  protected List<LifeRule> rulesList = new ArrayList<LifeRule>();
  
  public Position applyRulesWith(int neighbors) {
    for (LifeRule rule : rulesList) {
      if (rule.appliesWith(neighbors)) return oppositeState();
    }
  
    return this;
  }
  
  protected abstract Position oppositeState();
}
