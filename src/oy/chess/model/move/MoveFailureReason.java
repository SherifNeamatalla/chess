package oy.chess.model.move;

public enum MoveFailureReason {
  PIECE_NOT_FOUND,
  CELL_OR_PATH_NOT_AVAILABLE,
  WILL_BE_CHECK,
  MOVE_NOT_VALID,
  NOT_PLAYER_TURN,
  NO_PROMOTION_TARGET,
  SUCCESS
}
