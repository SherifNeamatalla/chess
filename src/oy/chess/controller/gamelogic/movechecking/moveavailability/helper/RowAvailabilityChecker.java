package oy.chess.controller.gamelogic.movechecking.moveavailability.helper;

import oy.chess.util.CellUtilHelper;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

public class RowAvailabilityChecker {

  public static boolean rowIsAvailable(Move move, List<Piece> pieces, boolean isAlliedPieces) {

    int biggerY = Math.max(move.getOldPosition().getY(), move.getNewPosition().getY());

    int smallerY = Math.min(move.getOldPosition().getY(), move.getNewPosition().getY());

    // Checks the way between the 2 positions ( the 2 positions are excluded i.e source cell and
    // goal cell ).
    boolean wayIsEmpty = wayIsEmpty(biggerY, smallerY, move, pieces);
    if (!wayIsEmpty) return false;

    if (isAlliedPieces) return CellUtilHelper.cellIsAvailable(move.getNewPosition(), pieces);

    return true;
  }

  private static boolean wayIsEmpty(int biggerY, int smallerY, Move move, List<Piece> pieces) {

    for (int i = smallerY + 1; i < biggerY; i++) {
      for (Piece p : pieces) {
        if (((p.getPosition().getX() == move.getOldPosition().getX()
            && !p.getPosition().equals(move.getOldPosition())
            && p.getPosition().getY() > smallerY
            && p.getPosition().getY() < biggerY))) return false;
      }
    }

    return true;
  }
}
