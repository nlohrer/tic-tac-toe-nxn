public class TttGrid {

    private String[][] grid;
    private String currentSymbol;      //this is either x or o

    private int[] filledRows;               //these arrays/ints indicate how many cells in a given row, column,
    private int[] filledColumns;            //or diagonal have been filled already. Once any entry reaches n,
    private int filledDiagonalFalling;      //the program will check whether all symbols within the row, column,
    private int filledDiagonalRising;       //or diagonal are the same

    public TttGrid(int size) {                  //initializes a grid of size n x n; doesn't work for n <= 0
        String[][] grid = new String[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = "e";
            }
        }
        this.grid = grid;

        this.filledRows = new int[size];
        this.filledColumns = new int[size];
        this.filledDiagonalFalling = 0;
        this.filledDiagonalRising = 0;

        this.currentSymbol = "x";      //x gets the first turn
    }

    public boolean fillCell(int i, int j) {        //fill cell at specified position with current symbol, returns true if the game has reached a winning state thereafter

        if (i < 0 || j < 0 || i > this.grid.length - 1 || j > this.grid.length - 1) {       //check whether the specified cell exists within the grid
            System.out.println("Out of bounds!");
            return false;
        }

        if (!this.grid[i][j].equals("e")) {         //check whether the cell is still empty
            System.out.println("Cell has already been filled!");
            return false;
        }
        this.grid[i][j] = this.currentSymbol;

        if (hasWon(i, j)) {
            return true;
        }

        this.changeSymbol();

        return false;
    }


    public boolean hasWon(int i, int j) {   //method to fill the "filled" arrays and determine whether someone has won
        this.filledRows[i]++;           //increases the amount of filled cells within the given row, column, and diagonal (if applicable) by 1
        if (this.filledRows[i] == this.grid.length && checkRowForWinner(i)) {       //checks whether all cells in a given row, column, or diagonal have been filled. If so, this will return true;
            return true;
        }
        this.filledColumns[j]++;
        if (this.filledColumns[j] == this.grid.length && checkColumnForWinner(j)) {
            return true;
        }

        if (i == j) {
            this.filledDiagonalFalling++;
            if (this.filledDiagonalFalling == this.grid.length && checkDiagonalFallingForWinner()) {
                return true;
            }
        }
        if (i + j == this.grid.length - 1) {
            this.filledDiagonalRising++;
            if (this.filledDiagonalRising == this.grid.length && checkDiagonalRisingForWinner()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRowForWinner(int i) {
        String potentialWinner = this.grid[i][0];
        for (String rowEntry : this.grid[i]) {      //goes through all entries in the given row and checks whether they are all equal;
            if (!rowEntry.equals(potentialWinner)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumnForWinner(int j) {
        String potentialWinner = this.grid[0][j];
        for (int row = 0; row < this.grid.length; row++) {
            if (!this.grid[row][j].equals(potentialWinner)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonalFallingForWinner() {
        String potentialWinner = this.grid[0][0];
        for (int index = 0; index < this.grid.length; index++) {
            if (!this.grid[index][index].equals(potentialWinner)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonalRisingForWinner() {
        String potentialWinner = this.grid[0][this.grid.length - 1];
        for (int index = 0; index < this.grid.length; index++) {
            int columnIndex = this.grid.length - (index + 1);
            if (!this.grid[index][columnIndex].equals(potentialWinner)) {
                return false;
            }
        }
        return true;
    }


    public String getCurrentSymbol() {
        return this.currentSymbol;
    }

    public void changeSymbol() {
        if (currentSymbol.equals("x")) {
            currentSymbol = "o";
        } else {
            currentSymbol = "x";
        }
    }

    @Override
    public String toString() {
        String toPrint = "";
        for (int i = 0; i <= this.grid.length - 2; i++) {
            for (int j = 0; j <= this.grid.length - 2; j++) {
                toPrint += this.grid[i][j] + " | ";
            }
            toPrint += this.grid[i][this.grid.length - 1] + "\n";
            for (int k = 1; k <= this.grid.length * 2 - 2; k++) {
                toPrint += "- ";
            }
            toPrint += "-\n";
        }

        for (int j = 0; j <= this.grid.length - 2; j++) {
            toPrint += this.grid[this.grid.length - 1][j] + " | ";
        }
        toPrint += this.grid[this.grid.length - 1][this.grid.length - 1];
        return toPrint;
    }
}
