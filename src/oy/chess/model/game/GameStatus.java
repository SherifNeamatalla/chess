package oy.chess.model.game;

public enum GameStatus {
  IS_WAITING_FOR_PROMOTION_CHOICE,
  NORMAL,
  IS_CHECK_TO_CURRENT_PLAYER,
  IS_CHECK_MATE,
  IS_STALE_MATE;
}
