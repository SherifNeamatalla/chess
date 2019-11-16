package oy.chess.controller.gamelogic.movechecking.moveavailability;

import oy.chess.controller.gamelogic.movechecking.moveavailability.helper.DiagonalAvailabilityChecker;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

class BishopMoveAvailabilityChecker {
  static boolean checkAvailability(
          Move move, List<Piece> currentPlayerPieces, List<Piece> idlePlayerPieces) {
    return DiagonalAvailabilityChecker.diagonalIsAvailable(move, currentPlayerPieces, true)
            && DiagonalAvailabilityChecker.diagonalIsAvailable(move, idlePlayerPieces, false);
  }
}
