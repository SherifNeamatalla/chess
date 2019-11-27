package oy.chess.ai.implementations.v1;

import oy.chess.ai.algorithm.interfaces.IAlgorithmScoreCalculator;
import oy.chess.controller.gamelogic.movecalculating.verifiers.CheckMateVerifier;
import oy.chess.controller.gamelogic.movecalculating.verifiers.KingCheckVerifier;
import oy.chess.controller.gamelogic.movecalculating.verifiers.StaleMateVerifier;
import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;

import java.util.List;

public class AlgorithmMaterialScoreCalculator implements IAlgorithmScoreCalculator {

  private static final int PAWN_SCORE = 100;

  private static final int BISHOP_SCORE = 330;

  private static final int KNIGHT_SCORE = 320;

  private static final int QUEEN_SCORE = 900;

  private static final int ROOK_SCORE = 500;

  private static final int STALEMATE_SCORE = -1;

  private static final int CHECK_SCORE = 200;

  @Override
  public double getScore(Game game, PlayerColor currentPlayer) {

    double score = 0;
    List<Piece> currentPlayerPieces = GameUtilHelper.getPiecesByPlayerColor(game, currentPlayer);
    List<Piece> idlePlayerPieces =
        GameUtilHelper.getOppositePiecesByPlayerColor(game, currentPlayer);

    // Returns -1 because we want stalemate to be at least as good as any other bad move, but not as
    // bad as neutral moves.

    boolean currentPlayerIsCurrentGamePlayer = currentPlayer == game.getCurrentPlayerColor();

    boolean isCheckToCurrentPlayer = KingCheckVerifier.gameIsCheck(game);

    if (isCheckToCurrentPlayer) {

      if (CheckMateVerifier.isCheckMate(game)) {
        return currentPlayerIsCurrentGamePlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      }
      score += currentPlayerIsCurrentGamePlayer ? -CHECK_SCORE : CHECK_SCORE;

    } else {
      Game changedTurnGame = GameUtilHelper.changeTurnTemporarily(game);

      boolean isCheckToOtherPlayer = KingCheckVerifier.gameIsCheck(changedTurnGame);

      if (isCheckToOtherPlayer) {

        if (CheckMateVerifier.isCheckMate(changedTurnGame)) {
          return currentPlayerIsCurrentGamePlayer ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        score += currentPlayerIsCurrentGamePlayer ? CHECK_SCORE : -CHECK_SCORE;
      } else {
        if (StaleMateVerifier.isStaleMate(game)) return STALEMATE_SCORE;
      }
    }

    // Not parallel for thread safety
    for (Piece piece : currentPlayerPieces) {

      switch (piece.getPieceType()) {
        case PAWN:
          score += PAWN_SCORE;
          break;
        case BISHOP:
          score += BISHOP_SCORE;
          break;
        case KNIGHT:
          score += KNIGHT_SCORE;
          break;
        case ROOK:
          score += ROOK_SCORE;
          break;
        case QUEEN:
          score += QUEEN_SCORE;
          break;
        default:
      }
    }

    for (Piece piece : idlePlayerPieces) {

      switch (piece.getPieceType()) {
        case PAWN:
          score -= PAWN_SCORE;
          break;
        case BISHOP:
          score -= BISHOP_SCORE;
          break;
        case KNIGHT:
          score -= KNIGHT_SCORE;
          break;
        case ROOK:
          score -= ROOK_SCORE;
          break;
        case QUEEN:
          score -= QUEEN_SCORE;
          break;
        default:
      }
    }

    return score;
  }
}
