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

public class CheckMateVerifier {

  public static boolean isCheckMate(Game game) {
    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);

    List<Piece> idlePlayerPieces = GameUtilHelper.getIdlePlayerPieces(game);

    Position kingPosition = KingCheckVerifier.getKingPosition(currentPlayerPieces);

    // If not check then not checkMate
    if (!KingCheckVerifier.positionIsCheck(kingPosition, idlePlayerPieces, game)) return false;

    for (Piece piece : currentPlayerPieces) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          Move move = new Move(piece.getPosition(), new Position(i, j));

          if (MoveValidator.moveIsValid(move, piece, game)
              && MoveAvailabilityChecker.moveIsAvailable(move, piece, game)) {
            Game tempGame = MoveCalculator.calculateNormalMove(move, game);
            Position newKingPosition =
                KingCheckVerifier.getKingPosition(GameUtilHelper.getCurrentPlayerPieces(tempGame));
            if (!KingCheckVerifier.positionIsCheck(
                newKingPosition, GameUtilHelper.getIdlePlayerPieces(tempGame), tempGame))
              return false;
          }
        }
      }
    }

    return true;
  }
}
