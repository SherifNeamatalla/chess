package oy.chess.controller.gamelogic.movechecking.moveavailability;

import oy.chess.controller.gamelogic.movechecking.moveavailability.helper.ColumnAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.moveavailability.helper.RowAvailabilityChecker;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

 class RookMoveAvailabilityChecker {
   static boolean checkAvailability(
      Move move, List<Piece> currentPlayerPieces, List<Piece> idlePlayerPieces) {

    if (move.getNewPosition().getY() == move.getOldPosition().getY()) {
      return ColumnAvailabilityChecker.columnIsAvailable(move, currentPlayerPieces, true)
          && ColumnAvailabilityChecker.columnIsAvailable(move, idlePlayerPieces, false);
    }

    return RowAvailabilityChecker.rowIsAvailable(move, currentPlayerPieces, true)
        && RowAvailabilityChecker.rowIsAvailable(move, idlePlayerPieces, false);
  }
}
