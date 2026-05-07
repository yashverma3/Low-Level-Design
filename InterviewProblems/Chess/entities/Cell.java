package entities;

import factory.Piece;

public class Cell {
    private final int row;
    private final int column;
    private Piece piece;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.piece = null;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        // Return piece with consistent 2-character width
        if (piece == null) {
            return " .";  // Empty cell with consistent spacing
        }
        return piece.toString();
    }
}
