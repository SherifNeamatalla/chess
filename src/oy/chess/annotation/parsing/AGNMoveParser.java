package oy.chess.annotation.parsing;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;

import java.util.Optional;

public class AGNMoveParser {

  public static Move parseAGNMove(String parsedTurn, Game game) {

    PieceType pieceType = AGNPieceTypeParser.parseAGNPieceType(parsedTurn);

    Optional<Move> castlingMoveOptional =
        AGNPositionParser.parseAGNPositionIfCastling(parsedTurn, game.getCurrentPlayerColor());

    if (castlingMoveOptional.isPresent()) return castlingMoveOptional.get();

    Position newPosition = AGNPositionParser.parseAGNPosition(parsedTurn);

    Position oldPosition =
        AGNOldPositionFinder.findOldPosition(newPosition, pieceType, game, parsedTurn);

    return new Move(oldPosition, newPosition);
  }

  public static boolean isPromotion(String parsedMove) {
    return AGNPromotionParser.isPromotion(parsedMove);
  }

  public static PieceType getPromotionResult(String parsedMove) {
    return AGNPromotionParser.getPromotionResult(parsedMove);
  }
}
