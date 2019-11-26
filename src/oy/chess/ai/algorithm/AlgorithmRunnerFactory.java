package oy.chess.ai.algorithm;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveChooser;
import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveGenerator;
import oy.chess.ai.algorithm.interfaces.IAlgorithmRunner;
import oy.chess.ai.algorithm.interfaces.IAlgorithmScoreCalculator;
import oy.chess.ai.algorithm.model.AlgorithmConfiguration;
import oy.chess.ai.alphabeta.AlphaBetaRunner;
import oy.chess.ai.implementations.v1.AlgorithmBasicMoveChooser;
import oy.chess.ai.implementations.v1.AlgorithmDumbMoveGenerator;
import oy.chess.ai.implementations.v1.AlgorithmMaterialScoreCalculator;
import oy.chess.ai.implementations.v2.AlgorithmAdvancedMoveChooser;
import oy.chess.ai.minmax.MinMaxRunner;

public class AlgorithmRunnerFactory {

  public static IAlgorithmRunner extractAlgorithmRunner(
      AlgorithmConfiguration algorithmConfiguration) {

    IAlgorithmScoreCalculator algorithmScoreCalculator =
        extractAlgorithmScoreCalculator(algorithmConfiguration);

    IAlgorithmMoveGenerator algorithmMoveGenerator =
        extractAlgorithmMoveGenerator(algorithmConfiguration);

    IAlgorithmMoveChooser algorithmBestMoveChooser =
        extractAlgorithmMoveChooser(algorithmConfiguration);

    switch (algorithmConfiguration.getAlgorithm()) {
      case MINMAX:
        return new MinMaxRunner(
            algorithmScoreCalculator,
            algorithmMoveGenerator,
            algorithmBestMoveChooser,
            algorithmConfiguration.getBranchingLimit(),
            algorithmConfiguration.getDepthLimit());

      case ALPHA_BETA:
        return new AlphaBetaRunner(
            algorithmScoreCalculator,
            algorithmMoveGenerator,
            algorithmBestMoveChooser,
            algorithmConfiguration.getBranchingLimit(),
            algorithmConfiguration.getDepthLimit());
    }
    return null;
  }

  static IAlgorithmScoreCalculator extractAlgorithmScoreCalculator(
      AlgorithmConfiguration algorithmConfiguration) {
    switch (algorithmConfiguration.getAlgorithmHeuristic()) {
      case MATERIAL:
        return new AlgorithmMaterialScoreCalculator();
    }
    return null;
  }

  static IAlgorithmMoveGenerator extractAlgorithmMoveGenerator(
      AlgorithmConfiguration algorithmConfiguration) {
    switch (algorithmConfiguration.getAlgorithmMoveGeneratingStrategy()) {
      case BASIC:
        return new AlgorithmDumbMoveGenerator();
    }
    return null;
  }

  static IAlgorithmMoveChooser extractAlgorithmMoveChooser(
      AlgorithmConfiguration algorithmConfiguration) {
    switch (algorithmConfiguration.getAlgorithmMoveChoosingStrategy()) {
      case BASIC:
        return new AlgorithmBasicMoveChooser();
      case ADVANCED:
        return new AlgorithmAdvancedMoveChooser();
    }
    return null;
  }
}
