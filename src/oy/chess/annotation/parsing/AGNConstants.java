package oy.chess.annotation.parsing;

import oy.chess.annotation.model.AGNColumn;
import oy.chess.annotation.model.AGNPieceType;
import oy.chess.annotation.model.AGNRow;
import oy.chess.model.piece.PieceType;

import java.util.Map;

import static oy.chess.annotation.model.AGNColumn.*;
import static oy.chess.annotation.model.AGNRow.*;

public class AGNConstants {

  static final Map<String, PieceType> AGN_PIECE_TO_PIECE_TYPE_MAP;

  static final Map<Character, Integer> AGN_TO_APP_COLUMN_MAP;
  static final Map<Integer, Integer> AGN_TO_APP_ROW_MAP;

  public static final Map<Integer, AGNRow> APP_TO_AGN_ROW_MAP;
  public static final Map<Integer, AGNColumn> APP_TO_AGN_COLUMN_MAP;

  public static final Map<PieceType, AGNPieceType> PIECE_TO_ANNOTATION_MAP;

  static {
    PIECE_TO_ANNOTATION_MAP =
        Map.of(
            PieceType.KING,
            AGNPieceType.K,
            PieceType.QUEEN,
            AGNPieceType.Q,
            PieceType.BISHOP,
            AGNPieceType.B,
            PieceType.ROOK,
            AGNPieceType.R,
            PieceType.KNIGHT,
            AGNPieceType.N,
            PieceType.PAWN,
            AGNPieceType.NONE);
  }

  static {
    APP_TO_AGN_ROW_MAP = Map.of(7, _1, 6, _2, 5, _3, 4, _4, 3, _5, 2, _6, 1, _7, 0, _8);
  }

  static {
    APP_TO_AGN_COLUMN_MAP = Map.of(0, A, 1, B, 2, C, 3, D, 4, E, 5, F, 6, G, 7, H);
  }

  static {
    AGN_TO_APP_COLUMN_MAP = Map.of('a', 0, 'b', 1, 'c', 2, 'd', 3, 'e', 4, 'f', 5, 'g', 6, 'h', 7);
  }

  static {
    AGN_TO_APP_ROW_MAP = Map.of(1, 7, 2, 6, 3, 5, 4, 4, 5, 3, 6, 2, 7, 1, 8, 0);
  }

  static {
    AGN_PIECE_TO_PIECE_TYPE_MAP =
        Map.of(
            "K",
            PieceType.KING,
            "Q",
            PieceType.QUEEN,
            "B",
            PieceType.BISHOP,
            "R",
            PieceType.ROOK,
            "N",
            PieceType.KNIGHT,
            "",
            PieceType.PAWN);
  }
}
