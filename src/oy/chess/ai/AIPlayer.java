package oy.chess.ai;

import oy.chess.ai.algorithm.AlgorithmRunnerFactory;
import oy.chess.ai.algorithm.interfaces.IAlgorithmRunner;
import oy.chess.ai.algorithm.model.AlgorithmConfiguration;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;

public class AIPlayer {

  private IAlgorithmRunner algorithmRunner;

  public AIPlayer(AlgorithmConfiguration algorithmConfiguration) {

    this.algorithmRunner = AlgorithmRunnerFactory.extractAlgorithmRunner(algorithmConfiguration);
  }

  public Move getBestMove(Game game) {
    return this.algorithmRunner.getBestMove(game).getMove();
  }
}
