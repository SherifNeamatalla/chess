package oy.chess.util;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.Player;
import oy.chess.model.player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class GameUtilHelper {

  public static Game copy(Game oldGame) {
    Player whitePlayer = PlayerUtilHelper.copy(oldGame.getWhitePlayer());
    Player blackPlayer = PlayerUtilHelper.copy(oldGame.getBlackPlayer());

    return new Game(
        whitePlayer,
        blackPlayer,
        oldGame.getCurrentPlayerColor(),
        oldGame.getGameStatus(),
        oldGame.getPromotionResult(),
        new ArrayList<>(oldGame.getMoves()),
        new ArrayList<>(oldGame.getCurrentTurnActions()));
  }

  public static Game changeTurn(Move move, Game game) {

    Game newGame = copy(game);

    // Important for Enpassant
    getCurrentPlayerPieces(newGame)
        .parallelStream()
        .forEach(
            p -> {
              if (p.getNumberOfMoves() > 0) p.increaseNumberOfTurnsSinceFirstMove();
            });

    if (newGame.getCurrentPlayerColor() == PlayerColor.WHITE) {
      newGame.setCurrentPlayerColor(PlayerColor.BLACK);
    } else if (newGame.getCurrentPlayerColor() == PlayerColor.BLACK) {
      newGame.setCurrentPlayerColor(PlayerColor.WHITE);
    }

    newGame.getMoves().add(MoveUtilHelper.collectMove(move, newGame));
    newGame.getCurrentTurnActions().clear();

    return newGame;
  }

  public static Game changeTurnTemporarily(Game game) {
    Game newGame = copy(game);

    if (newGame.getCurrentPlayerColor() == PlayerColor.WHITE) {
      newGame.setCurrentPlayerColor(PlayerColor.BLACK);
    } else if (newGame.getCurrentPlayerColor() == PlayerColor.BLACK) {
      newGame.setCurrentPlayerColor(PlayerColor.WHITE);
    }

    return newGame;
  }

  public static List<Piece> getCurrentPlayerPieces(Game game) {
    PlayerColor currentPlayerColor = game.getCurrentPlayerColor();

    if (currentPlayerColor == PlayerColor.WHITE) {
      return game.getWhitePlayer().getPieces();
    } else {
      return game.getBlackPlayer().getPieces();
    }
  }

  public static List<Piece> getIdlePlayerPieces(Game game) {
    PlayerColor currentPlayerColor = game.getCurrentPlayerColor();

    if (currentPlayerColor == PlayerColor.WHITE) {
      return game.getBlackPlayer().getPieces();
    } else {
      return game.getWhitePlayer().getPieces();
    }
  }
}
