package rules;

/*
 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies, as if by overcrowding.
Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */

//TODO a live cell with 1 live neighbor dies
//TODO a live cell with 3 live neighbor lives
//TODO a live cell with 4 live neighbor dies
//TODO a dead cell with 4 live neighbors stays dead

import static org.junit.Assert.*;

import org.junit.Test;


public class RulesTest {
  private boolean cellIsAlive;
  private int numberOfLiveNeighbors;


  @Test
  public void deadCell_WithOneLiveNeighbor_StayDead() throws Exception {
    cellIsAlive = false;
    numberOfLiveNeighbors = 1;
    assertEquals(false, cellIsAliveInNextGeneration(numberOfLiveNeighbors, cellIsAlive));
  }
  
  @Test
  public void deadCell_WithThreeLiveNeighbors_ComesToLife() throws Exception {
    cellIsAlive = false;
    numberOfLiveNeighbors = 3;
    assertEquals(true, cellIsAliveInNextGeneration(numberOfLiveNeighbors, cellIsAlive));   
  }
  
  @Test
  public void liveCell_WithTwoLiveNeighbors_Lives() throws Exception {
    cellIsAlive = true;
    numberOfLiveNeighbors = 2;
    assertEquals(true, cellIsAliveInNextGeneration(numberOfLiveNeighbors, cellIsAlive));
    
  }
  
  @Test
  public void deadCell_With_Two_Live_Neighbors_Stays_Dead() throws Exception
  {
    cellIsAlive = false;
    numberOfLiveNeighbors = 2;
    assertEquals(false, cellIsAliveInNextGeneration(numberOfLiveNeighbors, cellIsAlive));
  }
  

  
  
  

  private boolean cellIsAliveInNextGeneration(int numberOfLiveNeighbors, boolean cellIsAlive) {
    if (numberOfLiveNeighbors == 2 && cellIsAlive) return true;  
    if (numberOfLiveNeighbors == 3) return true;
    
    return false;
  }

}
