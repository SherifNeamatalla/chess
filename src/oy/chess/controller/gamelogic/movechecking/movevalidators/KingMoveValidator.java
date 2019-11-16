package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.controller.gamelogic.movecalculating.CastlingCalculator;
import oy.chess.controller.gamelogic.movechecking.helper.CastlingChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.ColumnsValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.DiagonalsValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.RowsValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

public class KingMoveValidator {
   static boolean validateMove(Move move, Piece chosenPiece, Game game) {

    return DiagonalsValidator.isSameDiagonal(move, 1)
        || RowsValidator.isSameRow(move, 1)
        || ColumnsValidator.isSameColumn(move, 1)
            || CastlingChecker.isCastling(move, chosenPiece, game);
  }

  public static boolean validateMoveWithNoCastling(Move move, Piece chosenPiece, Game game) {

    return DiagonalsValidator.isSameDiagonal(move, 1)
            || RowsValidator.isSameRow(move, 1)
            || ColumnsValidator.isSameColumn(move, 1);
  }

}
