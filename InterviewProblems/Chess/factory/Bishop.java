package factory;

import enums.Color;
import entities.Board;
import entities.Cell;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        if (to.getPiece() != null && to.getPiece().getColor() == color) return false;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getColumn() - from.getColumn());

        // Must move diagonally
        if (rowDiff != colDiff) return false;

        // Check no piece is blocking the diagonal path
        int rowDir = (to.getRow() - from.getRow()) > 0 ? 1 : -1;
        int colDir = (to.getColumn() - from.getColumn()) > 0 ? 1 : -1;

        int r = from.getRow() + rowDir;
        int c = from.getColumn() + colDir;
        while (r != to.getRow()) {
            if (board.getCell(r, c).getPiece() != null) return false;
            r += rowDir;
            c += colDir;
        }

        return true;
    }

    @Override
    public String getSymbol() { return "B"; }
}
