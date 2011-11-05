package rules;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CellLivesOrDiesTest {
  
  private GenerationRules rulesSay; 
  private Spot cell;
  
  @Before
  public void setup() {
    rulesSay = new GenerationRules();
  }

  @Test
  public void deadCellWith_Zero_LiveNeighbors_Dies() throws Exception {
    cell = new Nothingness();
    assertTrue(rulesSay.given(cell.withLiveNeighbors(0)).thatItDies());
  }
//  
//  @Test
//  public void deadCellWith_Two_LiveNeighbors_Dies() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsDead().withLiveNeighbors(2)).thatItDies());
//  }
//  
//  @Test
//  public void deadCellWith_Three_LiveNeighbors_Lives() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsDead().withLiveNeighbors(3)).thatItLives());
//  }
//  
//  @Test
//  public void deadCellWith_Four_LiveNeighbors_Dies() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsDead().withLiveNeighbors(4)).thatItDies());
//  }
//  
//  
//  @Test
//  public void liveCellWith_Zero_LiveNeighbors_Lives() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsAlive().withLiveNeighbors(0)).thatItDies());
//  }
//  
//  @Test
//  public void liveCellWith_Two_LiveNeighbors_Lives() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsAlive().withLiveNeighbors(2)).thatItLives());
//  }
//
//  @Test
//  public void liveCellWith_Three_LiveNeighbors_Lives() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsAlive().withLiveNeighbors(3)).thatItLives());
//  }
//  
//  @Test
//  public void liveCellWith_Four_LiveNeighbors_Dies() throws Exception {
//    assertTrue(rulesSay.given(cell.thatIsAlive().withLiveNeighbors(4)).thatItDies());
//  }
//  
//  /*
//   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//Any live cell with two or three live neighbours lives on to the next generation.
//Any live cell with more than three live neighbours dies, as if by overcrowding.
//
//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
//   */

}
