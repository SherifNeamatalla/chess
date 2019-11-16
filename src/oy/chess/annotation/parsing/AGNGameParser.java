package oy.chess.annotation.parsing;

import java.util.List;
import java.util.stream.Collectors;

public class AGNGameParser {
  public static List<String> parseAGNGame(String gameText) {

    List<String> parsedTurns = AGNTurnParser.parseAGNTurns(gameText);

    parsedTurns =
        parsedTurns.stream()
            .filter(p -> p.length() > 0 && !p.matches("\\s+"))
            .collect(Collectors.toList());

    return parsedTurns.stream()
        .map(p -> p.split("\\s"))
        .flatMap(l -> List.of(l).stream())
        .filter(p -> p.length() > 0 && !p.matches("\\s+") && !p.matches("\\d{1,3}-\\d{1,3}"))
        .collect(Collectors.toList());
  }
}
