package oy.chess.controller.gamelogic;

import oy.chess.controller.gamelogic.movecalculating.MoveCalculator;
import oy.chess.controller.gamelogic.movecalculating.verifiers.CheckMateVerifier;
import oy.chess.controller.gamelogic.movecalculating.verifiers.KingCheckVerifier;
import oy.chess.controller.gamelogic.movecalculating.verifiers.StaleMateVerifier;
import oy.chess.controller.gamelogic.movechecking.helper.MoveChecker;
import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameStatus;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveFailureReason;
import oy.chess.model.move.MoveResult;
import oy.chess.model.move.MoveType;
import oy.chess.util.GameUtilHelper;

public class MoveMaker {

  public static MoveResult doGetMoveResult(Move move, Game game) {

    // checks if move is valid.
    MoveFailureReason moveFailureReason = MoveChecker.doGetFailureReason(move, game);
    if (moveFailureReason != MoveFailureReason.SUCCESS)
      return new MoveResult(game, false, moveFailureReason);

    if (game.getPromotionResult() == null
        && MoveCalculator.isPromotion(
            move, PieceFinder.findPiece(move.getOldPosition(), game).get(), game))
      return new MoveResult(game, false, MoveFailureReason.NO_PROMOTION_TARGET);

    Game newGame = MoveCalculator.calculateNormalMove(move, game);

    // Checks if after the move the player to be moving will be in check, if yes the move fails.
    boolean willBeCheck = KingCheckVerifier.gameIsCheck(newGame);

    if (willBeCheck) return new MoveResult(game, false, MoveFailureReason.WILL_BE_CHECK);

    newGame = GameUtilHelper.changeTurnTemporarily(newGame);

    boolean isCheckToCurrentPlayer = KingCheckVerifier.gameIsCheck(newGame);
    if (isCheckToCurrentPlayer) {
      newGame.setGameStatus(GameStatus.IS_CHECK_TO_CURRENT_PLAYER);
      newGame.getCurrentTurnActions().add(MoveType.CHECK);
    }

    if (isCheckToCurrentPlayer) {
      if (CheckMateVerifier.isCheckMate(newGame)) {
        newGame.setGameStatus(GameStatus.IS_CHECK_MATE);
        newGame.getCurrentTurnActions().add(MoveType.CHECKMATE);
      }
    }

    boolean isStaleMate = false;

    if (!isCheckToCurrentPlayer) {
      isStaleMate = StaleMateVerifier.isStaleMate(newGame);

      if (isStaleMate) newGame.setGameStatus(GameStatus.IS_STALE_MATE);
    }

    if (!isStaleMate && !isCheckToCurrentPlayer) newGame.setGameStatus(GameStatus.NORMAL);

    // calling changeTurnTemporarily again to revert the changes, dirty but works
    newGame = GameUtilHelper.changeTurnTemporarily(newGame);
    newGame = GameUtilHelper.changeTurn(move, newGame);

    return new MoveResult(newGame, true);
  }
}
