package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveType;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;

import java.util.stream.Collectors;

class PieceCaptureCalculator {

  static Game captureIfCaptured(Move move, Game game) {
    Game newGame = GameUtilHelper.copy(game);

    PlayerColor currentPlayer = newGame.getCurrentPlayerColor();

    if (currentPlayer == PlayerColor.WHITE)
      newGame
          .getBlackPlayer()
          .setPieces(
              newGame
                  .getBlackPlayer()
                  .getPieces()
                  .parallelStream()
                  .filter(p -> !p.getPosition().equals(move.getNewPosition()))
                  .collect(Collectors.toList()));
    else if (currentPlayer == PlayerColor.BLACK)
      newGame
          .getWhitePlayer()
          .setPieces(
              newGame
                  .getWhitePlayer()
                  .getPieces()
                  .parallelStream()
                  .filter(p -> !p.getPosition().equals(move.getNewPosition()))
                  .collect(Collectors.toList()));
    if (newGame.getBlackPlayer().getPieces().size() < game.getBlackPlayer().getPieces().size()
        || newGame.getWhitePlayer().getPieces().size() < game.getWhitePlayer().getPieces().size())
      newGame.getCurrentTurnActions().add(MoveType.CAPTURES);

    return newGame;
  }
}
