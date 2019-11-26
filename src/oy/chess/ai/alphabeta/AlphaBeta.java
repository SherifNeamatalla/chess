package oy.chess.ai.alphabeta;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveChooser;
import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveGenerator;
import oy.chess.ai.algorithm.interfaces.IAlgorithmScoreCalculator;
import oy.chess.ai.algorithm.model.AlgorithmResult;
import oy.chess.ai.minmax.MinMaxChanger;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;

import java.util.List;

public class AlphaBeta {

  static AlgorithmResult getAlphaBetaMinMax(
      IAlgorithmMoveGenerator moveGenerator,
      IAlgorithmScoreCalculator scoreCalculator,
      IAlgorithmMoveChooser bestMoveChooser,
      Game game,
      PlayerColor playerColor,
      int branchingLimit,
      int depthLimit,
      MinMaxEnum currentMinMax,
      double alpha,
      double beta) {

    // Base case, reached maximum tree depth.
    // In case of base case, the position that's returned is null and is set in the layer above it
    // in the stack.
    if (depthLimit == 0) {
      var score = scoreCalculator.getScore(game, playerColor);
      return new AlgorithmResult(null, score);
    }

    MinMaxEnum nextMinMax = MinMaxChanger.changeMinMax(currentMinMax);
    int newDepthLimit = depthLimit - 1;

    // Generate each move then calculate the score for every one of them.
    List<Move> moves = moveGenerator.generateMoves(game, branchingLimit);

    AlgorithmResult bestResult = null;

    if (currentMinMax == MinMaxEnum.MAX) {

      double maxScore = Integer.MIN_VALUE;

      for (Move move : moves) {
        Game newGame = MoveMaker.doGetMoveResult(move, game).getGame();

        AlgorithmResult algorithmResult =
            AlphaBeta.getAlphaBetaMinMax(
                moveGenerator,
                scoreCalculator,
                bestMoveChooser,
                newGame,
                playerColor,
                branchingLimit,
                newDepthLimit,
                nextMinMax,
                alpha,
                beta);

        algorithmResult.setMove(move);

        if (bestResult == null || maxScore <= algorithmResult.getScore()) {

          bestResult = algorithmResult;

          maxScore = algorithmResult.getScore();
        }
        alpha = Double.max(alpha, maxScore);
        if (alpha >= beta) {

          break;
        }
      }

    } else if (currentMinMax == MinMaxEnum.MIN) {
      for (Move move : moves) {

        double minScore = Integer.MAX_VALUE;

        Game newGame = MoveMaker.doGetMoveResult(move, game).getGame();

        AlgorithmResult algorithmResult =
            AlphaBeta.getAlphaBetaMinMax(
                moveGenerator,
                scoreCalculator,
                bestMoveChooser,
                newGame,
                playerColor,
                branchingLimit,
                newDepthLimit,
                nextMinMax,
                alpha,
                beta);

        algorithmResult.setMove(move);

        if (bestResult == null || minScore >= algorithmResult.getScore()) {

          bestResult = algorithmResult;

          minScore = algorithmResult.getScore();
        }
        beta = Double.min(beta, minScore);
        if (alpha >= beta) {

          break;
        }
      }
    }

    return bestResult;
  }
}
