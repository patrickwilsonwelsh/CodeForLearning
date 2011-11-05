package stoned;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

  @Test
  public void canFindOneCell() {
    Board board = new Board(100, 100);
    board.spawnLifeAt(10, 10);
    assertEquals(1, board.numberOfLiveCells());
  }
  
  @Test
  public void canFindTwoCell() {
    Board board = new Board(100, 100);
    board.spawnLifeAt(10, 10);
    board.spawnLifeAt(20, 20);
    assertEquals(2, board.numberOfLiveCells());
  }
  
  
  
  
}
