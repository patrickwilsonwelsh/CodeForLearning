package gol;

import static gol.State.DEAD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class RulesTest {
  
  private Cell cell;
  private GenerationRules rules;
  
  @Before
  public void setup() {
    cell = new Cell();
    rules = new GenerationRules();
  }

  @Test
  public void deadCell_WithZeroLiveNeighbors_ShouldStayDead() throws Exception {
    assertTrue(rulesStateThat(cell.thatIs(DEAD).withLiveNeighbors(0)).dies(cell));
  }
  
  @Test
  public void deadCell_WithTwoLiveNeighbors_ShouldStayDead() throws Exception {
    assertTrue(rulesStateThat(cell.thatIs(DEAD).withLiveNeighbors(2)).dies(cell));
  }
  
  @Test
  public void deadCell_WithThreeLiveNeighbors_ShouldComeToLife() throws Exception {
    assertTrue(rulesStateThat(cell.thatIs(DEAD).withLiveNeighbors(3)).lives(cell));
  }
  
  @Test
  public void deadCell_WithFourLiveNeighbors_ShouldStayDead() throws Exception {
    assertTrue(rulesStateThat(cell.thatIs(DEAD).withLiveNeighbors(4)).dies(cell));
  }
  
  @Test
  public void liveCell_WithZeroLiveNeighbors_ShouldDie() throws Exception {
    assertTrue(rulesStateThat(cell.thatIs(DEAD).withLiveNeighbors(0)).dies(cell));
  }
  
  @Test
  public void liveCell_WithTwoLiveNeighbors_ShouldLive() throws Exception {
    Cell cell = new Cell().withLiveNeighbors(2).thatIs(State.ALIVE);
    GenerationRules rules = new GenerationRules();
    assertTrue(rules.lives(cell));
  }
  
  @Test
  public void liveCell_WithThreeLiveNeighbors_ShouldLive() throws Exception {
    Cell cell = new Cell().withLiveNeighbors(3).thatIs(State.ALIVE);
    GenerationRules rules = new GenerationRules();
    assertTrue(rules.lives(cell));
  }
  
  @Test
  public void liveCell_WithFourLiveNeighbors_ShouldDie() throws Exception {
    Cell cell = new Cell().withLiveNeighbors(4).thatIs(State.ALIVE);
    GenerationRules rules = new GenerationRules();
    assertFalse(rules.lives(cell));
  }
  
  private GenerationRules rulesStateThat(Cell thatIs) {
    return rules;
  }


    
}


/*

Any live cell with fewer than two live neighbours dies, as if caused by under-population.
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies, as if by overcrowding.
Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
*/