package oy.chess.controller.gamelogic.movecalculating.verifiers;

import oy.chess.controller.gamelogic.movecalculating.MoveCalculator;
import oy.chess.util.GameUtilHelper;
import oy.chess.controller.gamelogic.movechecking.moveavailability.MoveAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.MoveValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;

import java.util.List;

public class StaleMateVerifier {
  public static boolean isStaleMate(Game game) {
    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);

    return onePlayerIsStaleMate(currentPlayerPieces, game);
  }

  private static boolean onePlayerIsStaleMate(List<Piece> pieces, Game game) {

    for (Piece piece : pieces) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          Move move = new Move(piece.getPosition(), new Position(i, j));

          if (MoveValidator.moveIsValid(move, piece, game)
              && MoveAvailabilityChecker.moveIsAvailable(move, piece, game)) {
            Game tempGame = MoveCalculator.calculateNormalMove(move, game);

            if (!KingCheckVerifier.positionIsCheck(
                new Position(i, j), GameUtilHelper.getIdlePlayerPieces(tempGame), tempGame))
              return false;
          }
          ;
        }
      }
    }
    return true;
  }
}
