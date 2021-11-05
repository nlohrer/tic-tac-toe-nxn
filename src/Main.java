import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //String input = "3";
        //Scanner scanner = new Scanner(input);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter grid size:");
        int n = Integer.valueOf(scanner.nextLine());

        TttGrid grid = new TttGrid(n);

        while (true) {
            System.out.println("\n" + grid.toString() + "\n");
            System.out.println(grid.getCurrentSymbol() + "'s turn");

            System.out.println("Which row? (input q quits)");
            String row = scanner.nextLine();
            if (row.equals("q")) {
                break;
            }
            System.out.println("Which column? (input q quits)");
            String column = scanner.nextLine();
            if (column.equals("q")) {
                break;
            }

            int i = Integer.valueOf(row) - 1;
            int j = Integer.valueOf(column) - 1;
            if (grid.fillCell(i, j)) {            //1 is subtracted from both the cow and the column value to make it easier to work with arrays
                System.out.println(grid.getCurrentSymbol() + " has won!");
                break;
            }


        }

        System.out.println("Thank you for playing!");

    }
}
