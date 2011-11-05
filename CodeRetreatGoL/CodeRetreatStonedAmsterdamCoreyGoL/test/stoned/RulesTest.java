package stoned;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RulesTest {

  Cell cell;
  Nothingness nothingness;
  Rules rulesSay;
  @Before
  public void setUp() {
    rulesSay = new Rules();
    cell = new Cell();
    nothingness = new Nothingness();
  }
  
  
  @Test
  public void cellWithOneNeighborDies() {
    assertFalse(rulesSay.lives(cell, 1));
  }
  
  @Test
  public void cellWithTwoNeighborsLives() {
    assertTrue(rulesSay.lives(cell, 2));
  }

  @Test
  public void cellWithThreeNeighborsLives() {
    assertTrue(rulesSay.lives(cell, 3));    
  }
  
  @Test
  public void cellWithFourNeighborsDies() {
    assertFalse(rulesSay.lives(cell, 4));
  }
  
  @Test
  public void nothingnessWithTwoNeighborsIsntAliveNextRound() {
    assertFalse(rulesSay.producesCell(nothingness, 2));
  }
  
  @Test
  public void nothingnessWithThreeNeighborsSpawnsLife() {
    assertTrue(rulesSay.producesCell(nothingness, 3));    
  }

  @Test
  public void nothingnessWithFourNeighborsIsntAliveNextRound() {
    assertFalse(rulesSay.producesCell(nothingness, 4));
  }
  
  
}
