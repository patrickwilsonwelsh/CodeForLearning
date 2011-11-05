package rules;

import junit.framework.TestCase;


//TODO dead cell w 2 ln dead
//TODO dead cell w 4 ln dead
//TODO live cell w 1 ln dead 
//TODO live cell w 4 ln dead
//TODO live cell w 5 ln dead



public class RulesTest extends TestCase {
  
  
  private Rules rules;
  
  public void setUp() {
    rules = new Rules();
  }

  public void testDeadCell_WithOne_LiveNeighbors_StaysDead() throws Exception {
    Cell deadCell = new Cell(false);
    
    assertFalse(rules.sayThatGiven(deadCell.with(1).liveNeighbors()).cellWillLiveNextTime());
  }
  
  public void testDeadCell_WithTwo_LiveNeighbors_StaysDead() throws Exception {
    Cell deadCell = new Cell(false);
    
    assertFalse(rules.sayThatGiven(deadCell.with(2).liveNeighbors()).cellWillLiveNextTime());
  }
  
  public void testDeadCell_WithThree_LiveNeighbors_ComesAlive() throws Exception {
    Cell deadCell = new Cell(false);
    
    assertTrue(rules.sayThatGiven(deadCell.with(3).liveNeighbors()).cellWillLiveNextTime());
  }
  
  public void testLiveCell_WithTwo_LiveNeighbors_StaysAlive() throws Exception {
    Cell liveCell = new Cell(true);
    
    assertTrue(rules.sayThatGiven(liveCell.with(2).liveNeighbors()).cellWillLiveNextTime());
  }
  
  public void testLiveCell_WithThree_LiveNeighbors_StaysAlive() throws Exception {
    Cell liveCell = new Cell(true);
    
    assertTrue(rules.sayThatGiven(liveCell.with(3).liveNeighbors()).cellWillLiveNextTime());
  }

}
