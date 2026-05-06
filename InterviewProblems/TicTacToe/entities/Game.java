package InterviewProblems.TicTacToe.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import InterviewProblems.TicTacToe.enums.GameStatus;
import InterviewProblems.TicTacToe.enums.Symbol;
import InterviewProblems.TicTacToe.observer.GameObserver;
import InterviewProblems.TicTacToe.strategy.ColumnWinningStrategy;
import InterviewProblems.TicTacToe.strategy.DiagonalWinningStrategy;
import InterviewProblems.TicTacToe.strategy.RowWinningStrategy;
import InterviewProblems.TicTacToe.strategy.WinningStrategy;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;
    private GameStatus status;
    private final List<WinningStrategy> winningStrategies;
    private final List<GameObserver> observers;

    public Game(Player player1, Player player2, int boardSize) {
        this.board = new Board(boardSize);
        this.players = new Player[]{player1, player2};
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
        this.winningStrategies = initializeStrategies();
        this.observers = new CopyOnWriteArrayList<>();
    }

    private List<WinningStrategy> initializeStrategies() {
        List<WinningStrategy> strategies = new ArrayList<>();
        strategies.add(new RowWinningStrategy());
        strategies.add(new ColumnWinningStrategy());
        strategies.add(new DiagonalWinningStrategy());
        return strategies;
    }

    public synchronized void makeMove(int row, int col) {
        // Validate Move
        if(!board.isCellEmpty(row,col)) {
            throw new Error("Invalid move");
        }

        // Make Move
        Player currentPlayer = players[currentPlayerIndex];
        board.placeSymbol(row,col,currentPlayer.getSymbol());

        // Check Win
        if(checkWin(row,col,currentPlayer.getSymbol())) {
            status = (currentPlayer.getSymbol() == Symbol.X) ? GameStatus.WINNER_X : GameStatus.WINNER_O;
            notifyObservers();
            return;
        }

        // Check Draw
        if(board.isFull()) {
            status = GameStatus.DRAW;
            notifyObservers();
            return;
        }

        // Switch to next player
        currentPlayerIndex = 1 - currentPlayerIndex;
    }

    private boolean checkWin(int row, int col, Symbol symbol) {
        for (WinningStrategy strategy: winningStrategies) {
            if (strategy.checkWin(board,row,col,symbol)) {
                return true;
            }
        }
        return false;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update(this);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getWinner() {
        if (status == GameStatus.WINNER_X) {
            return players[0].getSymbol() == Symbol.X ? players[0] : players[1];
        } else if (status == GameStatus.WINNER_O) {
            return players[0].getSymbol() == Symbol.O ? players[0] : players[1];
        }
        return null;
    }

    public void printBoard() {
        board.printBoard();
    }
}
