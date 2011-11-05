package position;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PositionRuleTest {
  @Test
  public void DeadPosition_WithZeroNeighbors_StaysDead() throws Exception {
    int numberOfNeighbors = 0;
    
    Position expected = new Dead().applyRulesWith(numberOfNeighbors);
    assertTrue(expected instanceof Dead);    
  }
  
  @Test
  public void DeadPosition_WithThreeNeighbors_ComesToLife() throws Exception {
    int numberOfNeighbors = 3;
    
    Position expected = new Dead().applyRulesWith(numberOfNeighbors);
    assertTrue(expected instanceof Live);
  }
  
  @Test
  public void DeadPosition_WithFourNeighbors_StaysDead() throws Exception {
    int numberOfNeighbors = 4;
    
    Position expected = new Dead().applyRulesWith(numberOfNeighbors);
    assertTrue(expected instanceof Dead);
  }
  
  @Test
  public void LivePosition_WithThreeNeighbors_StaysAlive() throws Exception {
    int numberOfNeighbors = 3;
    
    Position expected = new Live().applyRulesWith(numberOfNeighbors);
    assertTrue(expected instanceof Live);    
  }
  
  @Test
  public void LivePosition_WithZeroNeighbors_Dies() throws Exception {
    int numberOfNeighbors = 0;
    
    Position expected = new Live().applyRulesWith(numberOfNeighbors);
    assertTrue(expected instanceof Dead);    
  }
  
  @Test
  public void LivePosition_WithFourNeighbors_Dies() throws Exception {
    int numberOfNeighbors = 4;
    
    Position expected = new Live().applyRulesWith(numberOfNeighbors);
    assertTrue(expected instanceof Dead);    
  }

  
}
