package oy.chess.ai.implementations.v1;

import oy.chess.ai.algorithm.interfaces.IAlgorithmScoreCalculator;
import oy.chess.controller.gamelogic.movecalculating.verifiers.KingCheckVerifier;
import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;

import java.util.List;
import java.util.Optional;

public class AlgorithmMaterialScoreCalculator implements IAlgorithmScoreCalculator {

  private static final int PAWN_SCORE = 100;

  private static final int BISHOP_SCORE = 330;

  private static final int KNIGHT_SCORE = 320;

  private static final int QUEEN_SCORE = 900;

  private static final int ROOK_SCORE = 500;

  @Override
  public double getScore(Game game, PlayerColor currentPlayer) {

    double score = 0;
    List<Piece> currentPlayerPieces = GameUtilHelper.getPiecesByPlayerColor(game, currentPlayer);
    List<Piece> idlePlayerPieces =
        GameUtilHelper.getOppositePiecesByPlayerColor(game, currentPlayer);

    // TODO : Add score for check

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

    // King might be captures in this case, cheaper than checking for checkmate for now
    Optional<Piece> kingOptional =
        currentPlayerPieces
            .parallelStream()
            .filter(p -> p.getPieceType() == PieceType.KING)
            .findFirst();

    if (kingOptional.isEmpty()) return Integer.MIN_VALUE;

    boolean isCheckToCurrentPlayer =
        KingCheckVerifier.positionIsCheck(kingOptional.get().getPosition(), idlePlayerPieces, game);

    if (isCheckToCurrentPlayer) score -= 15;

    return score;
  }
}
