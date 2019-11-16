package oy.chess.ai.impelementations.basic;

import oy.chess.ai.minmax.interfaces.IMinMaxScoreCalculator;
import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;

import java.util.List;

public class MinMaxMaterialScoreCalculator implements IMinMaxScoreCalculator {

  private static final int PAWN_SCORE = 1;

  private static final int BISHOP_SCORE = 1;

  private static final int KNIGHT_SCORE = 1;

  private static final int QUEEN_SCORE = 1;

  private static final int ROOK_SCORE = 1;

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

    return score;
  }
}
