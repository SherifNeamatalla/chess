package oy.chess.ai.algorithm.interfaces;

import oy.chess.ai.algorithm.model.AlgorithmResult;
import oy.chess.model.game.Game;

public interface IAlgorithmRunner {

  AlgorithmResult getBestMove(Game game);
}
