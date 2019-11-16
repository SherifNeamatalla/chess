package oy.chess.util;

import oy.chess.model.position.Position;

public class PositionUtilHelper {

  public static Position copy(Position oldPosition) {
    return new Position(oldPosition.getX(), oldPosition.getY());
  }
}
