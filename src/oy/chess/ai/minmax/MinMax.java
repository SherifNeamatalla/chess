package oy.chess.ai.minmax;

import oy.chess.ai.algorithm.AlgorithmMoveMaker;
import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveChooser;
import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveGenerator;
import oy.chess.ai.algorithm.interfaces.IAlgorithmScoreCalculator;
import oy.chess.ai.algorithm.model.AlgorithmResult;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

class MinMax {

  static AlgorithmResult getMinMax(
      IAlgorithmMoveGenerator moveGenerator,
      IAlgorithmScoreCalculator scoreCalculator,
      IAlgorithmMoveChooser minMaxBestMoveChooser,
      Game game,
      PlayerColor playerColor,
      int branchingLimit,
      int depthLimit,
      MinMaxEnum currentMinMax) {

    // Base case, reached maximum tree depth.
    // In case of base case, the position that's returned is null and is set in the layer above it
    // in the stack.
    if (depthLimit == 0) {
      var score = scoreCalculator.getScore(game, playerColor);
      return new AlgorithmResult(null, score);
    }

    List<AlgorithmResult> result = new ArrayList<>();
    MinMaxEnum nextMinMax = MinMaxChanger.changeMinMax(currentMinMax);
    int newDepthLimit = depthLimit - 1;

    // Generate each move then calculate the score for every one of them.
    List<Move> moves = moveGenerator.generateMoves(game, branchingLimit);

    if (moves.size() == 0) {
      var score = scoreCalculator.getScore(game, playerColor);
      return new AlgorithmResult(null, score);
    }

    moves
        .stream()
        .forEach(
            move -> {
              Game newGame = AlgorithmMoveMaker.doGetMoveResult(move, game);

              AlgorithmResult algorithmResult =
                  getMinMax(
                      moveGenerator,
                      scoreCalculator,
                      minMaxBestMoveChooser,
                      newGame,
                      playerColor,
                      branchingLimit,
                      newDepthLimit,
                      nextMinMax);

              // Null when there are no moves.
              algorithmResult.setMove(move);

              synchronized (result) {
                result.add(algorithmResult);
              }
            });

    return minMaxBestMoveChooser.chooseBestMove(currentMinMax, result);
  }
}
