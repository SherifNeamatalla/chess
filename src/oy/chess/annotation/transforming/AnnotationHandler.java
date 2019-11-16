package oy.chess.annotation.transforming;

import oy.chess.annotation.model.AGNMove;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;

/** Entering point for this package, handles all annotations requests. */
public class AnnotationHandler {

  public static String getAnnotationFromMove(Move move, Game game) {

    AGNMove agnMove = MoveTransformer.translateMoveToAGNMove(move);

    return AnnotationCollector.createAnnotationFromAGNMove(agnMove,game);
  }
}
