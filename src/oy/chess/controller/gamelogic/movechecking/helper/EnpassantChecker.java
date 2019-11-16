package oy.chess.controller.gamelogic.movechecking.helper;

import oy.chess.controller.gamelogic.movechecking.movevalidators.helper.DiagonalsValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;

import java.util.List;
import java.util.stream.Collectors;

public class EnpassantChecker {

  public static boolean isEnpassant(Move move, Game game) {

    int movingDirection = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 1 : -1;
    if (!(DiagonalsValidator.isSameDiagonal(move, 1)
        && move.getOldPosition().getX() == move.getNewPosition().getX() + movingDirection))
      return false;

    // Enpassant can happen only when white is on 3rd row or black on 4th row, this together with
    // the previous condition is enough to know the location is correct.
    int correctRow = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 3 : 4;
    if (move.getOldPosition().getX() != correctRow) return false;

    return enemyHasEnpassantPawn(move, game);
  }

  private static boolean enemyHasEnpassantPawn(Move move, Game game) {
    List<Piece> enemyPawns =
        GameUtilHelper.getIdlePlayerPieces(game)
            .parallelStream()
            .filter(p -> p.getPieceType() == PieceType.PAWN)
            .collect(Collectors.toList());

    for (Piece enemyPawn : enemyPawns) {

      // This ensures enemy pawn in the right position.
      if (enemyPawn.getPosition().getY() == move.getNewPosition().getY()
          && enemyPawn.getPosition().getX() == move.getOldPosition().getX()
          && enemyPawn.getNumberOfMoves() == 1
          && enemyPawn.getNumberOfTurnsSinceFirstMove() == 1) return true;
    }
    return false;
  }
}
