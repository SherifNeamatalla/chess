package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;

import java.util.Optional;

public class CastlingCalculator {


  static Game calculateCastlingForRook(Move move, Piece chosenPiece, Game game) {

    Game newGame = GameUtilHelper.copy(game);

    if (move.getNewPosition().getY() == 1) {

      Optional<Piece> rookOptional =
          PieceFinder.findPiece(new Position(chosenPiece.getPosition().getX(), 0), newGame);

      if (rookOptional.isPresent()) {

        Piece rook = rookOptional.get();

        rook.setPosition(new Position(chosenPiece.getPosition().getX(), 3));

        doMoveKing(move, newGame);
      }

    }
    // Castling to right.
    else if (move.getNewPosition().getY() == 6) {

      Optional<Piece> rookOptional =
          PieceFinder.findPiece(new Position(chosenPiece.getPosition().getX(), 7), newGame);

      if (rookOptional.isPresent()) {

        Piece rook = rookOptional.get();

        rook.setPosition(new Position(chosenPiece.getPosition().getX(), 5));

        doMoveKing(move, newGame);
      }
    }

    return newGame;
  }

  private static void doMoveKing(Move move, Game game) {

    Optional<Piece> kingOptional =
        GameUtilHelper.getCurrentPlayerPieces(game)
            .parallelStream()
            .filter(piece -> piece.getPieceType() == PieceType.KING)
            .findFirst();

    if (kingOptional.isPresent()) {

      Piece king = kingOptional.get();

      king.setPosition(move.getNewPosition());
    }
  }
}
