package lightsOff;

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
                    "Boards can only have non-zero positive dimensions; can't use ["
                            + height + ", " + width + "].");
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

    public int cells() {
        return height * width;
    }

    public void setOn(int x, int y) {
        set(x, y, true);
    }

    public void setOff(int x, int y) {
        set(x, y, false);
    }

    public void set(int x, int y, boolean value) {
        checkCoordinates(x, y);
        board[x][y] = value;
    }

    public boolean state(int x, int y) {
        checkCoordinates(x, y);
        return board[x][y];
    }

    private void checkCoordinates(int x, int y) {
        if (x < 0 || y < 0 || x >= height || y >= width) {
            throw new IllegalArgumentException(
                    "Invalid cell-coordinates: [" + x + ", " + y + "].");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Board)) {
            return false;
        }
        Board other = (Board) object;
        if (height != other.height || width != other.width) {
            return false;
        }

        boolean[][] otherBoard = other.board;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] != otherBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
