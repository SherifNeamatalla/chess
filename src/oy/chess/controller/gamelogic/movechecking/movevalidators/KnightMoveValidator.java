package oy.chess.controller.gamelogic.movechecking.movevalidators;

import oy.chess.model.move.Move;

 class KnightMoveValidator {
   static boolean validateMove(Move move) {

    int xDiff = move.getOldPosition().getX() - move.getNewPosition().getX();
    int yDiff = move.getOldPosition().getY() - move.getNewPosition().getY();

    return (xDiff == 2 && yDiff == 1)
        || (xDiff == 2 && yDiff == -1)
        || (xDiff == 1 && yDiff == 2)
        || (xDiff == 1 && yDiff == -2)
        || (xDiff == -2 && yDiff == 1)
        || (xDiff == -2 && yDiff == -1)
        || (xDiff == -1 && yDiff == 2)
        || (xDiff == -1 && yDiff == -2);
  }
}
