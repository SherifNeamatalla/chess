package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.DiagonalsValidator;
import oy.chess.model.move.Move;

class BishopMoveValidator {
  static boolean validateMove(Move move) {
    return DiagonalsValidator.isSameDiagonal(move);
  }
}
