package factory;

import enums.Color;
import entities.Board;
import entities.Cell;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        int fromRow = from.getRow(), fromCol = from.getColumn();
        int toRow   = to.getRow(),   toCol   = to.getColumn();

        int direction = isWhite() ? 1 : -1;   // WHITE moves up, BLACK moves down
        int startRow  = isWhite() ? 1 : 6;

        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);

        // One step forward into empty cell
        if (colDiff == 0 && rowDiff == direction && to.getPiece() == null)
            return true;

        // Two steps forward from starting position through empty cells
        if (colDiff == 0 && rowDiff == 2 * direction && fromRow == startRow
                && to.getPiece() == null
                && board.getCell(fromRow + direction, fromCol).getPiece() == null)
            return true;

        // Diagonal capture
        if (colDiff == 1 && rowDiff == direction
                && to.getPiece() != null && to.getPiece().getColor() != color)
            return true;

        return false;
    }

    @Override
    public String getSymbol() { return "P"; }
}
