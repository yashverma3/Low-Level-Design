package InterviewProblems.TicTacToe;

import InterviewProblems.TicTacToe.entities.Game;
import InterviewProblems.TicTacToe.entities.Player;
import InterviewProblems.TicTacToe.enums.GameStatus;
import InterviewProblems.TicTacToe.observer.Scoreboard;

public class TicTacToeSystem {
    private static volatile TicTacToeSystem instance;
    private static final Object lock = new Object();

    private final Scoreboard scoreboard;
    private Game currentGame;

    private TicTacToeSystem() {
        scoreboard = new Scoreboard();
    }

    public static TicTacToeSystem getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new TicTacToeSystem();
                }
            }
        }
        return instance;
    }

    public Game createGame(Player player1, Player player2) {
        currentGame = new Game(player1, player2, 3);
        currentGame.addObserver(scoreboard);
        return currentGame;
    }

    public void makeMove(Player player, int row, int col) {
        currentGame.makeMove(row, col);
        currentGame.getBoard().printBoard();
    }

    public GameStatus getGameStatus() {
        return currentGame.getStatus();
    }

    public void printScoreboard() {
        scoreboard.printScoreboard();
    }
}
