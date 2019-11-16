package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.ColumnsValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.RowsValidator;
import oy.chess.model.move.Move;

 class RookMoveValidator {
   static boolean validateMove(Move move) {
    return ColumnsValidator.isSameColumn(move) || RowsValidator.isSameRow(move);
  }
}
