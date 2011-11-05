package stoned;

public class Rules {

  public boolean producesCell(Nothingness nothingness, int neighbors) {
    return (neighbors == 3);
  }
  
  public boolean lives(Cell cell, int neighbors) {
    return (neighbors == 3 || neighbors == 2);
  }

}
