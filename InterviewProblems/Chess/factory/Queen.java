package factory;

import enums.Color;
import entities.Board;
import entities.Cell;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell from, Cell to) {
        // Queen = Rook + Bishop combined
        return new Rook(color).canMove(board, from, to)
            || new Bishop(color).canMove(board, from, to);
    }

    @Override
    public String getSymbol() { return "Q"; }
}
