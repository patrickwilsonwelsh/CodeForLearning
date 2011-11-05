package game;

public enum NeighborDirection {
	N (0, -1),
	NE (1, -1),
	E (1, 0),
	SE (1, 1),
	S (0, 1),
	SW (-1, 1),
	W (-1, 0),
	NW (-1, -1);

	NeighborDirection(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public int dy;
	public int dx;
}
