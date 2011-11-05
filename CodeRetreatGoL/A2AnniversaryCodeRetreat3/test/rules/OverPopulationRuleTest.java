package rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OverPopulationRuleTest {
  private LifeRule overPopulationRule;
  
  @Before
  public void setup() {
    overPopulationRule = new OverPopulationRule();
  }

  @Test
  public void cellWith_FourNeighbors_Dies_ByOverpopulation() throws Exception {
    int neighbors = 4;
    assertTrue(overPopulationRule.appliesWith(neighbors));
  }
  
  @Test
  public void cellWith_ThreeNeighbors_Survives_ByRequisitePopulation() throws Exception {
    int neighbors = 3;
    assertFalse(overPopulationRule.appliesWith(neighbors));
  }
 
  @Test
  public void cellWith_TwoNeighbors_Survives_ByRequisitePopulation() throws Exception {
    int neighbors = 2;
    assertFalse(overPopulationRule.appliesWith(neighbors));
  }


  
  
}
