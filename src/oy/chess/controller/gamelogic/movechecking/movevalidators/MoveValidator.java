package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

public class MoveValidator {
  public static boolean moveIsValid(Move move, Piece chosenPiece,Game game) {

    boolean isValid = false;
    switch (chosenPiece.getPieceType()) {
      case KING:
        isValid = KingMoveValidator.validateMove(move,chosenPiece,game);
        break;
      case PAWN:
        isValid = PawnMoveValidator.validateMove(move,chosenPiece,game);
        break;
      case ROOK:
        isValid = RookMoveValidator.validateMove(move);
        break;
      case QUEEN:
        isValid = QueenMoveValidator.validateMove(move);
        break;
      case BISHOP:
        isValid = BishopMoveValidator.validateMove(move);
        break;
      case KNIGHT:
        isValid = KnightMoveValidator.validateMove(move);
        break;
      default:
        return isValid;
    }
    return isValid;
  }
}
