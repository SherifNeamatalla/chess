package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.ColumnsValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.DiagonalsValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.RowsValidator;
import oy.chess.model.move.Move;

 class QueenMoveValidator {
   static boolean validateMove(Move move) {
    return DiagonalsValidator.isSameDiagonal(move)
        || RowsValidator.isSameRow(move)
        || ColumnsValidator.isSameColumn(move);
  }
}
