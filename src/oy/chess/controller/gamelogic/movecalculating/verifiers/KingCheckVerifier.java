package oy.chess.controller.gamelogic.movecalculating.verifiers;

import oy.chess.controller.gamelogic.movechecking.moveavailability.MoveAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.KingMoveValidator;
import oy.chess.controller.gamelogic.movechecking.movevalidators.MoveValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;

import java.util.List;
import java.util.Optional;

public class KingCheckVerifier {

  public static boolean gameIsCheck(Game game) {

    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);

    List<Piece> idlePlayerPieces = GameUtilHelper.getIdlePlayerPieces(game);

    Position kingPosition = getKingPosition(currentPlayerPieces);

    return positionIsCheck(kingPosition, idlePlayerPieces, game);
  }

  private static boolean positionIsCheck(Position kingPosition, Piece idlePlayerPiece, Game game) {

    Position oldPosition = idlePlayerPiece.getPosition();

    Move move = new Move(oldPosition, kingPosition);

    Game tempGame = GameUtilHelper.copy(game);

    tempGame = GameUtilHelper.changeTurnTemporarily(tempGame);

    // In case the chosenPiece  is  a king, we don't need to check if the move is available as the
    // validation is enough.
    if (idlePlayerPiece.getPieceType() == PieceType.KING) {
      return KingMoveValidator.validateMoveWithNoCastling(move, idlePlayerPiece, game);
    }

    return MoveValidator.moveIsValid(move, idlePlayerPiece, tempGame)
        && MoveAvailabilityChecker.moveIsAvailable(move, idlePlayerPiece, tempGame);
  }

  public static boolean positionIsCheck(
      Position kingPosition, List<Piece> idlePlayerPieces, Game game) {

    for (Piece piece : idlePlayerPieces) {
      if (positionIsCheck(kingPosition, piece, game)) return true;
    }
    return false;
  }

  public static Position getKingPosition(List<Piece> pieces) {
    Optional<Piece> pieceOptional =
        pieces.stream().filter(p -> p.getPieceType() == PieceType.KING).findFirst();

    return pieceOptional.map(Piece::getPosition).orElse(null);
  }
}
