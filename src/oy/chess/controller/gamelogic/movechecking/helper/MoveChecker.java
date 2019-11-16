package oy.chess.controller.gamelogic.movechecking.helper;

import oy.chess.controller.gamelogic.movechecking.moveavailability.MoveAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.MoveValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveFailureReason;
import oy.chess.model.piece.Piece;

import java.util.Optional;

public class MoveChecker {

  public static MoveFailureReason doGetFailureReason(Move move, Game game) {


    // Checks if it's the current player's turn.
    // Checks if piece exists.
    Optional<Piece> chosenPieceOptional = PieceFinder.findPiece(move.getOldPosition(), game);

    if (chosenPieceOptional.isEmpty()) return (MoveFailureReason.PIECE_NOT_FOUND);

    Piece chosenPiece = chosenPieceOptional.get();

    if (!(chosenPiece.getOwnerPlayerColor() == game.getCurrentPlayerColor())) {
      return (MoveFailureReason.NOT_PLAYER_TURN);
    }

    // Checks if move is valid.
    boolean moveIsValid = MoveValidator.moveIsValid(move, chosenPiece,game);

    if (!moveIsValid) return (MoveFailureReason.MOVE_NOT_VALID);

    // Checks if the cell is not occupied by another piece of the same color.


    boolean moveIsAvailable = MoveAvailabilityChecker.moveIsAvailable(move, chosenPiece, game);

    if (!moveIsAvailable) return (MoveFailureReason.CELL_OR_PATH_NOT_AVAILABLE);

    // Checks if position is currently under check.

    return MoveFailureReason.SUCCESS;
  }
}
