package oy.chess.annotation.util;

import oy.chess.controller.gamelogic.movechecking.moveavailability.MoveAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.MoveValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AmbiguityFinder {

  public static boolean isAmbiguousMove(Move move, Game game) {

    PieceType pieceType = move.getPieceType();

    Game gameCopy = GameUtilHelper.copy(game);

    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(gameCopy);

    List<Piece> sameTypePieces =
        currentPlayerPieces
            .parallelStream()
            .filter(p -> p.getPieceType() == pieceType)
            .collect(Collectors.toList());

    List<Piece> ambiguousPieces =
        getAmbiguousPieces(sameTypePieces, move.getNewPosition(), gameCopy);

    return ambiguousPieces.size() > 1;
  }

  public static List<Piece> getAmbiguousPieces(
      List<Piece> pieces, Position newPosition, Game game) {

    List<Piece> foundPieces = new ArrayList<>();
    for (Piece piece : pieces) {
      Position oldPosition = piece.getPosition();

      Move move = new Move(oldPosition, newPosition);

      Game tempGame = GameUtilHelper.copy(game);

      if (MoveValidator.moveIsValid(move, piece, tempGame)
          && MoveAvailabilityChecker.moveIsAvailable(move, piece, tempGame)) foundPieces.add(piece);
    }
    return foundPieces;
  }
}
