import enums.GameStatus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChessSystem game = ChessSystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Chess Game ===");
        System.out.println("Move format: fromRow fromCol toRow toCol   |   Type 'quit' to exit");
        System.out.println();
        game.getBoard().display();

        while (game.getStatus() == GameStatus.ACTIVE) {
            System.out.println("\n" + game.getCurrentPlayer() + "'s turn:");
            System.out.print("Move> ");

            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("quit")) {
                System.out.println("Game aborted.");
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 4) {
                System.out.println("Please enter exactly 4 numbers: fromRow fromCol toRow toCol");
                continue;
            }

            try {
                int fromRow = Integer.parseInt(parts[0]);
                int fromCol = Integer.parseInt(parts[1]);
                int toRow   = Integer.parseInt(parts[2]);
                int toCol   = Integer.parseInt(parts[3]);

                game.makeMove(fromRow, fromCol, toRow, toCol);
                game.getBoard().display();

            } catch (NumberFormatException e) {
                System.out.println("Invalid input — use numbers only.");
            } catch (IllegalArgumentException e) {
                System.out.println("Out of bounds: " + e.getMessage());
            }
        }

        if (game.getStatus() != GameStatus.ACTIVE) {
            System.out.println("\nFinal status: " + game.getStatus());
        }

        scanner.close();
    }
}
