
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TestClass {

    private TttGrid grid;
    private String startingState;

    @BeforeEach
    void setup() {
        grid = new TttGrid(3);
        startingState = "e | e | e\n" +
                        "- - - - -\n" +
                        "e | e | e\n" +
                        "- - - - -\n" +
                        "e | e | e";
    }

    @Test
    void initialGridIsEqualTo_startingState() {
        String initialGrid = grid.toString();
        assertEquals(startingState, initialGrid);
    }

    @Test
    void addingIndexOutOfBoundsMeansMatrixStaysEmpty() {
        grid.fillCell(-1, -1);
        grid.fillCell(-1, 1);
        grid.fillCell(1,-1);
        grid.fillCell(-1, 1);
        grid.fillCell(1,4);
        grid.fillCell(4,1);
        grid.fillCell(-1,5);
        grid.fillCell(5,-2);

        String newState = grid.toString();
        assertEquals(startingState, newState);
    }

    @Test
    void currentSymbolChangesOnlyAfterValidInput() {
        assertEquals(grid.getCurrentSymbol(), "x");
        grid.fillCell(1,1);
        assertEquals(grid.getCurrentSymbol(), "o");
        grid.fillCell(0,0);
        assertEquals(grid.getCurrentSymbol(), "x");
        grid.fillCell(1,0);
        assertEquals(grid.getCurrentSymbol(), "o");
        grid.fillCell(1,1);
        assertEquals(grid.getCurrentSymbol(), "o");
        grid.fillCell(0,0);
        assertEquals(grid.getCurrentSymbol(), "o");
        grid.fillCell(2,2);
        assertEquals(grid.getCurrentSymbol(), "x");
    }

    @Test
    void cannotOverwriteFilledCells() {
        grid.fillCell(1, 1);
        String filledState = grid.toString();
        String expectedState =
                "e | e | e\n" +
                "- - - - -\n" +
                "e | x | e\n" +
                "- - - - -\n" +
                "e | e | e";
        grid.fillCell(1,1);
        String newState = grid.toString();
        assertEquals(expectedState, newState);
    }

    @Test
    void winHorizontally() {
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            TttGrid testGrid = new TttGrid(3);
            check = false;
            for (int j = 0; j < 3; j++) {
                check = testGrid.fillCell(i, j);
                testGrid.fillCell((i + 1) % 2, j);
            }
            assertTrue(check);
        }
    }


    @Test
    void winVertically() {
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            TttGrid testGrid = new TttGrid(3);
            check = false;
            for (int j = 0; j < 3; j++) {
                check = testGrid.fillCell(j, i);
                testGrid.fillCell(j, (i + 1) % 2);
            }
            assertTrue(check);
        }
    }

    @Test
    void winFallingDiagonally() {
        grid.fillCell(1,0);
        grid.fillCell(0,0);
        grid.fillCell(1,2);
        grid.fillCell(1,1);
        grid.fillCell(0, 1);
        assertTrue(grid.fillCell(2,2));
    }

    @Test
    void winRisingDiagonally() {
        grid.fillCell(0,0);
        grid.fillCell(2,0);
        grid.fillCell(2,2);
        grid.fillCell(1,1);
        grid.fillCell(1,0);
        assertTrue(grid.fillCell(0,2));
    }

    @Test
    void noOneWinsWhenGameHasReachedAnUnwinnableState() {
        grid.fillCell(0, 0);
        grid.fillCell(1,1);
        assertFalse(grid.fillCell(2,2));
        assertFalse(grid.fillCell(0,2));
        assertFalse(grid.fillCell(0,1));
        assertFalse(grid.fillCell(2,1));
        assertFalse(grid.fillCell(2,0));
        assertFalse(grid.fillCell(1,0));
        assertFalse(grid.fillCell(1,2));
    }
}
