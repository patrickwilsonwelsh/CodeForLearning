package rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class UnderPopulationRuleTest {
 private LifeRule underPopulationRule;
  
  @Before
  public void setup() {
    underPopulationRule = new UnderPopulationRule();
  }

  @Test
  public void cellWith_ZeroNeighbors_Dies_ByUnderpopulation() throws Exception {
    int neighbors = 0;
    assertTrue(underPopulationRule.appliesWith(neighbors));
  }
  
  @Test
  public void cellWith_TwoNeighbors_Survives_ByRequisitePopulation() throws Exception {
    int neighbors = 2;
    assertFalse(underPopulationRule.appliesWith(neighbors));
  }
  
  @Test
  public void cellWith_ThreeNeighbors_Survives_ByRequisitePopulation() throws Exception {
    int neighbors = 3;
    assertFalse(underPopulationRule.appliesWith(neighbors));
  }
 
  



}
