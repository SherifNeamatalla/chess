package oy.chess.controller.gamelogic.movechecking.movevalidators.helper;

import oy.chess.model.move.Move;

public class RowsValidator {

  public static boolean isSameRow(Move move) {
    int x1 = move.getOldPosition().getX();
    int x2 = move.getNewPosition().getX();

    return x1 == x2;
  }

  public static boolean isSameRow(Move move, int limit) {

    int y1 = move.getOldPosition().getY();
    int y2 = move.getNewPosition().getY();

    return isSameRow(move) && Math.abs(y1 - y2) == limit;
  }
}
