package entities;

import enums.Color;
import factory.King;
import factory.Piece;
import factory.PieceFactory;

public class Board {
    private final int size = 8;
    private final Cell[][] grid;

    public Board() {
        grid = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for(int r=0;r<size;r++) {
            for(int c=0;c<size;c++) {
                grid[r][c] = new Cell(r,c);
            }
        }

        // Pawns
        for(int c=0;c<size;c++) {
            grid[1][c].setPiece(PieceFactory.createPiece("PAWN", Color.WHITE));
            grid[6][c].setPiece(PieceFactory.createPiece("PAWN", Color.BLACK));
        }

        // Other pieces
        String[] piecesOrder = {"ROOK", "KNIGHT", "BISHOP", "QUEEN", "KING", "BISHOP", "KNIGHT", "ROOK"};
        for(int c=0;c<size;c++) {
            grid[0][c].setPiece(PieceFactory.createPiece(piecesOrder[c], Color.WHITE));
            grid[7][c].setPiece(PieceFactory.createPiece(piecesOrder[c], Color.BLACK));
        }
    }

    public int getSize() {
        return this.size;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public Cell findKing(Color color) {
        for(int r=0;r<size;r++) {
            for(int c=0;c<size;c++) {
                Piece p = grid[r][c].getPiece();
                if(p instanceof King && p.getColor() == color) {
                    return grid[r][c];
                }
            }
        }
        return null;
    }

    public void display() {
        System.out.println("    0    1    2    3    4    5    6    7 ");
        System.out.println("  +----+----+----+----+----+----+----+----+");
        for (int r = size - 1; r >= 0; r--) {
            System.out.print(r + " |");
            for (int c = 0; c < size; c++)
                System.out.printf(" %-2s |" , grid[r][c]);
            System.out.println();
            System.out.println("  +----+----+----+----+----+----+----+----+");
        }
    }
}
