package codeChef.lightsOff;

public class Board {

	private final boolean[][] board;
	private final int height;
	private final int width;

	public Board(int dimension) {
		this(dimension, dimension);
	}

	public Board(int height, int width) {
		if (height <= 0 || width <= 0) {
			throw new IllegalArgumentException(
					"Boards have only non-zero positive dimensions; can't use [" + height + ", " + width + "].");
		}
		this.height = height;
		this.width = width;
		
		this.board = new boolean[width][];
		for (int i = 0; i < width; ++i) {
			this.board[i] = new boolean[height];
		}
	}
	
	public int height() {
		return height;
	}
	
	public int width() {
		return width;
	}
	
	public boolean state(int x, int y) {
		checkCoordinates(x, y);
		return board[x][y];
	}
	
	private void checkCoordinates(int x, int y) {
		if (x < 0 || y < 0 || x >= height || y >= width) {
			throw new IllegalArgumentException("Invalid cell-coordinates: [" + x + ", " + y + "].");
		}
	}
}
