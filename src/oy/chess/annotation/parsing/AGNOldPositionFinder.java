package oy.chess.annotation.parsing;

import oy.chess.annotation.util.AmbiguityFinder;
import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class AGNOldPositionFinder {

  private static final Pattern PATTERN =
      Pattern.compile("([KQBNR]?).*([([abcdefgh]) ([12345678])]).*([abcdefgh])([12345678])");

  static Position findOldPosition(
      Position newPosition, PieceType pieceType, Game game, String parsedTurn) {

    Game gameCopy = GameUtilHelper.copy(game);

    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(gameCopy);

    List<Piece> sameTypePieces =
        currentPlayerPieces
            .parallelStream()
            .filter(p -> p.getPieceType() == pieceType)
            .collect(Collectors.toList());

    Optional<Piece> optionalPiece =
        getMovingPiece(sameTypePieces, newPosition, gameCopy, parsedTurn);

    if (optionalPiece.isPresent()) {
      Piece piece = optionalPiece.get();

      return piece.getPosition();
    }

    return null;
  }

  private static Optional<Piece> getMovingPiece(
          List<Piece> pieces, Position newPosition, Game game, String parsedTurn) {

    List<Piece> foundPieces = AmbiguityFinder.getAmbiguousPieces(pieces, newPosition, game);

    // None found.
    if (foundPieces.size() == 0) return Optional.empty();
    // One piece found, has to be the moving one.
    if (foundPieces.size() == 1) return Optional.ofNullable(foundPieces.get(0));
    // More than one found, parse ambiguity
    else {

      return handleAmbiguityAndFindPiece(foundPieces, parsedTurn);
    }
  }

  private static Optional<Piece> handleAmbiguityAndFindPiece(
      List<Piece> foundPieces, String parsedTurn) {

    Matcher matcher = PATTERN.matcher(parsedTurn);

    if (matcher.find()) {

      String y = matcher.group(2);
      String x = matcher.group(3);
      if (y.length() > 0) {
        return foundPieces
            .parallelStream()
            .filter(
                p -> p.getPosition().getY() == AGNConstants.AGN_TO_APP_COLUMN_MAP.get(y.charAt(0)))
            .findFirst();
      }
      if (x.length() > 0) {
        return foundPieces
            .parallelStream()
            .filter(
                p ->
                    p.getPosition().getX()
                        == AGNConstants.AGN_TO_APP_ROW_MAP.get(Integer.parseInt(x)))
            .findFirst();
      }
    }

    return Optional.empty();
  }
}
