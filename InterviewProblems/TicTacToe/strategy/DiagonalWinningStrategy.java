package InterviewProblems.TicTacToe.strategy;

import InterviewProblems.TicTacToe.entities.Board;
import InterviewProblems.TicTacToe.enums.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();

        // Main diagonal (top-left to bottom-right)
        boolean mainDiagonalWin = true;
        for (int i=0; i<size; i++) {
            if (board.getCell(i,i).getSymbol() != symbol) {
                mainDiagonalWin = false;
                break;
            }
        }
        if (mainDiagonalWin) return true;

        // Anti-diagonal (top-right to bottom-left)
        for (int i=0; i<size; i++) {
            if (board.getCell(i,size-i-1).getSymbol() != symbol) {
                return false;
            }
        }

        return true;
    }
}