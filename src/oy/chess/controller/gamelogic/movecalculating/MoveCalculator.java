package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.util.GameUtilHelper;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;

public class MoveCalculator {

  public static Game calculateNormalMove(Move move, Game game) {

    return MoveCalculatorsManager.doMove(move, game);
  }

  public static Game calculatePromotionMove(Move move, Game game) {

    Game newGame = GameUtilHelper.copy(game);

    if (newGame.getPromotionResult() == null) return newGame;

    newGame = PromotionCalculator.calculate(move, newGame);
    newGame = PieceCaptureCalculator.captureIfCaptured(move, newGame);

    return newGame;
  }
}
