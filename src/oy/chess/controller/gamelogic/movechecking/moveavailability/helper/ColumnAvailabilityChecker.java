package oy.chess.controller.gamelogic.movechecking.moveavailability.helper;

import oy.chess.util.CellUtilHelper;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

public class ColumnAvailabilityChecker {
  public static boolean columnIsAvailable(Move move, List<Piece> pieces, boolean isAlliedPieces) {
    int biggerX = Math.max(move.getOldPosition().getX(), move.getNewPosition().getX());

    int smallerX = Math.min(move.getOldPosition().getX(), move.getNewPosition().getX());

    // Checks the way between the 2 positions ( the 2 positions are excluded i.e source cell and
    // goal cell ).
    boolean wayIsEmpty = wayIsEmpty(biggerX, smallerX, move, pieces);
    if (!wayIsEmpty) return false;

    if (isAlliedPieces) return CellUtilHelper.cellIsAvailable(move.getNewPosition(), pieces);

    return true;
  }

  private static boolean wayIsEmpty(int biggerX, int smallerX, Move move, List<Piece> pieces) {

    for (Piece p : pieces) {
      if (((p.getPosition().getY() == move.getOldPosition().getY()
          && !p.getPosition().equals(move.getOldPosition())
          && p.getPosition().getX() < biggerX
          && p.getPosition().getX() > smallerX))) {
        return false;
      }
    }

    return true;
  }
}
