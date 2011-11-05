package rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RulesForNextGenerationTest {
  private RulesForNextGeneration rules;
  private Cell cell;

  @Before
  public void setup() {
    rules = new RulesForNextGeneration();
  }
  
  @Test
  public void deadCellWith_0_LiveNeighbors_StaysDead() throws Exception {
    cell = new Cell().thatIsDead().withLiveNeighbors(0);
    
    assertDies(cell);
  }

  @Test
  public void deadCellWith_2_LiveNeighbors_StaysDead() throws Exception {
    cell = new Cell().thatIsDead().withLiveNeighbors(2);
    
    assertDies(cell);
  }

  @Test
  public void deadCellWith_3_LiveNeighbors_ComesAlive() throws Exception {
    cell = new Cell().thatIsDead().withLiveNeighbors(3);
    assertLives(cell);
  }

  @Test
  public void deadCellWith_4_LiveNeighbors_StaysDead() throws Exception {
    cell = new Cell().thatIsDead().withLiveNeighbors(4);
    
    assertDies(cell);
  }

  @Test
  public void liveCellWith_0_LiveNeighbors_Dies() throws Exception {
    cell = new Cell().thatIsAlive().withLiveNeighbors(0);
    
    assertDies(cell);
  }


  @Test
  public void liveCellWith_2_LiveNeighbors_StaysAlive() throws Exception {
    cell = new Cell().thatIsAlive().withLiveNeighbors(2);
    
    assertLives(cell);
  }

  @Test
  public void liveCellWith_3_LiveNeighbors_StaysAlive() throws Exception {
    cell = new Cell().thatIsAlive().withLiveNeighbors(3);
    
    assertLives(cell);
  }

  @Test
  public void liveCellWith_4_LiveNeighbors_Dies() throws Exception {
    cell = new Cell().thatIsAlive().withLiveNeighbors(4);
    
    assertDies(cell);
  }
  
  private void assertLives(Cell cell) {
    assertTrue(rules.indicateThat(cell).lives());
  }
  
  private void assertDies(Cell cell) {
    assertFalse(rules.indicateThat(cell).lives());
  }



  // Test list

  // TODO a dead cell with 0 live neighbors stays dead.
  // TODO a dead cell with 2 live neighbors stays dead.
  // TODO a dead cell with 3 live neighbors comes alive.
  // TODO a dead cell with 4 live neighbors stays dead.

  // TODO a live cell with 0 live neighbors dies.
  // TODO a live cell with 2 live neighbors stays alive.
  // TODO a live cell with 3 live neighbors stays alive.
  // TODO a live cell with 4 live neighbors dies.

}

