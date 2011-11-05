package game;

public class CellLocation {
    public int x;
    public int y;

    public CellLocation(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public CellLocation plus(NeighborDirection v) {
      return new CellLocation(x + v.dx, y + v.dy);
    }
}
