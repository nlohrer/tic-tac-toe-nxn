
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
}
