package oy.chess.ai.algorithm.model;

import oy.chess.model.move.Move;

public class AlgorithmResult {

  private Move move;

  private double score;

  public AlgorithmResult(Move move, double score) {
    this.move = move;
    this.score = score;
  }

  public Move getMove() {
    return move;
  }

  public void setMove(Move move) {
    this.move = move;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }
}
