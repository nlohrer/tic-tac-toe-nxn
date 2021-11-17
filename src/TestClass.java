
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TestClass {

    private TttGrid grid;
    private String startingState;

    @BeforeEach
    void setup() {
        grid = new TttGrid(3);
        startingState = grid.toString();
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
        assertTrue(startingState.equals(newState));
    }
}
