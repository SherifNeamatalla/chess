package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.util.GameUtilHelper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class EnpassantCalculator {

  static Game calculate(Move move, Game game) {
    Game tempGame = GameUtilHelper.copy(game);
    List<Piece> enemyPawns =
        GameUtilHelper.getIdlePlayerPieces(tempGame)
            .parallelStream()
            .filter(p -> p.getPieceType() == PieceType.PAWN)
            .collect(Collectors.toList());

    for (Piece enemyPawn : enemyPawns) {

      // This ensures enemy pawn in the right position.
      if (enemyPawn.getPosition().getY() == move.getNewPosition().getY()
          && enemyPawn.getPosition().getX() == move.getOldPosition().getX()
          && enemyPawn.getNumberOfMoves() == 1
          && enemyPawn.getNumberOfTurnsSinceFirstMove() == 1) {
        GameUtilHelper.getIdlePlayerPieces(tempGame).remove(enemyPawn);

        Optional<Piece> chosenPawnOptional = PieceFinder.findPiece(move.getOldPosition(), tempGame);

        if (chosenPawnOptional.isPresent()) {
          Piece chosenPawn = chosenPawnOptional.get();
          chosenPawn.setPosition(move.getNewPosition());
        }

        return tempGame;
      }
    }
    return tempGame;
  }
}
