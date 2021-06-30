package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lightsOff.Board;

public class BoardTest {
    private Board board = null;
    private Exception exception = null;

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void tearDown() {}

    /*
     * Constructor tests.
     */

    @Test
    @DisplayName("Constructing square board.")
    public void testSquareBoard() {
        this.board = new Board(10);
        assertEquals(board.height(), 10);
        assertEquals(board.width(), 10);
    }

    @Test
    @DisplayName("Constructing rectangle board.")
    public void testRectangleBoard() {
        this.board = new Board(2, 1000);
        assertEquals(board.height(), 2);
        assertEquals(board.width(), 1000);
        this.board = new Board(1000, 2);
        assertEquals(board.height(), 1000);
        assertEquals(board.width(), 2);
    }

    @Test
    @DisplayName("Board's initial state should have false cells.")
    public void testInitialState() {
        this.board = new Board(1000);

        for (int i = 0; i < board.height(); ++i) {
            for (int j = 0; j < board.width(); ++j) {
                assertFalse(board.state(i, j));
            }
        }
    }

    /*
     * Method tests.
     */

    @Test
    @DisplayName("Object#equals() should compare board sizes and states.")
    public void testEquals() {
        this.board = new Board(1000, 1000);
        Board board2 = new Board(1000, 999);
        assertNotEquals(board, board2);

        board2 = new Board(1000, 1000);
        assertEquals(board, board2);

        board2.set(123, 321, true);
        assertNotEquals(board, board2);
    }

    @Test
    @DisplayName("Set method should change value correctly.")
    public void testSetters() {
        this.board = new Board(1000, 1000);

        board.set(123, 321, true);
        board.set(321, 123, false);
        assertTrue(board.state(123, 321));
        assertFalse(board.state(321, 123));
    }

    @Test
    @DisplayName("Board#set<On/Off>() methods should change value correctly.")
    public void testOnOff() {
        this.board = new Board(1000, 1000);

        board.setOn(123, 321);
        board.setOff(321, 123);
        assertTrue(board.state(123, 321));
        assertFalse(board.state(321, 123));

        board.setOff(123, 321);
        board.setOn(321, 123);
        assertFalse(board.state(123, 321));
        assertTrue(board.state(321, 123));
    }

    /*
     * Error tests.
     */

    @Test
    @DisplayName("Attempting to fetch the state from a non-cell should throw an error.")
    public void testNegativeCoordinates() {
        this.board = new Board(1000);

        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.state(-1, 0);
        });
        assertEquals("Invalid cell-coordinates: [-1, 0].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.state(0, -1);
        });
        assertEquals("Invalid cell-coordinates: [0, -1].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.state(-1, -1);
        });
        assertEquals("Invalid cell-coordinates: [-1, -1].",
                exception.getMessage());
    }

    @Test
    @DisplayName("Attempting to fetch the state from a non-cell should throw an error.")
    public void testLargeCoordinates() {
        this.board = new Board(1000);
        int height = board.height();
        int width = board.width();

        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.state(height, 0);
        });
        assertEquals("Invalid cell-coordinates: [" + height + ", 0].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.state(0, width);
        });
        assertEquals("Invalid cell-coordinates: [0, " + width + "].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            board.state(height, width);
        });
        assertEquals(
                "Invalid cell-coordinates: [" + height + ", " + width + "].",
                exception.getMessage());
    }

    @Test
    @DisplayName("Errors should be thrown using negative or 0 dimensions.")
    public void testInvalidDimensions() {

        exception = assertThrows(IllegalArgumentException.class, () -> {
            this.board = new Board(-1);
        });
        assertEquals(
                "Boards can only have non-zero positive dimensions; can't use [-1, -1].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            this.board = new Board(10, -1);
        });
        assertEquals(
                "Boards can only have non-zero positive dimensions; can't use [10, -1].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            this.board = new Board(-1, 10);
        });
        assertEquals(
                "Boards can only have non-zero positive dimensions; can't use [-1, 10].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            this.board = new Board(0);
        });
        assertEquals(
                "Boards can only have non-zero positive dimensions; can't use [0, 0].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            this.board = new Board(10, 0);
        });
        assertEquals(
                "Boards can only have non-zero positive dimensions; can't use [10, 0].",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            this.board = new Board(0, 10);
        });
        assertEquals(
                "Boards can only have non-zero positive dimensions; can't use [0, 10].",
                exception.getMessage());
    }

}