package oy.chess.controller.gamelogic.movechecking.moveavailability;

import oy.chess.util.CellUtilHelper;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.List;

 class KnightMoveAvailabilityChecker {
   static boolean checkAvailability(Move move, List<Piece> currentPlayerPieces) {

    return CellUtilHelper.cellIsAvailable(move.getNewPosition(), currentPlayerPieces);
  }
}
