public class TttGrid {

    private String[][] grid;
    private String currentSymbol;      //this is either x or o

    public TttGrid(int size) {                  //initializes a grid of size n x n; doesn't work for n <= 0
        String[][] grid = new String[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = "E";
            }
        }
        this.grid = grid;
        this.currentSymbol = "X";      //x gets the first turn
    }

    public void fillCell(int i, int j) {
        this.grid[i][j] = this.currentSymbol;
    }

    public String getCurrentSymbol() {
        return this.currentSymbol;
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
                toPrint += "_ ";
            }
            toPrint += "_\n";
        }

        for (int j = 0; j <= this.grid.length - 2; j++) {
            toPrint += this.grid[this.grid.length - 1][j] + " | ";
        }
        toPrint += this.grid[this.grid.length - 1][this.grid.length - 1];
        return toPrint;
    }
}
