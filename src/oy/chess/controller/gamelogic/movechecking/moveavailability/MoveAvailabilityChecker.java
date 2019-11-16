package oy.chess.controller.gamelogic.movechecking.moveavailability;

import oy.chess.util.GameUtilHelper;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

public class MoveAvailabilityChecker {
    public static boolean moveIsAvailable(Move move, Piece chosenPiece, Game game) {


        List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);
        List<Piece> idlePlayerPieces = GameUtilHelper.getIdlePlayerPieces(game);


        boolean isAvailable = false;
        switch (chosenPiece.getPieceType()) {
            case KING:
                isAvailable = KingMoveAvailabilityChecker.checkAvailability(move, chosenPiece, game, currentPlayerPieces);
                break;
            case PAWN:
                isAvailable = PawnMoveAvailabilityChecker.checkAvailability(move, currentPlayerPieces,game);
                break;
            case ROOK:
                isAvailable = RookMoveAvailabilityChecker.checkAvailability(move, currentPlayerPieces, idlePlayerPieces);
                break;
            case QUEEN:
                isAvailable = QueenMoveAvailabilityChecker.checkAvailability(move, currentPlayerPieces, idlePlayerPieces);
                break;
            case BISHOP:
                isAvailable = BishopMoveAvailabilityChecker.checkAvailability(move, currentPlayerPieces, idlePlayerPieces);
                break;
            case KNIGHT:
                isAvailable = KnightMoveAvailabilityChecker.checkAvailability(move, currentPlayerPieces);
                break;
            default:
                return isAvailable;
        }
        return isAvailable;

    }
}
