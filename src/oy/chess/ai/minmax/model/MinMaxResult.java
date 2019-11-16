package oy.chess.ai.minmax.model;

import oy.chess.model.move.Move;

public class MinMaxResult {

  private Move move;

  private double score;

  public MinMaxResult(Move move, double score) {
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
