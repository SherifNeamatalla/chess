package oy.chess.controller.gamelogic.movechecking.moveavailability.helper;

import oy.chess.util.CellUtilHelper;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.DiagonalsValidator;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

public class DiagonalAvailabilityChecker {
  public static boolean diagonalIsAvailable(Move move, List<Piece> pieces, boolean isAlliedPieces) {

    // I just thought this on paper, if this works am gonna be pretty happy going to sleep tonight.
    for (Piece piece : pieces) {

      // If it's the piece we are observing no need to check it.
      if (!piece.getPosition().equals(move.getOldPosition())) {

        int newToOldX = move.getNewPosition().getX() - move.getOldPosition().getX();
        int newToOldY = move.getNewPosition().getY() - move.getOldPosition().getY();

        int newToPieceX = move.getNewPosition().getX() - piece.getPosition().getX();
        int newToPieceY = move.getNewPosition().getY() - piece.getPosition().getY();

        int pieceToOldX = piece.getPosition().getX() - move.getOldPosition().getX();
        int pieceToOldY = piece.getPosition().getY() - move.getOldPosition().getY();

        if (DiagonalsValidator.checkRightUp(newToOldX, newToOldY)
            && DiagonalsValidator.checkRightUp(newToPieceX, newToPieceY)
            && DiagonalsValidator.checkRightUp(pieceToOldX, pieceToOldY)) return false;
        if (DiagonalsValidator.checkLeftUp(newToOldX, newToOldY)
            && DiagonalsValidator.checkLeftUp(newToPieceX, newToPieceY)
            && DiagonalsValidator.checkLeftUp(pieceToOldX, pieceToOldY)) return false;
        if (DiagonalsValidator.checkLeftDown(newToOldX, newToOldY)
            && DiagonalsValidator.checkLeftDown(newToPieceX, newToPieceY)
            && DiagonalsValidator.checkLeftDown(pieceToOldX, pieceToOldY)) return false;
        if (DiagonalsValidator.checkRightDown(newToOldX, newToOldY)
            && DiagonalsValidator.checkRightDown(newToPieceX, newToPieceY)
            && DiagonalsValidator.checkRightDown(pieceToOldX, pieceToOldY)) return false;
      }
    }
    if (isAlliedPieces) return CellUtilHelper.cellIsAvailable(move.getNewPosition(), pieces);

    return true;
  }
}
