package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.controller.gamelogic.movechecking.helper.EnpassantChecker;
import oy.chess.util.CellUtilHelper;
import oy.chess.util.GameUtilHelper;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.ColumnsValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.DiagonalsValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.PlayerColor;

 class PawnMoveValidator {
   static boolean validateMove(Move move, Piece chosenPiece, Game game) {

    return isForwardMove(move, game) || isCaptureMove(move, game) || isDoubleForwardMove(move, chosenPiece, game) || EnpassantChecker.isEnpassant(move, game);
  }

  private static boolean isDoubleForwardMove(Move move, Piece chosenPiece, Game game) {
    int movingDirection = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 1 : -1;
    // If piece has moved then can't move double cells forward.
    if (chosenPiece.hasMoved())
      return false;

    // availability will not be checked here as this functionality is implemented in PawnAvailabilityChecker.


    return ColumnsValidator.isSameColumn(move, 2) && move.getOldPosition().getX() == move.getNewPosition().getX() + (movingDirection * 2);


  }

  private static boolean isCaptureMove(Move move, Game game) {

    int movingDirection = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 1 : -1;
    // If piece has moved then can't move double cells forward.


    return pawnWillCapture(move, game) && DiagonalsValidator.isSameDiagonal(move, 1) && move.getOldPosition().getX() == move.getNewPosition().getX() + movingDirection;
  }

  private static boolean isForwardMove(Move move, Game game) {
    int movingDirection = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 1 : -1;

    // availability will not be checked here as this functionality is implemented in PawnAvailabilityChecker.

    return ColumnsValidator.isSameColumn(move, 1) && move.getOldPosition().getX() == move.getNewPosition().getX() + movingDirection;
  }

  private static boolean pawnWillCapture(Move move, Game game) {

    return !CellUtilHelper.cellIsAvailable(move.getNewPosition(), GameUtilHelper.getIdlePlayerPieces(game));
  }
}
