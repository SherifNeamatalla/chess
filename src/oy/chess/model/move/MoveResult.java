package oy.chess.model.move;

import oy.chess.model.game.Game;

public class MoveResult {

  private Game game;

  private boolean success;

  private MoveFailureReason moveFailureReason;

  public MoveResult(Game game, boolean success) {
    this.game = game;
    this.success = success;
  }

  public MoveResult(Game game, boolean success, MoveFailureReason moveFailureReason) {
    this.game = game;
    this.success = success;
    this.moveFailureReason = moveFailureReason;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public MoveFailureReason getMoveFailureReason() {
    return moveFailureReason;
  }

  public void setMoveFailureReason(MoveFailureReason moveFailureReason) {
    this.moveFailureReason = moveFailureReason;
  }
}
