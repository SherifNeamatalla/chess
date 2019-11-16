package oy.chess.controller.gamelogic.movechecking.movevalidators.helper;

import oy.chess.model.move.Move;

public class DiagonalsValidator {

  public static boolean isSameDiagonal(Move move) {

    int xDiff = move.getOldPosition().getX() - move.getNewPosition().getX();
    int yDiff = move.getOldPosition().getY() - move.getNewPosition().getY();

    return checkRightUp(xDiff, yDiff)
        || checkRightDown(xDiff, yDiff)
        || checkLeftUp(xDiff, yDiff)
        || checkLeftDown(xDiff, yDiff);
  }

  // bunch of functions to check each diagonal
  public static boolean checkRightUp(int xDiff, int yDiff) {

    return xDiff == 0 - yDiff && xDiff > 0;
  }

  public static boolean checkRightDown(int xDiff, int yDiff) {
    return xDiff == yDiff && xDiff < 0;
  }

  public static boolean checkLeftUp(int xDiff, int yDiff) {
    return xDiff == yDiff && xDiff > 0;
  }

  public static boolean checkLeftDown(int xDiff, int yDiff) {
    return yDiff == 0 - xDiff && xDiff < 0;
  }

  public static boolean isSameDiagonal(Move move, int limit) {

    int xDiff = move.getOldPosition().getX() - move.getNewPosition().getX();
    int yDiff = move.getOldPosition().getY() - move.getNewPosition().getY();

    return isSameDiagonal(move)
        && ((xDiff == limit && yDiff == limit)
            || (xDiff == -limit && yDiff == -limit)
            || (xDiff == limit && yDiff == -limit)
            || (xDiff == -limit && yDiff == limit));
  }
}
