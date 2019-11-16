package oy.chess.annotation.parsing;

import oy.chess.model.piece.PieceType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AGNPromotionParser {

  private static Pattern PATTERN = Pattern.compile("[^=]*=([KQNB])");

  static boolean isPromotion(String parsedMove) {
    return parsedMove != null && parsedMove.indexOf('=') != -1;
  }

  static PieceType getPromotionResult(String parsedMove) {
    if (!isPromotion(parsedMove)) return null;

    Matcher matcher = PATTERN.matcher(parsedMove);

    String matched = "";
    if (matcher.find()) {

      matched = matcher.group(1);
    }

    return AGNConstants.AGN_PIECE_TO_PIECE_TYPE_MAP.get(matched);
  }
}
