package oy.chess.util;

import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;

import java.util.ArrayList;
import java.util.Optional;

public class MoveUtilHelper {

  static Move collectMove(Move move, Game game) {

    Move collectedMove = copy(move);

    // Important for keeping track of the game.
    int moveNumber;
    if ((game.getMoves().size() + 1) % 2 == 1) moveNumber = ((game.getMoves().size() + 1) / 2) + 1;
    else moveNumber = (game.getMoves().size() + 1) / 2;

    // New position because move is already made.
    Optional<Piece> movingPieceOptional = PieceFinder.findPiece(move.getNewPosition(), game);

    movingPieceOptional.ifPresent(piece -> collectedMove.setPieceType(piece.getPieceType()));
    collectedMove.setMoveNumber(moveNumber);
    collectedMove.setPlayerColor(game.getCurrentPlayerColor());
    collectedMove.setMoveTypes(new ArrayList<>(game.getCurrentTurnActions()));

    return collectedMove;
  }

  public static Move copy(Move oldMove) {

    return new Move(
        PositionUtilHelper.copy(oldMove.getOldPosition()),
        PositionUtilHelper.copy(oldMove.getNewPosition()),
        oldMove.getMoveNumber(),
        oldMove.getPlayerColor(),
        oldMove.getPieceType(),
        oldMove.getMoveTypes());
  }
}
