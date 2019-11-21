package oy.chess.ai.minmax;

import oy.chess.ai.implementations.MinMaxMoveMaker;
import oy.chess.ai.implementations.v1.MinMaxDumbMoveGenerator;
import oy.chess.ai.implementations.v1.MinMaxMaterialScoreCalculator;
import oy.chess.ai.implementations.v1.MinMaxBasicMoveChooser;
import oy.chess.ai.implementations.v2.MinMaxNotPieceFavoredMoveGenerator;
import oy.chess.ai.minmax.interfaces.IMinMaxBestMoveChooser;
import oy.chess.ai.minmax.interfaces.IMinMaxMoveGenerator;
import oy.chess.ai.minmax.interfaces.IMinMaxMoveMaker;
import oy.chess.ai.minmax.interfaces.IMinMaxScoreCalculator;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.minmax.model.MinMaxHeuristic;
import oy.chess.ai.minmax.model.MinMaxPieceChoosingStrategy;
import oy.chess.ai.minmax.model.MinMaxResult;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;

public class MinMaxRunner {

  private static final int BRANCHING_LIMIT = 100;

  // Number of moves in the future that will be calculated ( 1 move per player ).
  private static final int DEPTH_LIMIT = 3;

  private IMinMaxScoreCalculator minMaxScoreCalculator;

  private IMinMaxMoveGenerator minMaxMoveGenerator;

  public MinMaxRunner(
      MinMaxHeuristic minMaxHeuristic, MinMaxPieceChoosingStrategy minMaxPieceChoosingStrategy) {
    switch (minMaxHeuristic) {
      case MATERIAL:
        this.minMaxScoreCalculator = new MinMaxMaterialScoreCalculator();
        break;
    }
    switch (minMaxPieceChoosingStrategy) {
      case FIRST_FOUND:
        minMaxMoveGenerator = new MinMaxDumbMoveGenerator();
        break;
      case NOT_PIECE_FAVORED:
        minMaxMoveGenerator = new MinMaxNotPieceFavoredMoveGenerator();
        break;
    }
  }

  public Move getBestMove(Game game) {

    MinMaxEnum currentMinMax = MinMaxEnum.MAX;
    PlayerColor currentPlayerColor = game.getCurrentPlayerColor();
    IMinMaxMoveMaker moveMaker = new MinMaxMoveMaker();
    IMinMaxBestMoveChooser minMaxBestMoveChooser = new MinMaxBasicMoveChooser();

    long startTime = System.currentTimeMillis();
    MinMaxResult minMaxResult =
        MinMax.getMinMax(
            game,
            currentMinMax,
            currentPlayerColor,
            DEPTH_LIMIT,
            BRANCHING_LIMIT,
            minMaxMoveGenerator,
            minMaxScoreCalculator,
            moveMaker,
            minMaxBestMoveChooser);

    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println(elapsedTime);

    return minMaxResult.getMove();
  }
}
