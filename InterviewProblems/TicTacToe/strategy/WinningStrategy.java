package InterviewProblems.TicTacToe.strategy;

import InterviewProblems.TicTacToe.entities.Board;
import InterviewProblems.TicTacToe.enums.Symbol;

public interface WinningStrategy {
    boolean checkWin(Board board, int row, int col, Symbol symbol);
}
