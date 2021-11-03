import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //String input = "3";
        //Scanner scanner = new Scanner(input);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter grid size:");
        int n = Integer.valueOf(scanner.nextLine());

        TttGrid grid = new TttGrid(n);
        System.out.println(grid.toString());

    }
}
