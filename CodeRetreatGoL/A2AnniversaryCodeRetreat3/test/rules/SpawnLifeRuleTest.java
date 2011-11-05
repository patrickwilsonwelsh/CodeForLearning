package rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class SpawnLifeRuleTest {
  private SpawnLifeRule rule;

  @Before
  public void setup() {
    rule = new SpawnLifeRule();
  }
  
  @Test
  public void canSpawnNewCell_IfExactlyThreeNeighbors() throws Exception {
    int neighbors = 3;
    assertTrue(rule.appliesWith(neighbors));
  }
  
  @Test
  public void doesntSpawnNewCell_IfTwoNeighbors() throws Exception {
    int neighbors = 2;
    assertFalse(rule.appliesWith(neighbors));
  }
  
  @Test
  public void doesntSpawnNewCell_IfFourNeighbors() throws Exception {
    int neighbors = 4;
    assertFalse(rule.appliesWith(neighbors));
  }

}
