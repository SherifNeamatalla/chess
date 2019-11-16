package oy.chess.annotation.parsing;

import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;
import oy.chess.model.position.Position;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AGNPositionParser {

  private static final Pattern PATTERN = Pattern.compile("([KQBNR]?).*([abcdefgh])([12345678])");

  static Position parseAGNPosition(String parsedMove) {

    Position position = null;
    Matcher matcher = PATTERN.matcher(parsedMove);

    while (matcher.find()) {

      char y = matcher.group(2).charAt(0);
      int x = Integer.parseInt(Character.toString(matcher.group(3).charAt(0)));

      position =
          new Position(
              AGNConstants.AGN_TO_APP_ROW_MAP.get(x), AGNConstants.AGN_TO_APP_COLUMN_MAP.get(y));
    }
    return position;
  }

  static Optional<Move> parseAGNPositionIfCastling(
      String parsedMove, PlayerColor currentPlayerColor) {

    int oldKingRow = currentPlayerColor == PlayerColor.WHITE ? 7 : 0;
    int oldKingColumn = 4;
    Position oldPosition = new Position(oldKingRow, oldKingColumn);

    // King side castling
    if (parsedMove.equals("O-O")) {
      // New position for the king to move in.
      int newColumn = 6;
      Position newPosition = new Position(oldKingRow, newColumn);

      return Optional.of(new Move(oldPosition, newPosition));

    } else if (parsedMove.equals("O-O-O")) {

      int newColumn = 1;
      Position newPosition = new Position(oldKingRow, newColumn);

      return Optional.of(new Move(oldPosition, newPosition));
    }

    return Optional.empty();
  }
}
