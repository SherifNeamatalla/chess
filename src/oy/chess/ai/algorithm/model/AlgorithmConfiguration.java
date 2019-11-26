package oy.chess.ai.algorithm.model;

public class AlgorithmConfiguration {

  private int branchingLimit;

  private int depthLimit;

  private AlgorithmHeuristic algorithmHeuristic;

  private AlgorithmMoveGeneratingStrategy algorithmMoveGeneratingStrategy;

  private AlgorithmMoveChoosingStrategy algorithmMoveChoosingStrategy;

  private Algorithm algorithm;

  public AlgorithmConfiguration(
      int branchingLimit,
      int depthLimit,
      AlgorithmHeuristic algorithmHeuristic,
      AlgorithmMoveGeneratingStrategy algorithmMoveGeneratingStrategy,
      AlgorithmMoveChoosingStrategy algorithmMoveChoosingStrategy,
      Algorithm algorithm) {
    this.branchingLimit = branchingLimit;
    this.depthLimit = depthLimit;
    this.algorithmHeuristic = algorithmHeuristic;
    this.algorithmMoveGeneratingStrategy = algorithmMoveGeneratingStrategy;
    this.algorithmMoveChoosingStrategy = algorithmMoveChoosingStrategy;
    this.algorithm = algorithm;
  }

  public AlgorithmMoveChoosingStrategy getAlgorithmMoveChoosingStrategy() {
    return algorithmMoveChoosingStrategy;
  }

  public void setAlgorithmMoveChoosingStrategy(
      AlgorithmMoveChoosingStrategy algorithmMoveChoosingStrategy) {
    this.algorithmMoveChoosingStrategy = algorithmMoveChoosingStrategy;
  }

  public Algorithm getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  public int getBranchingLimit() {
    return branchingLimit;
  }

  public void setBranchingLimit(int branchingLimit) {
    this.branchingLimit = branchingLimit;
  }

  public int getDepthLimit() {
    return depthLimit;
  }

  public void setDepthLimit(int depthLimit) {
    this.depthLimit = depthLimit;
  }

  public AlgorithmHeuristic getAlgorithmHeuristic() {
    return algorithmHeuristic;
  }

  public void setAlgorithmHeuristic(AlgorithmHeuristic algorithmHeuristic) {
    this.algorithmHeuristic = algorithmHeuristic;
  }

  public AlgorithmMoveGeneratingStrategy getAlgorithmMoveGeneratingStrategy() {
    return algorithmMoveGeneratingStrategy;
  }

  public void setAlgorithmMoveGeneratingStrategy(
      AlgorithmMoveGeneratingStrategy algorithmMoveGeneratingStrategy) {
    this.algorithmMoveGeneratingStrategy = algorithmMoveGeneratingStrategy;
  }
}
