package factory;

import enums.Color;

public class PieceFactory {
    private PieceFactory(){};

    public static Piece createPiece(String type, Color color) {
        switch(type.toUpperCase()) {
            case "PAWN": return new Pawn(color);
            case "ROOK": return new Rook(color);
            case "KNIGHT": return new Knight(color);
            case "BISHOP": return new Bishop(color);
            case "QUEEN": return new Queen(color);
            case "KING": return new King(color);
            default: throw new IllegalArgumentException("Unknown piece type: " + type);
        }
    }
}
