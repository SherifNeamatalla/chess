package oy.chess.ai.alphabeta;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveChooser;
import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveGenerator;
import oy.chess.ai.algorithm.interfaces.IAlgorithmRunner;
import oy.chess.ai.algorithm.interfaces.IAlgorithmScoreCalculator;
import oy.chess.ai.algorithm.model.AlgorithmResult;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.model.game.Game;
import oy.chess.model.player.PlayerColor;

public class AlphaBetaRunner implements IAlgorithmRunner {

  private IAlgorithmScoreCalculator algorithmScoreCalculator;

  private IAlgorithmMoveGenerator algorithmMoveGenerator;

  private IAlgorithmMoveChooser algorithmBestMoveChooser;

  private int branchingLimit;

  private int depthLimit;

  public AlphaBetaRunner(
      IAlgorithmScoreCalculator algorithmScoreCalculator,
      IAlgorithmMoveGenerator algorithmMoveGenerator,
      IAlgorithmMoveChooser algorithmBestMoveChooser,
      int branchingLimit,
      int depthLimit) {
    this.algorithmScoreCalculator = algorithmScoreCalculator;
    this.algorithmMoveGenerator = algorithmMoveGenerator;
    this.algorithmBestMoveChooser = algorithmBestMoveChooser;
    this.branchingLimit = branchingLimit;
    this.depthLimit = depthLimit;
  }

  @Override
  public AlgorithmResult getBestMove(Game game) {
    MinMaxEnum currentMinMax = MinMaxEnum.MAX;
    PlayerColor currentPlayerColor = game.getCurrentPlayerColor();

    long startTime = System.currentTimeMillis();
    AlgorithmResult algorithmResult =
        AlphaBeta.getAlphaBetaMinMax(
            this.algorithmMoveGenerator,
            this.algorithmScoreCalculator,
            this.algorithmBestMoveChooser,
            game,
            currentPlayerColor,
            this.branchingLimit,
            this.depthLimit,
            currentMinMax);

    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println(elapsedTime);

    return algorithmResult;
  }
}
