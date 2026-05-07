package factory;

import enums.Color;
import entities.Board;
import entities.Cell;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        if (to.getPiece() != null && to.getPiece().getColor() == color) return false;

        int fromRow = from.getRow(), fromCol = from.getColumn();
        int toRow   = to.getRow(),   toCol   = to.getColumn();

        // Must move in straight line (same row or same column)
        if (fromRow != toRow && fromCol != toCol) return false;

        // Check no piece is blocking the path
        if (fromRow == toRow) {
            int minCol = Math.min(fromCol, toCol);
            int maxCol = Math.max(fromCol, toCol);
            for (int c = minCol + 1; c < maxCol; c++)
                if (board.getCell(fromRow, c).getPiece() != null) return false;
        } else {
            int minRow = Math.min(fromRow, toRow);
            int maxRow = Math.max(fromRow, toRow);
            for (int r = minRow + 1; r < maxRow; r++)
                if (board.getCell(r, fromCol).getPiece() != null) return false;
        }

        return true;
    }

    @Override
    public String getSymbol() { return "R"; }
}
