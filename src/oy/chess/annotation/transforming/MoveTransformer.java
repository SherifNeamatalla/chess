package oy.chess.annotation.transforming;

import oy.chess.annotation.model.AGNColumn;
import oy.chess.annotation.model.AGNMove;
import oy.chess.annotation.model.AGNPosition;
import oy.chess.annotation.model.AGNRow;
import oy.chess.annotation.parsing.AGNConstants;
import oy.chess.model.move.Move;
import oy.chess.model.position.Position;

import java.util.Map;

import static oy.chess.annotation.model.AGNColumn.*;
import static oy.chess.annotation.model.AGNRow.*;

class MoveTransformer {

  static final Map<AGNColumn, Integer> AGN_TO_APP_COLUMN_MAP;
  static final Map<AGNRow, Integer> AGN_TO_APP_ROW_MAP;

  static {
    AGN_TO_APP_COLUMN_MAP =
        Map.of(AGNColumn.A, 0, AGNColumn.B, 1, AGNColumn.C, 2, D, 3, E, 4, F, 5, G, 6, H, 7);
  }

  static {
    AGN_TO_APP_ROW_MAP = Map.of(_1, 7, _2, 6, _3, 5, _4, 4, _5, 3, _6, 2, _7, 1, _8, 0);
  }

  static AGNMove translateMoveToAGNMove(Move move) {

    AGNRow oldRow = AGNConstants.APP_TO_AGN_ROW_MAP.get(move.getOldPosition().getX());
    AGNRow newRow = AGNConstants.APP_TO_AGN_ROW_MAP.get(move.getNewPosition().getX());

    AGNColumn oldColumn = AGNConstants.APP_TO_AGN_COLUMN_MAP.get(move.getOldPosition().getY());
    AGNColumn newColumn = AGNConstants.APP_TO_AGN_COLUMN_MAP.get(move.getNewPosition().getY());

    return new AGNMove(
        new AGNPosition(oldRow, oldColumn),
        new AGNPosition(newRow, newColumn),
        move.getMoveNumber(),
        move.getPlayerColor(),
        move.getPieceType(),
        move.getMoveTypes());
  }

  static Move translateAGNMoveToMove(AGNMove move) {

    int oldRow = AGN_TO_APP_ROW_MAP.get(move.getOldPosition().getRow());
    int newRow = AGN_TO_APP_ROW_MAP.get(move.getNewPosition().getRow());

    int oldColumn = AGN_TO_APP_COLUMN_MAP.get(move.getOldPosition().getColumn());
    int newColumn = AGN_TO_APP_COLUMN_MAP.get(move.getNewPosition().getColumn());

    return new Move(
        new Position(oldRow, oldColumn),
        new Position(newRow, newColumn),
        move.getMoveNumber(),
        move.getPlayerColor(),
        move.getPieceType(),
        move.getMoveTypes());
  }
}
