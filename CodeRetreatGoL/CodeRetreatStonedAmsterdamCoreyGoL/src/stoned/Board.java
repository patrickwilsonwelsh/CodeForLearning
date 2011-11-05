package stoned;

public class Board {

  Occupier[][] board;

  public Board(int maxX, int maxY) {
    board = new Occupier[maxX][maxY];
  }

  public int numberOfLiveCells() {
    int liveCells = 0;
    for(int x = 0; x < board.length; x++) {
      for(int y = 0; y < board[0].length; y++) {
        if(board[x][y] instanceof Cell)
          liveCells++;
      }
    }
    return 1;
  }

  public void spawnLifeAt(int x, int y) {
    board[x][y] = new Cell();
  }

}
