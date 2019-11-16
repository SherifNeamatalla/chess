package oy.chess.controller.gamelogic.movechecking.moveavailability;

import oy.chess.controller.gamelogic.movechecking.helper.CastlingChecker;
import oy.chess.util.CellUtilHelper;
import oy.chess.util.GameUtilHelper;
import oy.chess.controller.gamelogic.movecalculating.verifiers.KingCheckVerifier;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;

import java.util.List;

 class KingMoveAvailabilityChecker {
   static boolean checkAvailability(Move move, Piece chosenPiece, Game game, List<Piece> currentPlayerPieces) {

    if (CastlingChecker.isCastling(move, chosenPiece, game)) {
      int x = move.getNewPosition().getX();
      int minY = Math.min(chosenPiece.getPosition().getY(), move.getNewPosition().getY());
      int maxY = Math.max(chosenPiece.getPosition().getY(), move.getNewPosition().getY());

      List<Piece> idlePlayerPieces = GameUtilHelper.getIdlePlayerPieces(game);

      // This is possible because we're lucky we have a universe with maths such as ours, maths is awesome!
      for (int currentY = minY; currentY < maxY; currentY++) {

        // If king is being checked pass it.
        if(currentY == chosenPiece.getPosition().getY())continue;

        // Checks if the current cell is check.
        if (KingCheckVerifier.positionIsCheck(new Position(x, currentY), idlePlayerPieces, game)) return false;

        // Checks if the current cell is already occupied by another allied piece.
        if (!CellUtilHelper.cellIsAvailable(new Position(x, currentY), currentPlayerPieces)) return false;

      }

      return true;
    }
    return CellUtilHelper.cellIsAvailable(move.getNewPosition(), currentPlayerPieces);
  }

}
