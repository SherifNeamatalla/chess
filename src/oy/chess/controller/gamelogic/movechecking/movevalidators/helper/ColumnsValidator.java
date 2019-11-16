package oy.chess.controller.gamelogic.movechecking.movevalidators.helper;

import oy.chess.model.move.Move;

public class ColumnsValidator {

  public static boolean isSameColumn(Move move) {
    int y1 = move.getOldPosition().getY();
    int y2 = move.getNewPosition().getY();

    return y1 == y2;
  }

  public static boolean isSameColumn(Move move, int limit) {
    int x1 = move.getOldPosition().getX();
    int x2 = move.getNewPosition().getX();

    return isSameColumn(move) && Math.abs(x1 - x2) == limit;
  }
}
