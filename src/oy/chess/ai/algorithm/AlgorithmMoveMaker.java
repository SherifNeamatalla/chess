package oy.chess.ai.algorithm;

import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.controller.gamelogic.movecalculating.PromotionCalculator;
import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

public class AlgorithmMoveMaker {

  public static Game doGetMoveResult(Move move, Game game) {

    if (PromotionCalculator.isPromotion(
        move, PieceFinder.findPiece(move.getOldPosition(), game).get(), game)) {
      game.setPromotionResult(new Piece(1, null, move.getPieceType(), null, true));
    }
    return MoveMaker.doGetMoveResult(move, game).getGame();
  }
}
