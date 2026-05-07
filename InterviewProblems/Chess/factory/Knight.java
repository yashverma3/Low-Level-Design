package factory;

import enums.Color;
import entities.Board;
import entities.Cell;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        if (to.getPiece() != null && to.getPiece().getColor() == color) return false;

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getColumn() - from.getColumn());

        // L-shape: (2,1) or (1,2) — knight jumps over pieces
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String getSymbol() { return "N"; }
}
