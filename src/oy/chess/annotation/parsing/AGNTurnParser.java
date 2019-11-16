package oy.chess.annotation.parsing;

import java.util.List;
import java.util.regex.Pattern;

class AGNTurnParser {

  private static final Pattern PATTERN = Pattern.compile("\\d{1,3}\\.");

  static List<String> parseAGNTurns(String text) {

    String[] myStrings = PATTERN.split(text);

    return List.of(myStrings);
  }
}
