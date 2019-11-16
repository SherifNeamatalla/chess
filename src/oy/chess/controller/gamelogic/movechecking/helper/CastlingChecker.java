package oy.chess.controller.gamelogic.movechecking.helper;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;

import java.util.Optional;

public class CastlingChecker {
  public static boolean isCastling(Move move, Piece chosenPiece, Game game) {
    if (chosenPiece.hasMoved()) return false;

    // Castling to left.
    if (move.getNewPosition().getY() == 1) {
      Optional<Piece> rookOptional =
          PieceFinder.findPiece(new Position(chosenPiece.getPosition().getX(), 0), game);

      if (rookOptional.isPresent()) {
        Piece rook = rookOptional.get();

        return rook.getPieceType() == PieceType.ROOK
            && rook.getOwnerPlayerColor() == chosenPiece.getOwnerPlayerColor()
            && !rook.hasMoved();
      }

    }
    // Castling to right.
    else if (move.getNewPosition().getY() == 6) {

      Optional<Piece> rookOptional =
          PieceFinder.findPiece(new Position(chosenPiece.getPosition().getX(), 7), game);

      if (rookOptional.isPresent()) {
        Piece rook = rookOptional.get();

        return rook.getPieceType() == PieceType.ROOK
            && rook.getOwnerPlayerColor() == chosenPiece.getOwnerPlayerColor()
            && !rook.hasMoved();
      }
    }

    return false;
  }
}
