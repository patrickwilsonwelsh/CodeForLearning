package gol;

public class GenerationRules {

  public boolean lives(Cell cell) {
    int numberOfLiveNeighbors = cell.numberOfLiveNeighbors;
    if (cell.isAlive()) 
      return (numberOfLiveNeighbors == 2) 
        || (numberOfLiveNeighbors == 3);
    
    return (numberOfLiveNeighbors == 3);
  }

  public boolean dies(Cell cell) {
    return (!lives(cell));
  }


}
