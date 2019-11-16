package oy.chess.util;

import oy.chess.model.piece.Piece;

public class PieceUtilHelper {

  public static Piece copy(Piece oldPiece) {
    return new Piece(
        oldPiece.getPieceID(),
        PositionUtilHelper.copy(oldPiece.getPosition()),
        oldPiece.getPieceType(),
        oldPiece.getOwnerPlayerColor(),
        oldPiece.hasMoved(),
        oldPiece.getNumberOfMoves(),
        oldPiece.getNumberOfTurnsSinceFirstMove());
  }
}
