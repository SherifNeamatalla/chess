package oy.chess.annotation.parsing;

import oy.chess.model.piece.PieceType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AGNPieceTypeParser {

  private static final Pattern PATTERN = Pattern.compile("^([KQBNR]?)");

  static PieceType parseAGNPieceType(String parsedTurn) {

    Matcher matcher = PATTERN.matcher(parsedTurn);

    String matched = "";
    while (matcher.find()) {

      matched = matcher.group(0);
    }
    return AGNConstants.AGN_PIECE_TO_PIECE_TYPE_MAP.get(matched);
  }
}
