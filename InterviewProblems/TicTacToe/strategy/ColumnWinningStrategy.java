package InterviewProblems.TicTacToe.strategy;

import InterviewProblems.TicTacToe.entities.Board;
import InterviewProblems.TicTacToe.enums.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        for (int r=0; r<size; r++) {
            if (board.getCell(r,col).getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
