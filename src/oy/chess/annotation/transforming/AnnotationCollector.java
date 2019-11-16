package oy.chess.annotation.transforming;

import oy.chess.annotation.model.AGNMove;
import oy.chess.model.game.Game;

class AnnotationCollector {

  static String createAnnotationFromAGNMove(AGNMove agnMove, Game game) {

    String finalAnnotation = "";

    String moveNumber = AnnotationPartGetter.getMoveNumberPart(agnMove);

    String castling = AnnotationPartGetter.getCastlingPart(agnMove);

    if (castling.length() == 0) {

      String movingPiece = AnnotationPartGetter.getMovingPiecePart(agnMove);

      String position = AnnotationPartGetter.getPositionPart(agnMove);

      String captures = AnnotationPartGetter.getCapturesPart(agnMove);

      String check = AnnotationPartGetter.getCheckPart(agnMove);

      String checkMate = AnnotationPartGetter.getCheckMatePart(agnMove);

      String ambiguitySolution = AnnotationPartGetter.getAmbiguitySolutionPart(agnMove,game);

      String promotion = AnnotationPartGetter.getPromotionPart(agnMove);

      if (promotion.length() == 0)
        finalAnnotation +=
            moveNumber + movingPiece + ambiguitySolution + captures + position + check + checkMate;
      else finalAnnotation += moveNumber + captures + position + promotion + check + checkMate;
    } else {
      finalAnnotation += moveNumber + castling;
    }

    return finalAnnotation;
  }
}
