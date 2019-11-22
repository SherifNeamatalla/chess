package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

public class MoveCalculator {

  public static Game calculateNormalMove(Move move, Game game) {

    return MoveCalculatorsManager.doMove(move, game);
  }

  public static boolean isPromotion(Move move, Piece chosenPiece, Game game) {
    return PromotionCalculator.isPromotion(move, chosenPiece, game);
  }
}
