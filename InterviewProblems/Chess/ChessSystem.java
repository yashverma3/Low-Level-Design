import entities.Board;
import entities.Cell;
import entities.Player;
import enums.Color;
import enums.GameStatus;
import factory.Piece;

public class ChessSystem {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;
    private GameStatus status;

    private static ChessSystem instance;

    public static ChessSystem getInstance() {
        if (instance == null) {
            instance = new ChessSystem();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    private ChessSystem() {
        this.board = new Board();
        this.whitePlayer = new Player("White Player", Color.WHITE);
        this.blackPlayer = new Player("Black Player", Color.BLACK);
        this.currentPlayer = this.whitePlayer;
        this.status = GameStatus.ACTIVE;
    }

    public boolean makeMove(int sourceRow, int sourceColumn, int destinationRow, int destinationColumn) {
        while(status == GameStatus.ACTIVE) {
            Cell source = board.getCell(sourceRow, sourceColumn);
            Cell destination = board.getCell(destinationRow, destinationColumn);
    
            Piece sourcePiece = source.getPiece();
    
            if(sourcePiece == null || sourcePiece.getColor() != currentPlayer.getColor()) {
                System.out.println("Invalid move.");
                return false;
            }
    
            if(!sourcePiece.canMove(board, source, destination)) {
                System.out.println("Invalid move.");
                return false;
            }
    
            if (moveLeavesKingInCheck(source, destination)) {
                System.out.println("Move leaves your king in check.");
                return false;
            }
    
            destination.setPiece(sourcePiece);
            source.setPiece(null);
    
            updateStatus();

            if(status == GameStatus.WHITE_WIN) {
                System.out.println("Checkmate, White wins!");
            } else if(status == GameStatus.BLACK_WIN) {
                System.out.println("Checkmate, Black wins!");
            } else if(status == GameStatus.STALEMATE) {
                System.out.println("Stalemate!");
            }

            switchTurn();
        }
        return true;
    }

    public GameStatus getStatus()        { return status; }
    public Player     getCurrentPlayer() { return currentPlayer; }
    public Board      getBoard()         { return board; }

    private boolean moveLeavesKingInCheck(Cell source, Cell destination) {
        Piece sourcePiece = source.getPiece();
        Piece destinationPiece = destination.getPiece();

        // Make move
        destination.setPiece(sourcePiece);
        source.setPiece(null);

        boolean inCheck = kingInCheck(currentPlayer.getColor());

        // Undo move
        destination.setPiece(destinationPiece);
        source.setPiece(sourcePiece);

        return inCheck;
    }

    private boolean kingInCheck(Color color) {
        Cell kingCell = board.findKing(color);

        for(int r=0;r<board.getSize();r++) {
            for(int c=0;c<board.getSize();c++) {
                Cell cell = board.getCell(r, c);
                Piece piece = cell.getPiece();
                if(piece!=null && piece.getColor()!=color && piece.canMove(board, cell, kingCell)) {
                    System.out.println("This move leaves your king in check!!");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasAnyLegalMove(Color color) {
        for(int r=0;r<board.getSize();r++) {
            for(int c=0;c<board.getSize();c++) {
                Cell cell = board.getCell(r, c);
                Piece piece = cell.getPiece();

                if(piece==null || piece.getColor()!=color) continue;

                for(int dr=0;dr<board.getSize();dr++) {
                    for(int dc=0;dc<board.getSize();dc++) {
                        if(!piece.canMove(board, cell, board.getCell(dr, dc))) continue;

                        Cell source = board.getCell(r, c);
                        Cell destination = board.getCell(dr, dc);
                        Piece captured = destination.getPiece();

                        // Make move
                        destination.setPiece(piece);
                        source.setPiece(null);

                        boolean inCheck = kingInCheck(color);

                        // Undo move
                        destination.setPiece(captured);
                        source.setPiece(piece);

                        if(!inCheck) return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateStatus() {
        Color opponent = currentPlayer.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        boolean opponentInCheck = kingInCheck(opponent);
        boolean opponentHasMoves = hasAnyLegalMove(opponent);

        if (!opponentHasMoves) {
            if(opponentInCheck) {
                status = currentPlayer.getColor() == Color.WHITE ? GameStatus.WHITE_WIN : GameStatus.BLACK_WIN;
            } else {
                status = GameStatus.STALEMATE;
            }
        } else if (opponentInCheck) {
            System.out.println("Check!");
        }
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

}
