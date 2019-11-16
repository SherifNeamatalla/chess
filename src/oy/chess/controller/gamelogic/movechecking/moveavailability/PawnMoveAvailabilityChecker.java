package oy.chess.controller.gamelogic.movechecking.moveavailability;

import oy.chess.controller.gamelogic.movechecking.helper.EnpassantChecker;
import oy.chess.util.CellUtilHelper;
import oy.chess.util.GameUtilHelper;
import oy.chess.controller.gamelogic.movechecking.moveavailability.helper.ColumnAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.ColumnsValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.PlayerColor;
import oy.chess.model.position.Position;

import java.util.List;

 class PawnMoveAvailabilityChecker {
   static boolean checkAvailability(Move move, List<Piece> currentPlayerPieces, Game game) {
    // Validity of move is not checked here, it is assumed that the move is valid and only
    // availability will be checked.
    int movingDirection = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 1 : -1;

    // One cell move.
    if (ColumnsValidator.isSameColumn(move, 1)
        && move.getOldPosition().getX() == move.getNewPosition().getX() + movingDirection)
      return ColumnAvailabilityChecker.columnIsAvailable(move, currentPlayerPieces, true)
          && ColumnAvailabilityChecker.columnIsAvailable(
              move, GameUtilHelper.getIdlePlayerPieces(game), true);

    // Two cell move.
    if (move.getOldPosition().getX() == move.getNewPosition().getX() + (2 * movingDirection))
      // Check first cell.
      return ColumnAvailabilityChecker.columnIsAvailable(move, currentPlayerPieces, true)
          && ColumnAvailabilityChecker.columnIsAvailable(
              move, GameUtilHelper.getIdlePlayerPieces(game), true)

          // Check 2nd cell.
          && ColumnAvailabilityChecker.columnIsAvailable(
              new Move(
                  move.getOldPosition(),
                  new Position(
                      move.getNewPosition().getX() + movingDirection,
                      move.getNewPosition().getY())),
              currentPlayerPieces,
              true)
          && ColumnAvailabilityChecker.columnIsAvailable(
              new Move(
                  move.getOldPosition(),
                  new Position(
                      move.getNewPosition().getX() + movingDirection,
                      move.getNewPosition().getY())),
              GameUtilHelper.getIdlePlayerPieces(game),
              true);

    if(EnpassantChecker.isEnpassant(move,game))
      return CellUtilHelper.cellIsAvailable(move.getNewPosition(), GameUtilHelper.getIdlePlayerPieces(game));

    return true;
  }
}
