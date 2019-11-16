package oy.chess.annotation.transforming;

import oy.chess.annotation.model.AGNMove;
import oy.chess.annotation.parsing.AGNConstants;
import oy.chess.annotation.util.AmbiguityFinder;
import oy.chess.model.game.Game;
import oy.chess.model.move.MoveType;

class AnnotationPartGetter {

  static String getMovingPiecePart(AGNMove agnMove) {
    if (agnMove.getPieceType() == null) return "";
    return AGNConstants.PIECE_TO_ANNOTATION_MAP.get(agnMove.getPieceType()).getValue();
  }

  static String getCapturesPart(AGNMove agnMove) {
    if (agnMove.getMoveTypes().indexOf(MoveType.CAPTURES) != -1) return "x";
    return "";
  }

  static String getCheckPart(AGNMove agnMove) {
    if (agnMove.getMoveTypes().indexOf(MoveType.CHECK) != -1) return "+";
    return "";
  }

  static String getCheckMatePart(AGNMove agnMove) {
    if (agnMove.getMoveTypes().indexOf(MoveType.CHECKMATE) != -1) return "++";
    return "";
  }

  static String getCastlingPart(AGNMove agnMove) {
    if (agnMove.getMoveTypes().indexOf(MoveType.KING_SIDE_CASTLING) != -1) return "O-O";
    else if (agnMove.getMoveTypes().indexOf(MoveType.QUEEN_SIDE_CASTLING) != -1) return "O-O-O";
    return "";
  }

  static String getPromotionPart(AGNMove agnMove) {
    if (agnMove.getMoveTypes().indexOf(MoveType.PROMOTION) != -1)
      return "=" + getMovingPiecePart(agnMove);
    return "";
  }

  static String getAmbiguitySolutionPart(AGNMove agnMove, Game game) {
    if (AmbiguityFinder.isAmbiguousMove(MoveTransformer.translateAGNMoveToMove(agnMove), game))
      return agnMove.getOldPosition().getColumn().toString();
    return "";
  }

  static String getPositionPart(AGNMove agnMove) {
    return ""
        + agnMove.getNewPosition().getColumn().getValue()
        + agnMove.getNewPosition().getRow().getValue();
  }

  static String getMoveNumberPart(AGNMove agnMove) {
    return agnMove.getMoveNumber() + ". ";
  }
}
