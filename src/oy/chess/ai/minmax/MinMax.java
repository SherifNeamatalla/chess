package oy.chess.ai.minmax;

import oy.chess.ai.minmax.interfaces.IMinMaxBestMoveChooser;
import oy.chess.ai.minmax.interfaces.IMinMaxMoveGenerator;
import oy.chess.ai.minmax.interfaces.IMinMaxMoveMaker;
import oy.chess.ai.minmax.interfaces.IMinMaxScoreCalculator;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.minmax.model.MinMaxResult;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

class MinMax {

  static MinMaxResult getMinMax(
      Game game,
      MinMaxEnum currentMinMax,
      PlayerColor playerColor,
      int depthLimit,
      int branchingLimit,
      IMinMaxMoveGenerator moveGenerator,
      IMinMaxScoreCalculator scoreCalculator,
      IMinMaxMoveMaker moveMaker,
      IMinMaxBestMoveChooser minMaxBestMoveChooser) {

    // Base case, reached maximum tree depth.
    // In case of base case, the position that's returned is null and is set in the layer above it
    // in the stack.
    if (depthLimit == 0) {
        var score = scoreCalculator.getScore(game, playerColor);
      System.out.println("Base case, Score : "+score+" Turn : "+currentMinMax);
      return new MinMaxResult(null, score);
    }

    List<MinMaxResult> result = new ArrayList<>();
    MinMaxEnum nextMinMax = MinMaxChanger.changeMinMax(currentMinMax);
    int newDepthLimit = depthLimit - 1;

    // Generate each move then calculate the score for every one of them.
    List<Move> moves = moveGenerator.generateMoves(game, branchingLimit);

    moves
        .parallelStream()
        .forEach(
            move -> {
              Game newGame = moveMaker.doMove(move, game);

              MinMaxResult minMaxResult =
                  getMinMax(
                      newGame,
                      nextMinMax,
                      playerColor,
                      newDepthLimit,
                      branchingLimit,
                      moveGenerator,
                      scoreCalculator,
                      moveMaker,
                      minMaxBestMoveChooser);
              minMaxResult.setMove(move);

              synchronized (result) {
                result.add(minMaxResult);
              }
            });

    // Chooses best move according to current min or max.
      MinMaxResult minMaxResult = minMaxBestMoveChooser.chooseBestMove(currentMinMax, result);

    System.out.println("Best result : "+minMaxResult.getScore()+" Turn : "+currentMinMax+" Depth : "+depthLimit);
    return minMaxResult;
  }
}
