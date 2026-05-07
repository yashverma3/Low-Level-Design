package factory;

import entities.Board;
import entities.Cell;
import enums.Color;

public abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() { return color; }

    public boolean isWhite() { return color == Color.WHITE; }

    public abstract boolean canMove(Board board, Cell from, Cell to);

    public abstract String getSymbol();

    @Override
    public String toString() {
        // Display piece with color: 'wK' for white King, 'bK' for black King
        char colorChar = (color == Color.WHITE) ? 'w' : 'b';
        return colorChar + getSymbol();
    }
}
